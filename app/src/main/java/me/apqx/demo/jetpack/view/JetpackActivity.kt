package me.apqx.demo.jetpack.view

import android.app.Activity
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import me.apqx.demo.Constant
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityJetpackBinding
import me.apqx.demo.jetpack.adapter.StudentAdapter
import me.apqx.demo.jetpack.bean.Student
import me.apqx.demo.jetpack.bean.StudentsViewModel

class JetpackActivity : AppCompatActivity() {
    private lateinit var model: StudentsViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJetpackBinding = DataBindingUtil.setContentView(this, R.layout.activity_jetpack)
        model = ViewModelProviders.of(this).get(StudentsViewModel::class.java)
        adapter = StudentAdapter(model.students.value, this)
        model.students.observe(this, Observer<List<Student>> {
            Log.d(Constant.TAG, "changed $it")
            adapter.notifyDataSetChanged()
        })
        val layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter
        binding.rvStudents.layoutManager = layoutManager
        lifecycle.addObserver(adapter)
    }

    fun onClickAdd(view: View) {
        println("btn click add")
        for (i in 1..5) {
            model.list.add(Student("Tom${model.list.size}", model.list.size))
        }
        model.students.value = model.list
    }

    fun onClickChange(view: View) {
        println("btn click change")
        model.list.forEach {
            it.name = it.name + "-"
        }
    }
}