package me.apqx.demo.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.apqx.demo.LogUtil;
import me.apqx.demo.R;

public class HorizontalPager extends RelativeLayout {
    private ViewPager viewPager;
    private Adapter adapter;
    private List<ViewGroup> pagerList = new ArrayList<>();
    private CusPagerAdapter pagerAdapter;

    /**
     * 页面指示器
     */
    private RadioGroup rg_indicator;

    /**
     * 是否执行轮播动画
     */
    private boolean loopAnimEnabled = false;

    /**
     * 动画间隔
     */
    private final int animTimeMills = 3000;

    private final int indicatorWidth = DisplayUtils.dpToPx(getContext(), 10);
    private final int indicatorHeight = DisplayUtils.dpToPx(getContext(), 2);
    private final int indicatorMarginBottom = DisplayUtils.dpToPx(getContext(), 15);

    /**
     * GridLayout的最终高度和宽度
     */
    private int gridAllWidth;
    private int gridAllHeight;
    /**
     * 每个item的宽度
     */
    private int itemWidth;
    /**
     * 每个item的高度
     */
    private int itemHeight;

    /**
     * GridLayout左右margin
     */
    private int marginSide = DisplayUtils.dpToPx(getContext(), 6);
    /**
     * GridLayout下Margin
     */
    private int marginBottom = DisplayUtils.dpToPx(getContext(), 28);
    /**
     * GridLayout上Margin
     */
    private int marginTop = DisplayUtils.dpToPx(getContext(), 18);


    private Handler handler;

    private Runnable animRunnable;
    private long lastTouchTime;

    public HorizontalPager(@NonNull Context context) {
        super(context);
        init();
    }

    public HorizontalPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LogUtil.INSTANCE.d("HorizontalPager init");
        handler = new Handler();
        viewPager = new ViewPager(getContext());
        LayoutParams vp_params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(viewPager, vp_params);
        pagerAdapter = new CusPagerAdapter(pagerList);
        viewPager.setAdapter(pagerAdapter);

        // 设置切换动画速度
        try {
            Field mField;

            mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);

            Scroller mScroller = new CusScroller(getContext(),
                    new AccelerateInterpolator());
            mField.set(viewPager, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }


