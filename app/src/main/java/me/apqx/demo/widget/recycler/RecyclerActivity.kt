package me.apqx.demo.widget.recycler

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import me.apqx.demo.R
import me.apqx.demo.ToastUtil
import me.apqx.demo.databinding.ActivityRecyclerBinding
import me.apqx.demo.widget.recycler.adapter.OutAdapter
import me.apqx.demo.widget.recycler.bean.Man
import me.apqx.demo.widget.recycler.viewmodel.ManViewModel

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var adapter: OutAdapter
    private lateinit var viewModel: ManViewModel
    private var lastItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler)
        viewModel = ViewModelProviders.of(this).get(ManViewModel::class.java)
        viewModel.manLiveData.observe(this, Observer<MutableList<Man>> {
//            adapter.notifyDataSetChanged()
            adapter.notifyItemChanged(lastItemCount)
            lastItemCount = it.size
        })

        val layoutManager = LinearLayoutManager(this)
        adapter = OutAdapter(viewModel.manLiveData.value!!)
        binding.rvManOut.layoutManager = layoutManager
        binding.rvManOut.adapter = adapter
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