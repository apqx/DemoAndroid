package me.apqx.demo.view.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import me.apqx.libtools.log.LogUtil

import java.util.*

class ListItemTouchHelperCallback : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
        or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        , ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        // 获取viewHolder在当前Adapter数据源列表中的我位置
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        if (toPosition == 0 || fromPosition == 0) {
            return false
        }
        val adapter = recyclerView.adapter as RecyclerListAdapter
        val list = adapter.getData()
        if (fromPosition < toPosition) {
            // 分别把中间所有的item的位置重新交换
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(list, i, i - 1)
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition)
        LogUtil.d("onMoved = $list")
        return true
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}