        rg_indicator = new RadioGroup(getContext());
        rg_indicator.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams rg_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rg_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rg_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rg_params.bottomMargin = indicatorMarginBottom;
        addView(rg_indicator, rg_params);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) rg_indicator.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        adapter.bindPagerView(this);
        refresh();
    }

    public void setLoopAnimEnabled(boolean loopAnimEnabled) {
        this.loopAnimEnabled = loopAnimEnabled;
        doAnim();
    }

    private void refresh() {
        if (adapter == null) {
            return;
        }
        // 停止之前的动画事件
//        handler.removeCallbacks(animRunnable);
        post(() -> {
            // 在完成了Measure过程后，才能获得正确的尺寸
            // 加载ViewPager
            loadViewPager();
            // 加载index指示器
            loadIndicator();
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.INSTANCE.d("onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 关闭动画，防止内存泄露
        LogUtil.INSTANCE.d("onDetachedFromWindow");
        handler.removeCallbacks(animRunnable);
    }

    private VelocityTracker velocityTracker = VelocityTracker.obtain();
    private GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            LogUtil.INSTANCE.d("gestureDetector onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            LogUtil.INSTANCE.d("gestureDetector onShowPress");

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LogUtil.INSTANCE.d("gestureDetector onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            LogUtil.INSTANCE.d("gestureDetector onScroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            LogUtil.INSTANCE.d("gestureDetector onLongPress");

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            LogUtil.INSTANCE.d("gestureDetector onFling " + velocityX + " : " + velocityY);
            if (velocityX < 0) {
                // 向左滑动，显示右面的元素
                int nextIndex = viewPager.getCurrentItem() + 1;
                if (nextIndex >= getPagerCount()) {
                } else {
                    viewPager.setCurrentItem(nextIndex);
                }
            } else {
                // 向右滑动，显示左面的元素
                int nextIndex = viewPager.getCurrentItem() - 1;
                if (nextIndex < 0) {
                } else {
                    viewPager.setCurrentItem(nextIndex);
                }
            }
            return false;
        }
    });


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        LogUtil.INSTANCE.e("dispatchTouchEvent");

        lastTouchTime = System.currentTimeMillis();
        velocityTracker.addMovement(ev);
        velocityTracker.computeCurrentVelocity(1000);
        gestureDetector.onTouchEvent(ev);

        switch (ev.getAction()) {
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        LogUtil.INSTANCE.e("onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        LogUtil.INSTANCE.e("onTouchEvent");
        return super.onTouchEvent(event);
    }

    /**
     * 加载Index指示器
     */
    private void loadIndicator() {
        rg_indicator.removeAllViews();
        for (int i = 0; i < getPagerCount(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_indicator));
            radioButton.setButtonDrawable(null);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(indicatorWidth, indicatorHeight);
            rg_indicator.addView(radioButton, layoutParams);
            if (i == 0) {
                radioButton.setChecked(true);
            }
        }
        rg_indicator.invalidate();
    }

    /**
     * 加载ViewPager
     */
    private void loadViewPager() {
        pagerList.clear();
        pagerAdapter.notifyDataSetChanged();
        gridAllWidth = getMeasuredWidth() - marginSide * 2;
        gridAllHeight = getMeasuredHeight() - marginTop - marginBottom;
        itemWidth = gridAllWidth / adapter.getColumnCount();
        itemHeight = gridAllHeight;


        int groupCount = getPagerCount();
        LogUtil.INSTANCE.d("groupCount = " + groupCount);
        for (int i = 0; i < groupCount; i++) {
            GridLayout gridLayout = fillGridLayout(i);
            gridLayout.setBackgroundColor(Color.GREEN);
            gridLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            FrameLayout container = new FrameLayout(getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(marginSide, marginTop, marginSide, marginBottom);
            container.addView(gridLayout, params);
            pagerList.add(container);
        }
        pagerAdapter.notifyDataSetChanged();
    }

    private void doAnim() {

        // 执行动画
        if (!loopAnimEnabled) {
            return;
        }
        if (animRunnable == null) {
            animRunnable = new AnimRunnable();
        }
        handler.postDelayed(animRunnable, animTimeMills);
    }

    @NotNull
    private GridLayout fillGridLayout(int groupPosition) {
        GridLayout gridLayout = new GridLayout(getContext());
        gridLayout.setColumnCount(adapter.getColumnCount());
        gridLayout.setRowCount(1);


        int itemCount = getColItemCount(groupPosition);
        LogUtil.INSTANCE.d("itemCount = " + itemCount);
        for (int i = 0; i < itemCount; i++) {
            // GridView不会自动平分，需要指定宽度和高度
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(i));
            layoutParams.setGravity(Gravity.CENTER);
            layoutParams.width = itemWidth;
            layoutParams.height = itemHeight;

            View view = adapter.getView(groupPosition * adapter.getColumnCount() + i);
            gridLayout.addView(view, layoutParams);
        }
        return gridLayout;
    }

    /**
     * 获取指定的栏的item数
     *
     * @param groupPosition 栏index
     */
    private int getColItemCount(int groupPosition) {
        int residue = adapter.getCount() % adapter.getColumnCount();
        if (residue == 0) {
            return adapter.getColumnCount();
        } else if (groupPosition == getPagerCount() - 1) {
            return residue;
        } else {
            return adapter.getColumnCount();
        }
    }

    /**
     * 获取需要的Pager页数
     */
    private int getPagerCount() {
        // 余数
        int residue = adapter.getCount() % adapter.getColumnCount();
        // 商
        int value = adapter.getCount() / adapter.getColumnCount();
        return residue > 0 ? value + 1 : value;
    }

    private int getItemWidth() {
        return getMeasuredWidth() / adapter.getColumnCount();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
    }

    public static abstract class Adapter {
        private HorizontalPager pager;

        public abstract int getCount();

        public abstract View getView(int position);

        public abstract int getColumnCount();

        public void bindPagerView(HorizontalPager pager) {
            this.pager = pager;
        }

        public void notifyDataSetChanged() {
            pager.refresh();
        }
    }


    private class AnimRunnable implements Runnable {
        @Override
        public void run() {
            LogUtil.INSTANCE.d("anim run");
            if (!loopAnimEnabled) {
                LogUtil.INSTANCE.d("anim run 1");
                return;
            }
            if (System.currentTimeMillis() - lastTouchTime < animTimeMills) {
                doAnim();
                return;
            }
            LogUtil.INSTANCE.d("anim run 2");
            if (viewPager != null) {
                LogUtil.INSTANCE.d("anim run 3");
                int currentIndex = viewPager.getCurrentItem();
                LogUtil.INSTANCE.d("anim run currentIndex = " + currentIndex + ", count = " + getPagerCount());
                int nextIndex = currentIndex + 1;
                LogUtil.INSTANCE.d("anim run nextIndex = " + nextIndex);
                if (nextIndex >= getPagerCount()) {
                    LogUtil.INSTANCE.d("anim run nextIndex in");
                    nextIndex = 0;
                }
                LogUtil.INSTANCE.d("anim run nextIndex = " + nextIndex);
                viewPager.setCurrentItem(nextIndex, true);
                doAnim();
            }
        }
    }

    private class CusPagerAdapter extends PagerAdapter {
        private final List<ViewGroup> pagerList;

        CusPagerAdapter(List<ViewGroup> pagerList) {
            this.pagerList = pagerList;
        }

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ViewGroup gridLayout = pagerList.get(position);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(gridLayout, layoutParams);
            return gridLayout;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            // 覆写此方法，保证当notifyDataSetChanged时，所有View被销毁重建
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            LogUtil.INSTANCE.d("destroyItem " + position);
            container.removeView((View) object);
        }
    }

    private class CusScroller extends Scroller {
        private int timeMills = 600;

        public CusScroller(Context context) {
            super(context);
        }

        public CusScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public CusScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, timeMills);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, timeMills);
        }
    }
}

