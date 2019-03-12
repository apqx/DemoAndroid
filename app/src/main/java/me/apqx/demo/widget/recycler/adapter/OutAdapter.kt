package me.apqx.demo.widget.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemRecyclerListBinding
import me.apqx.demo.databinding.ItemRecyclerListManBinding
import me.apqx.demo.databinding.ItemRecyclerManBinding
import me.apqx.demo.widget.recycler.bean.DATA_TYPE_HORIZONTAL
import me.apqx.demo.widget.recycler.bean.DATA_TYPE_NORMAL
import me.apqx.demo.widget.recycler.bean.Man

/**
 * 外层竖向RecyclerView的Adapter
 */
class OutAdapter(val list: MutableList<Man>) : RecyclerView.Adapter<OutAdapter.CusViewHolder>() {
    /**
     * 普通item绑定
     */
    private lateinit var itemRecyclerManBinding: ItemRecyclerManBinding
    /**
     * 横向滑动List布局绑定
     */
    private lateinit var itemRecyclerListBinding: ItemRecyclerListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        LogUtil.d("onCreateViewHolder type = $viewType")
        val viewHolder: CusViewHolder
        if (viewType == DATA_TYPE_NORMAL) {
            val binding = DataBindingUtil.inflate<ItemRecyclerManBinding>(LayoutInflater.from(parent.context),
                    // 正常item
                    R.layout.item_recycler_man, parent, false)
            viewHolder = CusViewHolder(binding.root)
            viewHolder.itemRecyclerManBinding = binding
        } else {
            val binding = DataBindingUtil.inflate<ItemRecyclerListBinding>(LayoutInflater.from(parent.context),
                    // 嵌套的横向滑动RecyclerView item
                    R.layout.item_recycler_list, parent, false)
            viewHolder = CusViewHolder(binding.root)
            viewHolder.itemRecyclerListBinding = binding
            viewHolder.initList(list, parent.context)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_TYPE_NORMAL) {
            holder.bindItemNormal(list[position])
        } else{
            holder.bindItemList(list)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].dataType
    }

    class CusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var itemRecyclerManBinding: ItemRecyclerManBinding

        lateinit var itemRecyclerListBinding: ItemRecyclerListBinding

        private lateinit var adapter: InnerAdapter

        fun bindItemNormal(man: Man) {
            itemRecyclerManBinding.man = man
        }

        fun initList(list: List<Man>, context: Context) {
            adapter = InnerAdapter(list)
            itemRecyclerListBinding.rvHorizontal.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            itemRecyclerListBinding.rvHorizontal.adapter = adapter
        }

        fun bindItemList(list: List<Man>) {
            // 复用RecyclerView加载新数据
        }
    }


}