package me.apqx.demo.widget.recycler

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import me.apqx.demo.R
import me.apqx.demo.ToastUtil
import me.apqx.demo.databinding.ActivityRecyclerBinding
import me.apqx.demo.widget.recycler.adapter.StudentAdapter
import me.apqx.demo.widget.recycler.bean.Student
import me.apqx.demo.widget.recycler.viewmodel.StudentViewModel

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var adapter: StudentAdapter
    private lateinit var viewModel: StudentViewModel
    private var lastItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler)
        viewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        viewModel.studentLiveData.observe(this, Observer<MutableList<Student>> {
//            adapter.notifyDataSetChanged()
            adapter.notifyItemChanged(lastItemCount)
            lastItemCount = it.size
        })

        val layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(viewModel.studentLiveData.value!!)
        binding.rvStudents.layoutManager = layoutManager
        binding.rvStudents.adapter = adapter
        viewModel.refreshNew()
    }

    fun onClick(view: View) {
       when (view.id) {
           R.id.btn_refresh -> {
               viewModel.refreshNew()
               ToastUtil.showToast("refresh")
           }
       }
    }
}