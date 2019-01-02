package me.apqx.demo.jetpack.navigation

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityNavBinding
import me.apqx.demo.jetpack.navigation.fragments.*

class NavActivity : AppCompatActivity(), FragmentMain.OnFragmentInteractionListener, FragmentLogin.OnFragmentInteractionListener,
        FragmentSettings.OnFragmentInteractionListener, FragmentRed.OnFragmentInteractionListener, FragmentBlue.OnFragmentInteractionListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityNavBinding = DataBindingUtil.setContentView<ActivityNavBinding>(this, R.layout.activity_nav)
//        val nabController = findNavController(R.layout.fragment_main)
        val navController = Navigation.findNavController(this, R.id.fragment)
        activityNavBinding.bnvTag.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_main ->  {
                    LogUtil.d("click fragment_main")
                    // TODO: singleInstance
//                    navController.popBackStack()
                    // 返回上一个Stack中的destination，到达start destination后，不会退出activity，这一点和back键不一样
                    navController.navigateUp()
//                    navController.navigate(R.id.fragment_main)
                    true
                }
                R.id.item_login -> {
                    LogUtil.d("click fragment_login")
                    val bundle = Bundle()
                    bundle.putString("name", "Jerry")
                    val direction = FragmentMainDirections.actionFragmentMainToFragmentLogin()

                    navController.navigate(direction)
                    true
                }
                R.id.item_settings -> {
                    LogUtil.d("click fragment_settings")
                    navController.navigate(R.id.fragment_info)
                    true
                }
                else -> {
                    LogUtil.d("click nothing = ${it.title}")
                    true
                }
            }
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        LogUtil.d("onFragmentInteraction")
    }
}