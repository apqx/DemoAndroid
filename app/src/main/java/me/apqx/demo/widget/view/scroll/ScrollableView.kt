package me.apqx.demo.widget.view.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Scroller
import androidx.constraintlayout.widget.ConstraintLayout
import me.apqx.demo.LogUtil
import java.util.jar.Attributes

/**
 * 跟随手指移动的View，5种移动方式
 */
class ScrollableView : ImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val scroller = Scroller(context)

    private var curX = 0f
    private var curY = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var offsetX = 0f
    private var offsetY = 0f



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        curX = event!!.rawX
        curY = event!!.rawY
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = curX
                lastY = curY
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = curX - lastX
                offsetY = curY - lastY
                // 进行重新定位，高频的重定位将表现为滑动
                location4(offsetX.toInt(), offsetY.toInt())

                lastX = curX
                lastY = curY

            }

            MotionEvent.ACTION_UP -> {
                // 当手指移开的时候，平滑移动到原点
                val parentView = parent as View
                scroller.startScroll(parentView.scrollX, parentView.scrollY, -parentView.scrollX, -parentView.scrollY, 1000)
                invalidate()
            }
        }
        return true
    }

    private fun location1(offsetX: Int, offsetY: Int) {
        layout(left + offsetX, top + offsetY, right + offsetX, bottom + offsetY)
    }

    private fun location2(offsetX: Int, offsetY: Int) {
        offsetLeftAndRight(offsetX)
        offsetTopAndBottom(offsetY)
    }

    /**
     * 使用LayoutParams的方式不一定对所有ViewGroup都有效
     */
    private fun location3(offsetX: Int, offsetY: Int) {
        val layout = layoutParams as ConstraintLayout.LayoutParams
        layout.setMargins(layout.leftMargin + offsetX, layout.topMargin + offsetY, layout.rightMargin, layout.bottomMargin)
        layoutParams = layout
    }

    private fun location4(offsetX: Int, offsetY: Int) {
        val parentView = parent as View
        parentView.scrollBy(-offsetX, - offsetY)
    }

    private fun location5(offsetX: Int, offsetY: Int) {
        val parentView = parent as View
        LogUtil.d("offsetX = $offsetX, offsetY = $offsetY")
        parentView.scrollTo(parentView.scrollX - offsetX, parentView.scrollY - offsetY)
    }

    override fun computeScroll() {
        val parentView = parent as View
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            parentView.scrollTo(scroller.currX, scroller.currY)
            postInvalidate()
        }
    }
}