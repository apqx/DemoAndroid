package me.apqx.demo.widget.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemRecyclerBinding
import me.apqx.demo.widget.recycler.bean.DATA_TYPE_HORIZONTAL
import me.apqx.demo.widget.recycler.bean.DATA_TYPE_NORMAL
import me.apqx.demo.widget.recycler.bean.Student

class StudentAdapter(val list: MutableList<Student>) : RecyclerView.Adapter<StudentAdapter.CusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        val itemRecyclerBinding: ItemRecyclerBinding = if (viewType == DATA_TYPE_NORMAL) {
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.item_recycler, parent, false)
        } else {
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.item_recycler, parent, false)
        }
        return CusViewHolder(itemRecyclerBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].dataType == DATA_TYPE_NORMAL) DATA_TYPE_NORMAL else DATA_TYPE_HORIZONTAL
    }

    inner class CusViewHolder(val itemRecyclerBinding: ItemRecyclerBinding) : RecyclerView.ViewHolder(itemRecyclerBinding.root) {
        fun setItem(student: Student) {
            if (student.dataType == DATA_TYPE_NORMAL) {
                itemRecyclerBinding.student = student
            }
        }
    }
}