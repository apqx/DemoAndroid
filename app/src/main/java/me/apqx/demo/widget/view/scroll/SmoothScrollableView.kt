package me.apqx.demo.widget.view.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Scroller
import me.apqx.libtools.log.LogUtil


/**
 * 使用Scroller进行平滑移动
 */
class SmoothScrollableView : ImageView {
    constructor(context: Context) : super(context) {
//        scroller = Scroller(context)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        scroller = Scroller(context)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        scroller = Scroller(context)
    }

    private val scroller: Scroller = Scroller(context)


    private fun smoothScrollTo(destX: Int, destY: Int) {
        LogUtil.d("smoothScrollTo $destX,$destY")
        var offsetX = -scrollX + destX
        var offsetY = -scrollY + destY
        scroller.startScroll(scrollX, scrollY, -offsetX, - offsetY, 1000)
        invalidate()
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            (parent as View).scrollTo(scroller.currX, scroller.currY)
            postInvalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                smoothScrollTo(x.toInt() + 150, y.toInt() + 150)
            }
        }
        return true
    }
}