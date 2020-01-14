package me.apqx.demo.view

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.frag_view.*
import me.apqx.demo.MainActivity
import me.apqx.demo.tools.LogUtil
import me.apqx.demo.R
import me.apqx.demo.SecondActivity
import me.apqx.demo.databinding.FragViewBinding
import me.apqx.demo.fragment.CusFragmentActivity

class FragmentView: BaseFragment() {
    private lateinit var binding: FragViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_view, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_fragment.setOnClickListener(this)
        btn_second_activity.setOnClickListener(this)
        btn_dialog.setOnClickListener(this)
        btn_anim.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_fragment -> {
                startActivity(Intent(activity, CusFragmentActivity::class.java))
            }
            R.id.btn_second_activity -> {
                startActivity(Intent(activity, SecondActivity::class.java))
            }
            R.id.btn_dialog -> {
                (activity as MainActivity).navController.navigate(FragmentHomeDirections.actionHomeToDialog())
            }
            R.id.btn_anim -> {
                (activity as MainActivity).navController.navigate(FragmentHomeDirections.actionHomeToAnim())
            }
        }
    }


}