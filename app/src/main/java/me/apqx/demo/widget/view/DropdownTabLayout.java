package me.apqx.demo.widget.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import me.apqx.demo.LogUtil;
import me.apqx.demo.R;

public class DropdownTabLayout extends FrameLayout {
    private TabLayout tabLayout;
    private ImageView iv_dropdown;
    private ViewGroup vg_more;
    private TextView tv_more;

    private List<TabBean<String>> tabList;
    private PopupWindow popupWindow;
    private OnTabSelectListener<String> onTabSelectListener;

    private int colorBlack;
    private int colorBlue;
    private int colorTransparent;

    private static final int MAX_TAB_COUNT = 5;
    private static int selectIndex;
    private static boolean recreate;

    private TextView selectedDropdownItem;
    private OnClickListener onMoreTabClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!(view instanceof TextView)) {
                return;
            }
            showDropTabNotClick();
            selectedDropdownItem = (TextView) view;
            LogUtil.INSTANCE.d("dropdown tab click: " + view.getTag() + " : " + ((TextView) view).getText().toString());
            // 点击的item字体变为蓝色
            selectedDropdownItem.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));

            popupWindow.dismiss();
            TabBean<String> tabBean = tabList.get((Integer) view.getTag());
            if (onTabSelectListener != null) {
                onTabSelectListener.onTabClick(tabBean.getData());
            }
            // “更多”显示点击的下拉标签项
            showDropTabClick(tabBean.getData());
        }
    };


    /**
     * 显示点击的下拉列表里的tag
     */
    private void showDropTabClick(String tag) {
        tv_more.setText(tag);
        tv_more.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        setTabLayoutUnselectable();
    }

    /**
     * 显示下拉列表的tab没有被点击的状态
     */
    private void showDropTabNotClick() {
        tv_more.setText("更多");
        tv_more.setTextColor(getResources().getColor(android.R.color.black));
        if (selectedDropdownItem != null)
            selectedDropdownItem.setTextColor(getResources().getColor(android.R.color.black));
    }

    /**
     * 设置tabLayout为可以选择的显示状态
     */
    private void setTabLayoutSelectable() {
        tabLayout.setTabTextColors(colorBlack, colorBlue);
        tabLayout.setSelectedTabIndicatorColor(colorBlue);
        tv_more.setTextColor(colorBlack);
    }

    /**
     * 设置tabLayout为不可以选择的显示状态
     */
    private void setTabLayoutUnselectable() {
        tabLayout.setTabTextColors(colorBlack, colorBlack);
        tabLayout.setSelectedTabIndicatorColor(colorTransparent);
    }


    public DropdownTabLayout(Context context) {
        super(context);
        init();
    }

    public DropdownTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DropdownTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        LogUtil.INSTANCE.d("DropdownTabLayout init recreate = " + recreate);


        colorBlack = Color.BLACK;
        colorBlue = Color.parseColor("#2F74E9");
        colorTransparent = getResources().getColor(android.R.color.transparent);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_dropdown_tab, this, false);
        tabLayout = view.findViewById(R.id.tl_tab);
        iv_dropdown = view.findViewById(R.id.iv_dropdown);
        vg_more = view.findViewById(R.id.ll_more);
        tv_more = view.findViewById(R.id.tv_tab_more);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, layoutParams);

        setTabLayoutSelectable();

        post(new Runnable() {
            @Override
            public void run() {
                if (recreate) {
                    LogUtil.INSTANCE.d("recreate, restore select " + selectIndex);
                    tabLayout.getTabAt(selectIndex).select();
                } else {
                    recreate = true;
                }
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectIndex = (int) tab.getTag();
                LogUtil.INSTANCE.d("tab click: " + tab.getTag() + " : " + tab.getText() + " index = " + selectIndex);
                showDropTabNotClick();
                setTabLayoutSelectable();
                TabBean<String> tabBean = tabList.get(selectIndex);
                if (onTabSelectListener != null) {
                    onTabSelectListener.onTabClick(tabBean.getData());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vg_more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击了“更多”按钮，弹出下拉列表，启动一个旋转动画
                LogUtil.INSTANCE.d("dropdown click");
                expandIconAnimExpand();
                if (popupWindow == null) {
                    LinearLayout linearLayout = fillDropdownList();
                    popupWindow = new PopupWindow(linearLayout, vg_more.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                    popupWindow.setFocusable(true);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            expandIconAnimResume();
                        }
                    });

                }
                popupWindow.showAsDropDown(vg_more);
            }
        });
    }

    /**
     * 执行一个扩展按钮图表的180°旋转动画
     */
    private void expandIconAnimExpand() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_dropdown, "rotation", 0f, 180f);
        objectAnimator.setDuration(400);
        objectAnimator.start();
    }

    /**
     * 执行一个扩展按钮图表的180°旋转动画，恢复原状
     */
    private void expandIconAnimResume() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_dropdown, "rotation", 180f, 0f);
        objectAnimator.setDuration(400);
        objectAnimator.start();
    }

    /**
     * 根据传入的数据，填充下拉列表的UI视图
     */
    private LinearLayout fillDropdownList() {
        int dp5 = DisplayUtils.dpToPx(getContext(), 5);
        int dp2 = DisplayUtils.dpToPx(getContext(), 2);
        int dp10 = DisplayUtils.dpToPx(getContext(), 10);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(dp5, 0, dp5, 0);
        linearLayout.setBackgroundColor(Color.parseColor("#F0F0F0"));
        if (tabList.size() <= MAX_TAB_COUNT) {
            // 标签可以在TabLayout中全部显示，下拉列表为空
        } else {
            // 填充下拉列表
            for (int i = MAX_TAB_COUNT; i < tabList.size(); i++) {
                TabBean<String> tabBean = tabList.get(i);
                TextView textView = new TextView(getContext());
                textView.setOnClickListener(onMoreTabClickListener);
                textView.setText(tabBean.getTabStr());
                textView.setTextColor(colorBlack);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                textView.setGravity(Gravity.CENTER);
                textView.setTag(i);
                textView.setMaxLines(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , DisplayUtils.dpToPx(getContext(), 30));
                layoutParams.topMargin = dp2;
                layoutParams.bottomMargin = dp2;
                linearLayout.addView(textView, layoutParams);
            }
        }
        // 添加一个编辑按钮
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.edit);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.topMargin = dp5;
        layoutParams.bottomMargin = dp10;
        linearLayout.addView(imageView, layoutParams);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击了编辑按钮
                if (onTabSelectListener != null) {
                    onTabSelectListener.onEditClick();
                }
                popupWindow.dismiss();
            }
        });
        return linearLayout;
    }

    /**
     * 设置标签数据，可以用于刷新，前6个标签显示在tab里，其它标签显示在下拉列表里
     *
     * @param tabList 固定显示的标签
     */
    public void setData(List<TabBean<String>> tabList) {
        this.tabList = tabList;
        if (tabList == null) return;
        // 横向的tab
        if (tabList.size() >= MAX_TAB_COUNT) {
            // 当标签数大于MAX_TAB_COUNT时，tabLayout的每一个tab平分总宽度
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        tabLayout.removeAllTabs();
        for (int i = 0; i < MAX_TAB_COUNT; i++) {
            if (i >= tabList.size()) break;
            TabBean<String> tabBean = tabList.get(i);
            tabLayout.addTab(tabLayout.newTab().setText(tabBean.getTabStr()).setTag(i));
        }
        setTabLayoutSelectable();
        // 下拉列表的tab
        if (popupWindow == null) {
            // 下拉列表还没有加载，不刷新数据
            return;
        }
        LinearLayout linearLayout = fillDropdownList();
        popupWindow.setContentView(linearLayout);
        showDropTabNotClick();
    }

    /**
     * 设置标签点击监听器
     */
    public void setOnTabSelectListener(OnTabSelectListener<String> listener) {
        this.onTabSelectListener = listener;
    }

}
