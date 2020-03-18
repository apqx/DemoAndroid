package me.apqx.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.libbase.util.LogUtil
import me.apqx.demo.view.tab.TabComponentFragment
import me.apqx.demo.view.tab.TabOtherFragment
import me.apqx.demo.view.tab.TabViewFragment
import me.apqx.demo.widget.dialog.LoadingDialog
import me.apqx.demo.widget.view.DisplayUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private lateinit var loadingDialog: LoadingDialog
    private val tagFragmentView = "tagFragmentView"
    private val tagFragmentComponent = "tagFragmentComponent"
    private val tagFragmentOthers = "tagFragmentOthers"

    var fragmentView: Fragment? = null
    var fragmentComponent: Fragment? = null
    var fragmentOthers: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        DisplayUtils.listViews(window.decorView, 0)
        DisplayUtils.setStatusBarTransparent(this)
        LogUtil.d("MainActivity savedInstanceState = $savedInstanceState")
//        initByFragmentManager(savedInstanceState)
        navController = findNavController(R.id.frag_nav_host_main)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.tabViewFragment, R.id.tabComponentFragment, R.id.tabOtherFragment -> {
                    bnv_tab.visibility = View.VISIBLE
                }
                else -> {
                    bnv_tab.visibility = View.GONE
                }
            }
        }
        NavigationUI.setupWithNavController(bnv_tab, navController)

    }

    private fun initByFragmentManager(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            showPageView()
        } else {
            fragmentView = supportFragmentManager.findFragmentByTag(tagFragmentView)
            fragmentComponent = supportFragmentManager.findFragmentByTag(tagFragmentComponent)
            fragmentOthers = supportFragmentManager.findFragmentByTag(tagFragmentOthers)
        }

        bnv_tab.setOnNavigationItemSelectedListener {
            LogUtil.d("showPag${it.title} fragmentView = $fragmentView")
            LogUtil.d("showPag${it.title} fragmentComponent = $fragmentComponent")
            LogUtil.d("showPag${it.title} fragmentOthers = $fragmentOthers")
            when (it.itemId) {
                R.id.tabViewFragment -> {
                    showPageView()
                }
                R.id.tabComponentFragment -> {
                    showPageComponent()
                }
                R.id.tabOtherFragment -> {
                    showPageOthers()
                }
            }
            true
        }
        bnv_tab.setOnNavigationItemReselectedListener {
            if (it.itemId == R.id.tabViewFragment) {
                (fragmentView as TabViewFragment).toggleExpandStatus()
            }
        }
    }

    private fun showPageView() {
        supportFragmentManager.beginTransaction().apply {
            fragmentComponent?.let { hide(it) }
            fragmentOthers?.let { hide(it) }
            if (fragmentView == null) {
                fragmentView = TabViewFragment()
                add(R.id.fl_main_frag_container, fragmentView as Fragment, tagFragmentView)
            } else {
                show(fragmentView as Fragment)
            }
            commit()
        }
    }

    private fun showPageComponent() {
        supportFragmentManager.beginTransaction().apply {
            fragmentView?.let { hide(it) }
            fragmentOthers?.let { hide(it) }
            if (fragmentComponent == null) {
                fragmentComponent = TabComponentFragment()
                add(R.id.fl_main_frag_container, fragmentComponent as Fragment, tagFragmentComponent)
            } else {
                show(fragmentComponent as Fragment)
            }
            commit()
        }
    }

    private fun showPageOthers() {
        supportFragmentManager.beginTransaction().apply {
            fragmentComponent?.let { hide(it) }
            fragmentView?.let { hide(it) }
            if (fragmentOthers == null) {
                fragmentOthers = TabOtherFragment()
                add(R.id.fl_main_frag_container, fragmentOthers as Fragment, tagFragmentOthers)
            } else {
                show(fragmentOthers as Fragment)
            }
            commit()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(str: String) {
        LogUtil.d("${javaClass.simpleName} EventBus onEvent $str")
        EventBus.getDefault().removeStickyEvent(str)
    }

    fun onClick(view: View) {

    }

    public fun showLoading(hint: String) {
        showLoading(hint, true, true)
    }

    /**
     * @param cancelAble 按Back键是否dismiss
     * @param cancelOnTouchOutside 点击外部区域是否dismiss
     */
    public fun showLoading(hint: String, cancelAble: Boolean, cancelOnTouchOutside: Boolean) {
        if (!this::loadingDialog.isInitialized) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog.setCancelable(cancelAble)
        loadingDialog.setCanceledOnTouchOutside(cancelOnTouchOutside)
        loadingDialog.show(hint)
    }

    public fun dismissLoading() {
        if (isLoadingShowing()) {
            loadingDialog.dismiss()
        }
    }

    public fun isLoadingShowing(): Boolean {
        return this::loadingDialog.isInitialized && loadingDialog.isShowing
    }

}