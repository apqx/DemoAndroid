package me.apqx.demo.jetpack.view

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityJetpackBinding
import me.apqx.demo.jetpack.adapter.StudentAdapter
import me.apqx.demo.jetpack.bean.Student

class JetpackActivity : Activity() {
    private val list = ArrayList<Student>()
    private var adapter: StudentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJetpackBinding = DataBindingUtil.setContentView(this, R.layout.activity_jetpack)
        adapter = StudentAdapter(list, this)
        val layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter
        binding.rvStudents.layoutManager = layoutManager
    }

    fun onClick(view: View) {
        println("click")
        for (i in 1..5) {
            list.add(Student("Tom${list.size}", list.size))
        }
        adapter?.notifyDataSetChanged()
    }
}