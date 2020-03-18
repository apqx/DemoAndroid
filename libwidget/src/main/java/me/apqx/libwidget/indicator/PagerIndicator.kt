package me.apqx.libwidget.indicator

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import me.apqx.libbase.util.LogUtil

class PagerIndicator : LinearLayout {

    constructor(context: Context?) : super(context) {
        LogUtil.d("PagerIndicator constructor1")
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LogUtil.d("PagerIndicator constructor2")

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LogUtil.d("PagerIndicator constructor3")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onNestedFling(target: View?, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return super.onNestedFling(target, velocityX, velocityY, consumed)
    }

    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    }

    override fun onNestedScroll(target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }
}