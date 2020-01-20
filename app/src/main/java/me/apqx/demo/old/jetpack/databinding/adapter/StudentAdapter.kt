package me.apqx.demo.old.jetpack.databinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemStudentDatabindingBinding
import me.apqx.demo.old.jetpack.databinding.bean.Student

class StudentAdapter(val list: MutableList<Student>) : RecyclerView.Adapter<StudentAdapter.CusViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        context = parent.context
        val dataBinding = DataBindingUtil.inflate<ItemStudentDatabindingBinding>(LayoutInflater.from(parent.context), R.layout.item_student_databinding, parent, false)
        return CusViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    fun onItemClick(student: Student) {
        Toast.makeText(context, student.name, Toast.LENGTH_SHORT).show()
    }

    inner class CusViewHolder(val dataBinding: ItemStudentDatabindingBinding?) : RecyclerView.ViewHolder(dataBinding!!.root) {
        fun setItem(student: Student) {
            dataBinding?.student = student
            dataBinding?.adapter = this@StudentAdapter
            dataBinding?.executePendingBindings()
        }

    }
}