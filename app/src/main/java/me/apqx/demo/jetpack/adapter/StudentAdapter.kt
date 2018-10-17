package me.apqx.demo.jetpack.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.apqx.demo.Constant
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemStudentBinding
import me.apqx.demo.jetpack.bean.Student

class StudentAdapter(private val list: ArrayList<Student>,
                     private val context: Context): RecyclerView.Adapter<StudentAdapter.CusViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CusViewHolder {
        val binding: ItemStudentBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_student, p0, false)
        return CusViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: CusViewHolder, p1: Int) {
        p0.bind(list[p1])
    }

    fun onItemClick(std: Student) {
        Log.d(Constant.TAG, "item click $std")
    }

    inner class CusViewHolder(private val binding: ItemStudentBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.std = student
            binding.adapter = this@StudentAdapter
            binding.executePendingBindings()
        }
    }
}