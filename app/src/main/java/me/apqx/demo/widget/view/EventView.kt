package me.apqx.demo.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import me.apqx.demo.LogUtil

class EventView : ImageView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtil.e("$id onTouchEvent")
        val result = super.onTouchEvent(event)
//        val result = false
        val curResult = result
        LogUtil.e("$id onTouchEvent super.result = $result, cur.result = $curResult")
        return curResult
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.e("$id dispatchTouchEvent")
        val result = super.dispatchTouchEvent(ev)
//        val result = false
        val curResult = result
        LogUtil.e("$id dispatchTouchEvent super.result = $result, cur.result = $curResult")
        return curResult
    }
}