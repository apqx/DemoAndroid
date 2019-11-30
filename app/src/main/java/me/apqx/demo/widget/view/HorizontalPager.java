package me.apqx.demo.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import me.apqx.demo.LogUtil;

public class HorizontalPager extends FrameLayout {
    private ViewPager viewPager;
    private Adapter adapter;

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
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        refresh();
    }

    private void refresh() {
        if (adapter == null) {
            return;
        }
        post(() -> {
            int itemWidth = getItemWidth();
            int groupCount = getGroupCount();
            LogUtil.INSTANCE.d("groupCount = " + groupCount);
            for (int i = 0; i < groupCount; i++) {
                GridLayout gridView = fillGridView(i, itemWidth);
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                addView(gridView, layoutParams);
            }
        });
    }

    @NotNull
    private GridLayout fillGridView(int groupPosition, int itemWidth) {
        GridLayout gridView = new GridLayout(getContext());
        gridView.setColumnCount(adapter.getColumnCount());
        gridView.setRowCount(1);

        int itemViewCount = getColItemCount(groupPosition);
        LogUtil.INSTANCE.d("itemViewCount = " + itemViewCount);
        for (int i = 0; i < itemViewCount; i++) {
            // GridView会自动平分
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(40, 40, 40, 40);

            gridView.addView(adapter.getView(groupPosition * adapter.getColumnCount() + i), layoutParams);
        }
        return gridView;
    }

    /**
     * 获取指定的栏的item数
     *
     * @param groupPosition 栏index
     */
    private int getColItemCount(int groupPosition) {
        int residue = adapter.getCount() % adapter.getColumnCount();
        int value = adapter.getCount() / adapter.getColumnCount();
        if (residue == 0) {
            return value;
        } else if (groupPosition == getGroupCount() - 1) {
            return residue;
        } else {
            return value;
        }
    }

    private int getGroupCount() {
        int residue = adapter.getCount() % adapter.getColumnCount();
        int value = adapter.getCount() / adapter.getColumnCount();
        return residue > 0 ? value + 1 : value;
    }

    private int getItemWidth() {
        return getMeasuredWidth() / adapter.getColumnCount();
    }

    public static abstract class Adapter {
        public abstract int getCount();

        public abstract View getView(int position);

        public abstract int getColumnCount();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.INSTANCE.d("HorizontalPager onMeasure");
    }
}
