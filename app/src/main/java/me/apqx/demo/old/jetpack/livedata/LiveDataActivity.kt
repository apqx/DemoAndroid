package me.apqx.demo.old.jetpack.livedata

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityLivedataBinding
import me.apqx.demo.old.jetpack.livedata.adapter.StudentAdapter
import me.apqx.demo.old.jetpack.livedata.bean.Student
import me.apqx.demo.old.jetpack.livedata.viewmodel.StudentViewModel

/**
 * LiveData与DataBinding配合
 */
class LiveDataActivity: AppCompatActivity(), ILiveDataActivity {
    lateinit var dataBinding: ActivityLivedataBinding
    lateinit var adapter: StudentAdapter
    lateinit var stdViewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_livedata)
        stdViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        stdViewModel.studentLiveData.observe(this, Observer<MutableList<Student>> {
            // 数据发生了变化，刷新UI
            adapter.notifyDataSetChanged()
            LogUtil.d("notifyDataSetChanged")
        })

        adapter = StudentAdapter(stdViewModel.studentLiveData.value!!, this)
        val layoutManager = LinearLayoutManager(this)
        dataBinding.rvStudents.adapter = adapter
        dataBinding.rvStudents.layoutManager = layoutManager
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_add -> {
                stdViewModel.add()
            }
            R.id.btn_delete -> {
                stdViewModel.delete()
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
        stdViewModel.refresh()
    }
}