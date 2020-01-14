package me.apqx.demo.view

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.apqx.demo.tools.LogUtil
import me.apqx.demo.R

class FragmentComponent: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_component, container, false)
    }

    override fun onClick(v: View?) {

    }
}