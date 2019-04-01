package me.apqx.demo.widget.view.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import me.apqx.demo.LogUtil
import java.util.jar.Attributes

class ScalableView : ImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var curX = 0f
    var curY = 0f
    var lastX = 0f
    var lastY = 0f
    var offsetX = 0f
    var offsetY = 0f
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
                location5(offsetX.toInt(), offsetY.toInt())

                lastX = curX
                lastY = curY

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
        (parent as View).scrollBy(-offsetX, - offsetY)
    }

    private fun location5(offsetX: Int, offsetY: Int) {
        LogUtil.d("offsetX = $offsetX, offsetY = $offsetY")
        val parentView = parent as View
        parentView.scrollTo(parentView.x.toInt() + offsetX, parentView.y.toInt() + offsetY)
    }
}