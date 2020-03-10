package me.apqx.demo.old.jetpack.livedata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ItemStudentLivedataBinding
import me.apqx.demo.old.jetpack.livedata.ILiveDataActivity
import me.apqx.demo.old.jetpack.livedata.bean.Student

class StudentAdapter(val list: MutableList<Student>, val iLiveDataActivity: ILiveDataActivity): RecyclerView.Adapter<StudentAdapter.CusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusViewHolder {
        LogUtil.e("onCreateViewHolder")
        return CusViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_student_livedata, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CusViewHolder, position: Int) {
        LogUtil.d("onBindViewHolder $position")
        holder.setItem(list[position])
    }

    fun onClickItem(student: Student) {
        iLiveDataActivity.onClickItem(student)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    inner class CusViewHolder(val dataBinding: ItemStudentLivedataBinding): RecyclerView.ViewHolder(dataBinding.root) {
        fun setItem(student: Student) {
            dataBinding.student = student
            dataBinding.activity = iLiveDataActivity
            dataBinding.executePendingBindings()
        }
    }
}