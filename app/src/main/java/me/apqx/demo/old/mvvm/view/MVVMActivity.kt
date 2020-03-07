package me.apqx.demo.old.mvvm.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityMvvmBinding
import me.apqx.demo.old.mvvm.bean.Student
import me.apqx.demo.old.mvvm.viewmodel.StudentViewModel

class MVVMActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMvvmBinding
    private lateinit var studentViewMode: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        studentViewMode = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        studentViewMode.studentLiveData.observe(this, Observer<Student> { t ->
            LogUtil.d("update $t")
            dataBinding.tvStudentName.text = t.name
            dataBinding.tvStudentAge.text = t.age.toString()
        })
    }

    fun onClick(view: View) {
        LogUtil.d("click $view")
        studentViewMode.refreshData()
    }
}