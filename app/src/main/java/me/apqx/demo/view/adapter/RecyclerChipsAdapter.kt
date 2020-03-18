package me.apqx.demo.view.adapter

import android.content.Context
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chips.view.*
import me.apqx.demo.R
import me.apqx.libbase.util.LogUtil

class RecyclerChipsAdapter : RecyclerView.Adapter<RecyclerChipsAdapter.ChipsViewHolder>() {

    private val list = ArrayList<String>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chips, parent, false)
        return ChipsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChipsViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun setData(list: java.util.ArrayList<String>) {
        LogUtil.d("${javaClass.simpleName} setData $list")
        this.list.clear()
        this.list.addAll(list)
    }

    public fun getData(): List<String> {
        return list
    }

    class ChipsViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {

            itemView.setOnLongClickListener{
                val vibrator = itemView.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(100)
                true
            }
        }

        fun bindData(s: String) {
            // Kotlin可以直接访问ViewHolder中的View，自动import kotlinx.android.synthetic
            itemView.tv_chips_title.text = s
        }
    }
}