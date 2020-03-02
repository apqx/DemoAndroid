package me.apqx.demo.mvp

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.apqx.demo.old.tools.LogUtil

abstract class BaseFragment<P : BasePresenter<*>>: Fragment(), IBaseView, View.OnClickListener {

    protected var presenter: P? = null

    override fun onAttach(activity: Activity) {
        LogUtil.i("fragment onAttach ${javaClass.simpleName}")
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.i("fragment onCreate ${javaClass.simpleName}")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.i("fragment onCreateView ${javaClass.simpleName}")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtil.i("fragment onActivityCreated ${javaClass.simpleName}")
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter()
    }

    protected open fun initPresenter(): P? {
        return null
    }

    override fun onStart() {
        LogUtil.i("fragment onStart ${javaClass.simpleName}")
        super.onStart()
    }

    override fun onResume() {
        LogUtil.i("fragment onResume ${javaClass.simpleName}")
        super.onResume()
    }

    override fun onPause() {
        LogUtil.i("fragment onPause ${javaClass.simpleName}")
        super.onPause()
    }

    override fun onStop() {
        LogUtil.i("fragment onStop ${javaClass.simpleName}")
        super.onStop()
    }

    override fun onDestroyView() {
        LogUtil.i("fragment onDestroyView ${javaClass.simpleName}")
        super.onDestroyView()
    }

    override fun onDestroy() {
        LogUtil.i("fragment onDestroy ${javaClass.simpleName}")
        super.onDestroy()
        presenter?.detachView()
    }

    override fun onDetach() {
        LogUtil.i("fragment onDetach ${javaClass.simpleName}")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtil.i("fragment onViewCreated ${javaClass.simpleName}")
        super.onViewCreated(view, savedInstanceState)
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        LogUtil.i("fragment onConfigurationChanged ${javaClass.simpleName}")
        super.onConfigurationChanged(newConfig)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        LogUtil.i("fragment onViewStateRestored ${javaClass.simpleName}")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        LogUtil.i("fragment onSaveInstanceState ${javaClass.simpleName}")
        super.onSaveInstanceState(outState)
    }
}