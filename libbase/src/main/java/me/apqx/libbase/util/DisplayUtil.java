package me.apqx.libbase.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class DisplayUtil {
    /**
     * 将dp或dip转换为px
     */
    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px转换为dp或dip
     */
    public static int pxToDp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp转换为px
     */
    public static int spToPx(Context context, float dpValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * fontScale + 0.5f);
    }

    /**
     * 将px转换为sp
     */
    public static int pxToSp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 把半角字符切换为全角字符
     */
    public static String halfToFull(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 半角空格比较特殊
            if (chars[i] == 32) {
                chars[i] = (char) 12288;
                continue;
            }
            // 其他符号都转换为全角
            if (chars[i] > 32 && chars[i] < 127)
                chars[i] = (char) (chars[i] + 65248);
        }
        return new String(chars);
    }

    /**
     * 把全角字符切换为半角字符
     */
    public static String fullToHalf(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 半角空格比较特殊
            if (chars[i] == 12288) {
                chars[i] = (char) 32;
                continue;
            }
            // 其他符号都转换为全角
            if (chars[i] > 65280 && chars[i] < 65375)
                chars[i] = (char) (chars[i] - 65248);
        }
        return new String(chars);
    }

    /**
     * 设置指定Activity的状态栏透明显示
     *
     * @param darkStatusIcon 是否显示深色的状态栏图标、文字
     */
    public static void setStatusBarTransparent(Activity activity, boolean darkStatusIcon) {
        setStatusBarTransparent(activity);
        setStatusDarkIcon(activity, darkStatusIcon);
    }

    /**
     * 在Activity运行时，设置状态栏为透明，必须设置Theme#windowTranslucentStatus
     * 安卓5.0 SDK21及以上有效
     */
    public static void setStatusBarTransparent(Activity activity) {
        LogUtil.INSTANCE.i("setStatusBarTransparent " + activity.getClass().getSimpleName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 添加Flag
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 在Activity运行时，动态设置状态栏颜色，不需要设置Theme
     * 安卓5.0 SDK21及以上有效
     */
    public static void setStatusBarColor(Activity activity, int color) {
        LogUtil.INSTANCE.i("setStatusBarColor " + activity.getClass().getSimpleName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.setStatusBarColor(color);
        }
    }

    /**
     * 在Activity运行时，动态设置状态栏图标颜色，不需要设置Theme
     * 安卓6.0 SDK23及以上有效，在5.0~6.0之间的设备，无法设置状态栏图标颜色，如果因为状态栏透明显示，需要设置深色图标，避免状态栏一片白，
     * 可以{@link DisplayUtil#setStatusBarColor setStatusBarColor}给状态栏设置一个深色的背景
     *
     * @param darkStatusIcon 是否显示深色的状态栏图标、文字
     */
    public static void setStatusDarkIcon(Activity activity, boolean darkStatusIcon) {
        LogUtil.INSTANCE.i("setStatusDarkIcon " + activity.getClass().getSimpleName() + ", "
                + "darkStatusIcon = " + darkStatusIcon);
        Window window = activity.getWindow();
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkStatusIcon) {
                // 添加Flag
                systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                // 删除Flag
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    /**
     * 对于一个32位二进制，指定的位是否为1
     *
     * @param bits       原始数据段
     * @param targetBits 目标数据段
     */
    private static boolean bitAlreadyEnable(int bits, int targetBits) {
        return (bits & targetBits) == targetBits;
    }


    /**
     * 打印View层级
     */
    public static void listViews(View view, int level) {
        if (view instanceof ViewGroup) {
            LogUtil.INSTANCE.d(getLevelSpace(level) + "|-" + view + "\\");
            int newLevel = level + 1;
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                listViews(((ViewGroup) view).getChildAt(i), newLevel);
            }
        } else {
            LogUtil.INSTANCE.d(getLevelSpace(level) + "|-" + view);
        }
    }

    private static String getLevelSpace(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("_");
        }
        return sb.toString();
    }

    /**
     * 隐藏输入法
     * @param view 与弹出输入法的View在同一个Window中的其它任何一个View
     */
    public static void hideSoftInputKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 清除该View和其子View的所有EditText的焦点
     * @param view View或ViewGroup
     */
    public static void clearEditFocus(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                clearEditFocus(vg.getChildAt(i));
            }
        } else {
            if (view instanceof EditText) {
                EditText et = (EditText) view;
                et.setFocusableInTouchMode(false);
                et.clearFocus();
                et.setFocusableInTouchMode(true);
            }
        }
    }

}
