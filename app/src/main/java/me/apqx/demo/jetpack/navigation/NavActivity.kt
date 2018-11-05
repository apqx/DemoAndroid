package me.apqx.demo.jetpack.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.jetpack.navigation.fragments.FragmentMain

class NavActivity : AppCompatActivity(), FragmentMain.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
    }

    override fun onFragmentInteraction(uri: Uri) {
        LogUtil.log("onFragmentInteraction")
    }
}