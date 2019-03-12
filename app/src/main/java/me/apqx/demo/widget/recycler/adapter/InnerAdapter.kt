package me.apqx.demo.widget.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemRecyclerListManBinding
import me.apqx.demo.widget.recycler.bean.Man

/**
 * 内层横向RecyclerView的Adapter
 */
class InnerAdapter(private val list: List<Man>) : RecyclerView.Adapter<InnerAdapter.CusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        val databinding = DataBindingUtil.inflate<ItemRecyclerListManBinding>(LayoutInflater.from(parent.context),
                R.layout.item_recycler_list_man, parent, false)
        return CusViewHolder(databinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    inner class CusViewHolder(private val itemBinding: ItemRecyclerListManBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun setItem(man: Man) {
            itemBinding.man = man
        }

    }
}