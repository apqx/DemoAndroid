package me.apqx.demo.old.jetpack.navigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemStudentBinding
import me.apqx.demo.old.jetpack.navigation.bean.Student

class StudentAdapter(val list: List<Student>) : RecyclerView.Adapter<StudentAdapter.CusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return CusViewHolder(DataBindingUtil.bind<ItemStudentBinding>(view))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        holder.setStudent(list.get(position))
    }

    inner class CusViewHolder(val itemStudentBinding: ItemStudentBinding?) : RecyclerView.ViewHolder(itemStudentBinding!!.root) {
        fun setStudent(student: Student) {
            itemStudentBinding!!.tvStudentName.text = student.name
            itemStudentBinding!!.tvStudentAge.text = student.age.toString()
            itemStudentBinding!!.ivStudentHead.setImageResource(R.mipmap.ic_launcher_round)
        }

    }
}