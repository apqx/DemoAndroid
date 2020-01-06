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
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.FragViewBinding
import me.apqx.demo.fragment.CusFragmentActivity

class FragmentView: Fragment() {
    private lateinit var binding: FragViewBinding

    override fun onAttach(activity: Activity) {
        LogUtil.d("fragment onAttach ${javaClass.simpleName}")
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.d("fragment onCreate ${javaClass.simpleName}")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.d("fragment onCreateView ${javaClass.simpleName}")
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_view, container, false)
        binding.btnFragment.setOnClickListener {
            startActivity(Intent(activity, CusFragmentActivity::class.java))
        }
        return binding.root
    }

    override fun onStart() {
        LogUtil.d("fragment onStart ${javaClass.simpleName}")
        super.onStart()
    }

    override fun onResume() {
        LogUtil.d("fragment onResume ${javaClass.simpleName}")
        super.onResume()
    }

    override fun onPause() {
        LogUtil.d("fragment onPause ${javaClass.simpleName}")
        super.onPause()
    }

    override fun onStop() {
        LogUtil.d("fragment onStop ${javaClass.simpleName}")
        super.onStop()
    }

    override fun onDestroyView() {
        LogUtil.d("fragment onDestroyView ${javaClass.simpleName}")
        super.onDestroyView()
    }

    override fun onDestroy() {
        LogUtil.d("fragment onDestroy ${javaClass.simpleName}")
        super.onDestroy()
    }

    override fun onDetach() {
        LogUtil.d("fragment onDetach ${javaClass.simpleName}")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtil.d("fragment onViewCreated ${javaClass.simpleName}")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtil.d("fragment onActivityCreated ${javaClass.simpleName}")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LogUtil.d("fragment onConfigurationChanged ${javaClass.simpleName}")
        super.onConfigurationChanged(newConfig)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        LogUtil.d("fragment onViewStateRestored ${javaClass.simpleName}")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        LogUtil.d("fragment onSaveInstanceState ${javaClass.simpleName}")
        super.onSaveInstanceState(outState)
    }
}