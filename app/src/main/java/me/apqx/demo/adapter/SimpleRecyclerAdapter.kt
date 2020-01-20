package me.apqx.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerAdapter: RecyclerView.Adapter<SimpleRecyclerAdapter.CusViewHolder>() {
    private val list = ArrayList<String>()

    public fun setData(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        return CusViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    inner class CusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tv: TextView = itemView.findViewById(android.R.id.text1)

        fun bindView(str: String) {
            tv.text = str
        }

    }
}