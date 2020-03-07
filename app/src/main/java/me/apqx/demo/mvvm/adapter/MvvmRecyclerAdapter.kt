package me.apqx.demo.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_student.view.*
import me.apqx.demo.R
import me.apqx.demo.mvvm.data.Student

class MvvmRecyclerAdapter : ListAdapter<Student, RecyclerView.ViewHolder>(CusDiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CusViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CusViewHolder).bindData(getItem(position))
    }

    private class CusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(student: Student) {
            itemView.tv_student_name.text = student.name
            itemView.iv_student_head.setImageResource(R.mipmap.ic_launcher_round)
        }
    }

    private class CusDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }

        override fun getChangePayload(oldItem: Student, newItem: Student): Any? {
            return super.getChangePayload(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }
    }
}