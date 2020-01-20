package me.apqx.demo.old.jetpack.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityDatabindingBinding
import me.apqx.demo.old.jetpack.databinding.adapter.StudentAdapter
import me.apqx.demo.old.jetpack.databinding.bean.Student

/**
 * 不使用LiveData的DataBinding，演示基本的绑定UI和数据
 */

class DataBindingActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityDatabindingBinding
    lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding)
        adapter = StudentAdapter(ArrayList<Student>())
        val layoutManager = LinearLayoutManager(this)
        dataBinding.rvStudents.adapter = adapter
        dataBinding.rvStudents.layoutManager = layoutManager
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_add -> {
                for (i in 0..9) {
                    adapter.list.add(Student("Tom${adapter.list.size}", adapter.list.size, R.mipmap.ic_launcher_round, adapter.list.size))
                }
                adapter.notifyDataSetChanged()
            }
            R.id.btn_delete -> {
                for (i in 0..9)
                    adapter.list.removeAt(adapter.list.size - 1)
                adapter.notifyDataSetChanged()
            }
        }
    }
}