package me.apqx.demo.jetpack.livedata

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityLivedataBinding
import me.apqx.demo.jetpack.livedata.adapter.StudentAdapter
import me.apqx.demo.jetpack.livedata.bean.Student
import me.apqx.demo.jetpack.livedata.bean.StudentViewModel

/**
 * LiveData与DataBinding配合
 */
class LiveDataActivity: AppCompatActivity(), ILiveDataActivity {
    lateinit var dataBinding: ActivityLivedataBinding
    lateinit var adapter: StudentAdapter
    lateinit var stdViewModel: StudentViewModel
    val list: MutableList<Student> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_livedata)
        stdViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        stdViewModel.studentLiveData.observe(this, Observer<MutableList<Student>> {
            // 数据发生了变化，刷新UI
            adapter.notifyDataSetChanged()
            LogUtil.d("notifyDataSetChanged")
        })

        adapter = StudentAdapter(list, this)
        val layoutManager = LinearLayoutManager(this)
        dataBinding.rvStudents.adapter = adapter
        dataBinding.rvStudents.layoutManager = layoutManager
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_add -> {
                for (i in 0..9) {
                    list.add(Student("Tom${adapter.list.size}", adapter.list.size, R.mipmap.ic_launcher_round))
                }
                stdViewModel.studentLiveData.value = list
            }
            R.id.btn_delete -> {
                for (i in 0..9)
                    list.removeAt(adapter.list.size - 1)
                stdViewModel.studentLiveData.value = list
            }
            R.id.btn_change -> {

            }
        }
    }

    override fun onClickItem(student: Student) {
        LogUtil.d("click ${student.name}")
        student.name += " !"
        student.select = !student.select
        // 通知数据发生了变化，触发Observer的回调
        stdViewModel.studentLiveData.value = list
    }
}