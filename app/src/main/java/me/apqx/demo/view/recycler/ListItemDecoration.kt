package me.apqx.demo.view.recycler

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.apqx.libtools.log.LogUtil


/**
 *
 */
class ListItemDecoration : RecyclerView.ItemDecoration() {
    val titleHeight = 300
    val paint by lazy {
        Paint().apply {
            color = Color.RED
        }
    }

    /**
     * 当滑动屏幕，View移动时执行，每移动一点点，就会执行一次，执行的非常频繁
     * onDraw和onDrawOver针对的是RecyclerView本身
     * 在itemView下层绘制
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        // 获取当前可见的View数量
        val visibleItemCount = parent.childCount
        for (i in 0 until visibleItemCount) {
            // 获取在可见的View列表中，指定位置的View
            val childView = parent.getChildAt(i)
            // 获取该View在整个数据列表中的位置，然后就可以拿到该View对应的数据了
            val adapterPosition = parent.getChildAdapterPosition(childView)
            // 找到目标位置的Item
            if (adapterPosition == 0) {
                // 这里的绝对坐标，以RecyclerView的左上角为原点
                // 当给View用getItemOffsets加了Margin后，这个坐标就是加了外边距位置移动后的坐标
                // 当滚动时，坐标值也会随位置的变化而变化，只要知道在getItemOffsets中设置的Margin
                // 就可以确定在滚动时整个item的各个区域的实时坐标，相对于父RecyclerView
                LogUtil.d("onDraw child.getY = " + childView.y)
                // getTop和getY返回值是一样的
                LogUtil.d("onDraw child.getTop = " + childView.top)

                val titleTop = childView.top - titleHeight

                c.drawRect(0F, titleTop.toFloat(), childView.width.toFloat(), (titleTop + titleHeight).toFloat(), paint)
            }
        }

    }

    /**
     * 当滑动屏幕，View移动时执行，每移动一点点，就会执行一次，执行的非常频繁
     * onDraw和onDrawOver针对的是RecyclerView本身
     * 在ItemView上层绘制
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    /**
     * 当有一个新的View滑入可见范围时执行，给这个新的子View设置外边距
     * getItemOffsets针对的是每一个itemView
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 获取在Adapter数据源中的位置
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            // 设置这个Item的顶部外边距，同时这个Item的位置相对外边距移动，但尺寸不变
            outRect.top = titleHeight
        }
    }
}