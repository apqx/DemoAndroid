package me.apqx.demo.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import me.apqx.demo.R;
import me.apqx.libtools.view.DisplayUtil;

public class CusSwitcher extends LinearLayout {
    private int selectedTextColor;
    private int unselectedTextColor;

    private int selectedBgDrawableRes;
    private int unselectedBgDrawableRes;

    private TextView tv_left;
    private TextView tv_right;

    private boolean leftSelected;
    private OnSwitcherSelectListener listener;

    public CusSwitcher(Context context) {
        super(context);
    }

    public CusSwitcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusSwitcher(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int dp3 = DisplayUtil.INSTANCE.dpToPx(getContext(), 3);

        setBackgroundResource(R.drawable.bg_rg_tab);

        selectedTextColor = Color.BLACK;
        unselectedTextColor = Color.WHITE;

        selectedBgDrawableRes = R.drawable.bg_rb_selected;
        unselectedBgDrawableRes = R.drawable.bg_rb_unselected;

        // 加载2个横向的TextView作为tab，在点击时自动切换选中的tab
        setOrientation(LinearLayout.HORIZONTAL);
        tv_left = new TextView(getContext());
        tv_left.setPadding(dp3, dp3, dp3, dp3);
        tv_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        tv_left.setText("左");
        tv_left.setGravity(Gravity.CENTER);
        LayoutParams layoutParamsLeft = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        addView(tv_left, layoutParamsLeft);

        tv_right = new TextView(getContext());
        tv_right.setPadding(dp3, dp3, dp3, dp3);
        tv_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        tv_right.setText("右");
        tv_right.setGravity(Gravity.CENTER);
        LayoutParams layoutParamsRight = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        addView(tv_right, layoutParamsRight);

        showLeftSelected();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftSelected) {
                    showRightSelected();
                } else {
                    showLeftSelected();
                }
            }
        });
    }

    private void showLeftSelected() {
        leftSelected = true;
        tv_left.setTextColor(selectedTextColor);
        tv_left.setBackgroundResource(selectedBgDrawableRes);

        tv_right.setTextColor(unselectedTextColor);
        tv_right.setBackgroundResource(unselectedBgDrawableRes);
    }

    private void showRightSelected() {
        leftSelected = false;
        tv_left.setTextColor(unselectedTextColor);
        tv_left.setBackgroundResource(unselectedBgDrawableRes);

        tv_right.setTextColor(selectedTextColor);
        tv_right.setBackgroundResource(selectedBgDrawableRes);
    }

    /**
     * 设置左右标签要显示的文字
     */
    public void setTabText(String leftStr, String rightStr) {
        tv_left.setText(leftStr);
        tv_right.setText(rightStr);
    }

    /**
     * 设置选中的字体颜色，会立即刷新视图
     */
    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        if (leftSelected) {
            showLeftSelected();
        } else {
            showRightSelected();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    public interface OnSwitcherSelectListener {
        void onSwitchSelect(boolean left);
    }

    public void setOnSwitcherSelectListener(OnSwitcherSelectListener listener) {
        this.listener = listener;
    }
}
