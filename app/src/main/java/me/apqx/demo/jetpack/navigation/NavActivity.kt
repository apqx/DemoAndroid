package me.apqx.demo.jetpack.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityNavBinding
import me.apqx.demo.jetpack.navigation.fragments.FragmentMain

class NavActivity : AppCompatActivity(), FragmentMain.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityNavBinding = DataBindingUtil.setContentView<ActivityNavBinding>(this, R.layout.activity_nav)
        activityNavBinding.bnvTag.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_main0 ->  {
                    LogUtil.log("click fragment_main")
                    true
                }
                R.id.fragment_login0 -> {
                    LogUtil.log("click fragment_login")
                    true
                }
                else -> {
                    LogUtil.log("click nothing = ${it.title}")
                    true
                }
            }
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        LogUtil.log("onFragmentInteraction")
    }

    override fun onSupportNavigateUp() = findNavController(R.layout.fragment_main).navigateUp()
}