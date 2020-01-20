package me.apqx.demo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.frag_home.*
import me.apqx.demo.R
import me.apqx.demo.databinding.FragHomeBinding

class HomeFragment: BaseFragment() {
    private lateinit var fragmentHomeBinding: FragHomeBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_home, container, false)
        // <fragment>标签其实是一个FrameLayout，也即是这里的Container，即，Fragment的View被插入到了它要被FragmentManager操作的那个ViewGroup里面
        return fragmentHomeBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 最终也是调用的view.findNavController()
        navController = activity!!.findNavController(R.id.frag_nav_host_home)
        // NavHostFragment内部有一个NavController，与它的View绑定，如果这个View的Container也是一个NavHostFragment的话，这个Container也会与这个NavController绑定
        // view.findNavController是在当前的View上查找NavController，没有的话，就层层向上查找
        // 所以，这个view应该是<fragment>NavHostFragment，才能拿到这个NavController
        navController = view!!.findViewById<View>(R.id.frag_nav_host_home).findNavController()
        initNavigation()
    }

    private fun initNavigation() {
        // 绑定BottomNavigationView和Navigation里的destinations，注意，id要相同，才能实现点击跳转
        NavigationUI.setupWithNavController(bnv_tab, navController)
    }

    override fun onClick(v: View?) {

    }
}