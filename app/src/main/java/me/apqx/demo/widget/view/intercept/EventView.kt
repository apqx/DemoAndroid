package me.apqx.demo.widget.view.intercept

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import me.apqx.libbase.util.LogUtil

/**
 * 事件分发拦截：外部拦截法
 */
class EventView : ImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 用于判断滑动的触控坐标记录
     */
    private var xPoint = 0f
    private var yPoint = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 处理所有收到的事件
        var result = true
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtil.e("$id onTouchEvent  ACTION_DOWN")
                xPoint = event.rawX
                yPoint = event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                var offsetX = event.rawX - xPoint
                LogUtil.e("$id onTouchEvent  ACTION_MOVE offsetX = $offsetX")
                // 水平移动
                if (offsetX != 0f) {
                    offsetLeftAndRight(offsetX.toInt())
                    // 不允许父View再拦截本事件序列的后续事件
                    parent.requestDisallowInterceptTouchEvent(true)
                }
                xPoint = event.rawX
                yPoint = event.rawY
            }

            MotionEvent.ACTION_UP -> {
                LogUtil.e("$id onTouchEvent  ACTION_UP")
            }
        }
        LogUtil.e("$id onTouchEvent  $result")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var result = super.dispatchTouchEvent(ev)
        LogUtil.e("$id dispatchTouchEvent  $result")
        return result
    }
}