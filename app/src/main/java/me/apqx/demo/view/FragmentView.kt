package me.apqx.demo.view

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.frag_view.*
import me.apqx.demo.MainActivity
import me.apqx.demo.tools.LogUtil
import me.apqx.demo.R
import me.apqx.demo.SecondActivity
import me.apqx.demo.databinding.FragViewBinding
import me.apqx.demo.fragment.CusFragmentActivity
import me.apqx.demo.tools.ToastUtil

class FragmentView : BaseFragment() {
    private lateinit var binding: FragViewBinding
    private lateinit var simpleAdapter: SimpleRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_view, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 在Fragment中，使用Toolbar，需要手动设置行为，不能使用Activity的setSupportActionBar()
        tb_top_bar.setNavigationOnClickListener {
            ToastUtil.showToast("Click Back")
        }

        btn_expand_top.setOnClickListener(this)

        btn_fragment.setOnClickListener(this)
        btn_second_activity.setOnClickListener(this)
        btn_dialog.setOnClickListener(this)
        btn_anim.setOnClickListener(this)
        btn_add_view.setOnClickListener(this)
        btn_toggle_loading.setOnClickListener(this)

        simpleAdapter = SimpleRecyclerAdapter()
        simpleAdapter.setData(generateSimpleList())
        rv.adapter = simpleAdapter
        rv.layoutManager = LinearLayoutManager(context)

        srl.setOnRefreshListener {
            srl.postDelayed({
                srl.isRefreshing = false
            }, 2000)
        }
    }

    private fun generateSimpleList(): List<String> {
        val list = ArrayList<String>()
        for (i in 0 until 100) {
            list.add("Simple Item $i")
        }
        return list
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_fragment -> {
                startActivity(Intent(activity, CusFragmentActivity::class.java))
            }
            R.id.btn_second_activity -> {
                startActivity(Intent(activity, SecondActivity::class.java))
            }
            R.id.btn_dialog -> {
                (activity as MainActivity).navController.navigate(FragmentHomeDirections.actionHomeToDialog())
            }
            R.id.btn_anim -> {
                (activity as MainActivity).navController.navigate(FragmentHomeDirections.actionHomeToAnim())
            }
            R.id.btn_add_view -> {
                (activity as MainActivity).navController.navigate(FragmentHomeDirections.actionHomeToAddView())
            }
            R.id.btn_toggle_loading -> {
                if ((activity as MainActivity).isLoadingShowing()) {
                    (activity as MainActivity).dismissLoading()
                } else {
                    (activity as MainActivity).showLoading("")
                }
            }

            R.id.btn_expand_top -> {
                // 设置AppBar展开
                abl_top.setExpanded(true, true)
            }
        }
    }


}