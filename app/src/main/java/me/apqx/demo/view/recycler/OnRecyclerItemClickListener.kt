package me.apqx.demo.view.recycler

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

abstract class OnRecyclerItemClickListener(val context: Context) : RecyclerView.OnItemTouchListener {
    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true
        }

    })

    abstract fun onItemClick(position: Int)

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val itemView = rv.findChildViewUnder(e.x, e.y) ?: return false
        val position = rv.getChildAdapterPosition(itemView)
        if (position != -1 && gestureDetector.onTouchEvent(e)) {
            onItemClick(position)
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}