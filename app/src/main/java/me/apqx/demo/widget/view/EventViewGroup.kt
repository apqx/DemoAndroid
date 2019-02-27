package me.apqx.demo.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import me.apqx.demo.LogUtil

class EventViewGroup : RelativeLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.d("$id onInterceptTouchEvent")
        val result = super.onInterceptTouchEvent(ev)
//        val result = false
        val curResult = result;
        LogUtil.d("$id onInterceptTouchEvent super.result = $result, cur.result = $curResult")
        return curResult
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtil.d("$id onTouchEvent")
        val result = super.onTouchEvent(event)
//        val result = false
        val curResult = result
        LogUtil.d("$id onTouchEvent super.result = $result, cur.result = $curResult")
        return curResult
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.d("$id dispatchTouchEvent")
        val result = super.dispatchTouchEvent(ev)
//        val result = false
        val curResult = result
        LogUtil.d("$id dispatchTouchEvent super.result = $result, cur.result = $curResult")
        return curResult
    }
}