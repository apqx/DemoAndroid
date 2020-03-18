package me.apqx.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerAdapter: RecyclerView.Adapter<SimpleRecyclerAdapter.CusViewHolder>() {
    private val list = ArrayList<String>()

    private lateinit var onItemClickListenerListener: OnItemClickListener

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

    inner class CusViewHolder: RecyclerView.ViewHolder {
        private val tv: TextView = itemView.findViewById(android.R.id.text1)

        constructor(itemView: View) : super(itemView) {
            itemView.setOnClickListener {
                if (this@SimpleRecyclerAdapter::onItemClickListenerListener.isInitialized) {
                    onItemClickListenerListener.onItemClick()
                }
            }
        }

        fun bindView(str: String) {
            tv.text = str
        }

    }

    public fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListenerListener = listener
    }


    public interface OnItemClickListener {
        fun onItemClick();
    }
}