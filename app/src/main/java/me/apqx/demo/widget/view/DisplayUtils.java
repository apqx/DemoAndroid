package me.apqx.demo.widget.view;

import android.content.Context;

public class DisplayUtils {
    /**
     * 将dp或dip转换为px
     */
    public static int dpToPx(Context context, float dpValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
    /**
     * 将px转换为dp或dip
     */
    public static int pxToDp(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
    /**
     * 将sp转换为px
     */
    public static int spToPx(Context context, float dpValue){
        final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(dpValue * fontScale + 0.5f);
    }
    /**
     * 将px转换为sp
     */
    public static int pxToSp(Context context, float pxValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5f);
    }

}
