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
    }

    override fun onFragmentInteraction(uri: Uri) {
        LogUtil.d("onFragmentInteraction")
    }
}