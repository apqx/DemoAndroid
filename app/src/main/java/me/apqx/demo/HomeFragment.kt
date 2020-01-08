package me.apqx.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_home.*
import me.apqx.demo.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO 这里找到的是MainActivity的navController
        navController = findNavController()
        LogUtil.d("HomeFragment navController = $navController")
        LogUtil.d("bnv $bnv_tab")
        LogUtil.d("navHostFragment $frag_nav_host_home")
        initNavigation()
    }

    private fun initNavigation() {
        // 绑定BottomNavigationView和Navigation里的destinations，注意，id要相同，才能实现点击跳转
        NavigationUI.setupWithNavController(bnv_tab, navController)
    }
}