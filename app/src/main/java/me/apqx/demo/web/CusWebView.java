package me.apqx.demo.web;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

import me.apqx.demo.LogUtil;

public class CusWebView extends WebView {
    private final int paddingTop = 50;
    private Handler handler = new Handler();

    public CusWebView(Context context) {
        super(context);
//        init();
    }

    public CusWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    public CusWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }
    private void init() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                offsetTopAndBottom(paddingTop);
                layout(0, paddingTop, getWidth(), getHeight() + paddingTop);
                LogUtil.INSTANCE.d("paddingTop = " + paddingTop);
            }
        }, 200);
    }

    int x;
    int y;
    int lastX;
    int lastY;
    int offsetX;
    int offsetY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 此时, x, y是相对于屏幕左上角的绝对坐标
        x = (int) ev.getRawX();
        y = (int) ev.getRawY();
        LogUtil.INSTANCE.d("getScrollY = " + getScrollY() + ", getY = " + getY());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = x - lastX;
                offsetY = y - lastY;
                // 在这里改变View的位置实现滑动
                // 向上滚动，scrollY > 0

                if (offsetY > 0) {
                    // 向下滑
                    if (getY() < paddingTop && getScrollY() == 0) {
                        offsetTopAndBottom(offsetY);
                    }
                } else if (offsetY < 0) {
                    if (getY() > 0) {
                        offsetTopAndBottom(offsetY);
                    }
                }

                if (getY() > paddingTop) {
                    offsetTopAndBottom((int) (paddingTop - getY()));
                }

                if (getY() < 0) {
                    offsetTopAndBottom((int) (-getY()));
                }


                lastX = x;
                lastY = y;
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        LogUtil.INSTANCE.d("getScrollYT = " + getScrollY());
        return super.onTouchEvent(event);
    }
}
