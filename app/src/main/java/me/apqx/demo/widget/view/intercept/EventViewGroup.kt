package me.apqx.demo.widget.view.intercept

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import me.apqx.demo.LogUtil

/**
 * 事件分发拦截：外部拦截法
 */
class EventViewGroup : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 用于判断是否拦截的触控坐标记录
     */
    private var xIntercept = 0f
    private var yIntercept = 0f

    /**
     * 用于判断滑动的触控坐标记录
     */
    private var xPoint = 0f
    private var yPoint = 0f

    /**
     * 是否要拦截，如果ViewGroup对第一次滑动事件没有拦截，则同序列的后续事件都不应该拦截
     */
    private var intercept = true

    // ViewGroup判断是否要拦截事件，否则所有事件均交给View处理
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var result = when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtil.e("$id onTouchEvent  ACTION_DOWN")
                // 记录ACTION_DOWN事件坐标
                xIntercept = ev.rawX
                yIntercept = ev.rawY

                xPoint = ev.rawX
                yPoint = ev.rawY
                intercept = true
                // 不拦截ACTION_DOWN事件
                false
            }

            MotionEvent.ACTION_MOVE -> {
                LogUtil.d("$id onInterceptTouchEvent  ACTION_MOVE")
                // 只拦截竖向事件
                val moveY = ev.rawY - yIntercept
                val moveX = ev.rawX - xIntercept
                xIntercept = ev.rawX
                yIntercept = ev.rawY
                if (moveX == moveY && moveX == 0f) {
                    // 收到重复事件，不拦截
                    false
                } else if (Math.abs(moveY) >= Math.abs(moveX)) {
                    // 垂直距离大于水平距离，拦截
                    true
                } else {
                    // 如果第一次滑动不拦截事件，则事件已交给子View处理，不再拦截同序列事件
                    intercept = false
                    // 其余情况不拦截
                    false
                }
            }

            MotionEvent.ACTION_UP -> {
                LogUtil.d("$id onInterceptTouchEvent  ACTION_UP")
                false
            }

            else -> false
        }
        if (!intercept) {
            // 是否拦截同序列事件
            result = false
        }
        LogUtil.d("$id onInterceptTouchEvent  $result $intercept")
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 处理所有收到的事件
        var result = true
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtil.d("$id onTouchEvent  ACTION_DOWN")
            }

            MotionEvent.ACTION_MOVE -> {
                var offsetY = event.rawY - yPoint
                var offsetX = event.rawX - xPoint
                LogUtil.d("$id onTouchEvent  ACTION_MOVE offsetX = $offsetX offsetY = $offsetY")
                // 垂直移动
                offsetTopAndBottom(offsetY.toInt())
                yPoint = event.rawY
                xPoint = event.rawX
            }

            MotionEvent.ACTION_UP -> {
                LogUtil.d("$id onTouchEvent  ACTION_UP")
            }
        }

        LogUtil.d("$id onTouchEvent  $result")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.d("$id event.rawX = ${ev?.rawX} event.rawY = ${ev?.rawY}")
        var result = super.dispatchTouchEvent(ev)
        LogUtil.d("$id dispatchTouchEvent  $result")
        return result
    }
}