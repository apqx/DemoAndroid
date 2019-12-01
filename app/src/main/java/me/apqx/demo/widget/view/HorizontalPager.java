package me.apqx.demo.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.apqx.demo.LogUtil;

public class HorizontalPager extends FrameLayout {
    private ViewPager viewPager;
    private Adapter adapter;
    private List<GridLayout> pagerList = new ArrayList<>();
    private CusPagerAdapter pagerAdapter;

    private int itemPaddingDp = 10;

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
        viewPager = new ViewPager(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(viewPager, layoutParams);
        pagerAdapter = new CusPagerAdapter(pagerList);
        viewPager.setAdapter(pagerAdapter);
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        adapter.bindPagerView(this);
        refresh();
    }

    private void refresh() {
        if (adapter == null) {
            return;
        }
        post(() -> {
            // 在完成了Measure过程后，才能获得正确的尺寸
            pagerList.clear();

            int itemWidth = getItemWidth();
            int groupCount = getGroupCount();
            LogUtil.INSTANCE.d("groupCount = " + groupCount);
            for (int i = 0; i < groupCount; i++) {
                GridLayout gridLayout = fillGridLayout(i, itemWidth);
                gridLayout.setBackgroundColor(Color.GREEN);
                gridLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                pagerList.add(gridLayout);
            }

            pagerAdapter.notifyDataSetChanged();

        });
    }

    @NotNull
    private GridLayout fillGridLayout(int groupPosition, int itemWidth) {
        GridLayout gridLayout = new GridLayout(getContext());
        gridLayout.setColumnCount(adapter.getColumnCount());
        gridLayout.setRowCount(1);

        int itemCount = getColItemCount(groupPosition);
        LogUtil.INSTANCE.d("itemCount = " + itemCount);
            int margins = DisplayUtils.dpToPx(getContext(), itemPaddingDp);
        for (int i = 0; i < itemCount; i++) {
            // GridView不会自动平分，需要指定宽度和高度
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(i));
            layoutParams.setGravity(Gravity.CENTER);
            layoutParams.width = itemWidth - 2 * margins;
            layoutParams.height = getMeasuredHeight() - 2 * margins;
            layoutParams.setMargins(margins, margins, margins, margins);

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
        } else if (groupPosition == getGroupCount() - 1) {
            return residue;
        } else {
            return adapter.getColumnCount();
        }
    }

    /**
     * 获取需要的Pager页数
     */
    private int getGroupCount() {
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



    private class CusPagerAdapter extends PagerAdapter {
        private final List<GridLayout> pagerList;

        CusPagerAdapter(List<GridLayout> pagerList) {
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
            GridLayout gridLayout = pagerList.get(position);
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
            container.removeView(pagerList.get(position));
        }
    }
}

