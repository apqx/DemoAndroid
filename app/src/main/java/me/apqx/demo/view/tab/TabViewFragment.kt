package me.apqx.demo.view.tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag_view.*
import me.apqx.demo.MainActivity
import me.apqx.demo.R
import me.apqx.demo.SecondActivity
import me.apqx.demo.adapter.SimpleRecyclerAdapter
import me.apqx.demo.databinding.FragViewBinding
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.mvvm.viewmodels.DemoViewModel
import me.apqx.demo.view.frags.FragsActivity
import me.apqx.libbase.util.ToastUtil
import java.lang.ref.SoftReference

class TabViewFragment : BaseFragment<BasePresenter<IBaseView>>() {
    private lateinit var binding: FragViewBinding
    private lateinit var simpleAdapter: SimpleRecyclerAdapter

    private lateinit var viewSoftReference: SoftReference<View>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        if (this::viewSoftReference.isInitialized && viewSoftReference.get() != null) {
//            return viewSoftReference.get()
//        }
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_view, container, false)
        viewSoftReference = SoftReference(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: DemoViewModel by viewModels()
        model.studentList.observe(viewLifecycleOwner, Observer {
//            ToastUtil.showToast("TabView LiveData $it")
        })
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
        btn_text.setOnClickListener(this)

        simpleAdapter = SimpleRecyclerAdapter()
        simpleAdapter.setOnItemClickListener(object : SimpleRecyclerAdapter.OnItemClickListener {
            override fun onItemClick() {
                simpleAdapter.notifyDataSetChanged()
            }
        })
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
//                findNavController().navigate(TabViewFragmentDirections.actionTabViewFragmentToFragsFragment())
                startActivity(Intent(context, FragsActivity::class.java))
            }
            R.id.btn_second_activity -> {
                startActivity(Intent(activity, SecondActivity::class.java))
            }
            R.id.btn_dialog -> {
                findNavController().navigate(TabViewFragmentDirections.actionTabViewFragmentToDialogFragment())
            }
            R.id.btn_anim -> {
                findNavController().navigate(TabViewFragmentDirections.actionTabViewFragmentToAnimFragment())
            }
            R.id.btn_add_view -> {
                findNavController().navigate(TabViewFragmentDirections.actionTabViewFragmentToAddViewFragment())
            }
            R.id.btn_toggle_loading -> {
                if ((activity as MainActivity).isLoadingShowing()) {
                    (activity as MainActivity).dismissLoading()
                } else {
                    (activity as MainActivity).showLoading("")
                }
            }
            R.id.btn_text -> {
                findNavController().navigate(TabViewFragmentDirections.actionTabViewFragmentToTextFragment())
            }

            R.id.btn_expand_top -> {
                // 设置AppBar展开
                abl_top.setExpanded(true, true)
            }
        }
    }

    fun toggleExpandStatus() {
        abl_top.setExpanded(true, true)
    }


}