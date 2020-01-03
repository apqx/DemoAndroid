package me.apqx.demo

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_view.*
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.demo.widget.view.DisplayUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private var input = ""
    private var lastInputTimeMills = 0L
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var fragNavHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fragNavHost = frag_nav_host as NavHostFragment

        DisplayUtils.dealStatusBarTransparent(this)
        initNavigation()
    }

    private fun initNavigation() {
        // 绑定BottomNavigationView和Navigation里的destinations，注意，id要相同，才能实现点击跳转
        NavigationUI.setupWithNavController(bnv_tab, fragNavHost.navController)
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

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        LogUtil.d("onKeyDown dispatchKeyEvent ${event?.keyCode} ${event?.action}")
        return super.dispatchKeyEvent(event)
    }

}