package me.apqx.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.demo.tools.LogUtil
import me.apqx.demo.widget.view.DisplayUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.frag_nav_host_main)
        DisplayUtils.listViews(window.decorView, 0)

//        DisplayUtils.dealStatusBarTransparent(this)
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

}