package me.apqx.demo.jetpack.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemStudentDatabindingBinding
import me.apqx.demo.jetpack.databinding.bean.Student

class StudentAdapter(val list: MutableList<Student>) : RecyclerView.Adapter<StudentAdapter.CusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student_databinding, parent, false)
//        val dataBinding = DataBindingUtil.bind<ItemStudentDatabindingBinding>(view)
        val dataBinding = DataBindingUtil.inflate<ItemStudentDatabindingBinding>(LayoutInflater.from(parent.context), R.layout.item_student_databinding, parent, false)
        return CusViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    inner class CusViewHolder(val dataBinding: ItemStudentDatabindingBinding?) : RecyclerView.ViewHolder(dataBinding!!.root) {
        fun setItem(student: Student) {
            dataBinding?.student = student
            dataBinding?.adapter = this@StudentAdapter
//            dataBinding?.tvName?.text = student.name
//            dataBinding?.tvAge?.text = student.age.toString()
//            dataBinding?.ivHead?.setImageResource(R.mipmap.ic_launcher_round)
            dataBinding?.executePendingBindings()
        }

    }
}