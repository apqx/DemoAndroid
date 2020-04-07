package me.apqx.demo.view.adapter

import android.content.Context
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_simple_list_man.view.*
import me.apqx.demo.R
import me.apqx.libbase.util.LogUtil

class RecyclerListAdapter : RecyclerView.Adapter<RecyclerListAdapter.ListViewHolder>() {

    private val list = ArrayList<String>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple_list_man, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindData(list[position], position)
    }

    fun setData(list: java.util.ArrayList<String>) {
        LogUtil.d("${javaClass.simpleName} setData $list")
        this.list.clear()
        this.list.addAll(list)
    }

    public fun getData(): MutableList<String> {
        return list
    }

    inner class ListViewHolder : RecyclerView.ViewHolder {
        private var itemPosition: Int = 0

        constructor(itemView: View) : super(itemView) {
            itemView.setOnClickListener {

            }
            itemView.setOnLongClickListener{
                val vibrator = itemView.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(100)
                true
            }

        }

        fun bindData(s: String, position: Int) {
            this.itemPosition = position
            // Kotlin可以直接访问ViewHolder中的View，自动import kotlinx.android.synthetic
            itemView.tv_title.text = s
            itemView.iv_icon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}