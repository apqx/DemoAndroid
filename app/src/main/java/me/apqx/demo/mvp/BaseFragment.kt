package me.apqx.demo.mvp

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.apqx.libtools.log.LogUtil


abstract class BaseFragment<P : BasePresenter<*>>: Fragment(), IBaseView, View.OnClickListener {

    protected var presenter: P? = null

    override fun onAttach(activity: Activity) {
        LogUtil.i("fragment onAttach ${javaClass.simpleName} : $this")
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.i("fragment onCreate ${javaClass.simpleName} : $this")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.i("fragment onCreateView ${javaClass.simpleName} : $this")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtil.i("fragment onActivityCreated ${javaClass.simpleName} : $this")
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter()
    }

    protected open fun initPresenter(): P? {
        return null
    }

    override fun onStart() {
        LogUtil.i("fragment onStart ${javaClass.simpleName} : $this")
        super.onStart()
    }

    override fun onResume() {
        LogUtil.i("fragment onResume ${javaClass.simpleName} : $this")
        super.onResume()
    }

    override fun onPause() {
        LogUtil.i("fragment onPause ${javaClass.simpleName} : $this")
        super.onPause()
    }

    override fun onStop() {
        LogUtil.i("fragment onStop ${javaClass.simpleName} : $this")
        super.onStop()
    }

    override fun onDestroyView() {
        LogUtil.i("fragment onDestroyView ${javaClass.simpleName} : $this")
        super.onDestroyView()
    }

    override fun onDestroy() {
        LogUtil.i("fragment onDestroy ${javaClass.simpleName} : $this")
        super.onDestroy()
        presenter?.detachView()
    }

    override fun onDetach() {
        LogUtil.i("fragment onDetach ${javaClass.simpleName} : $this")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtil.i("fragment onViewCreated ${javaClass.simpleName} : $this")
        super.onViewCreated(view, savedInstanceState)
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        LogUtil.i("fragment onConfigurationChanged ${javaClass.simpleName} : $this")
        super.onConfigurationChanged(newConfig)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        LogUtil.i("fragment onViewStateRestored ${javaClass.simpleName} : $this")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        LogUtil.i("fragment onHiddenChanged ${javaClass.simpleName} hidden = $hidden : $this")
        super.onHiddenChanged(hidden)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        LogUtil.i("fragment onSaveInstanceState ${javaClass.simpleName} : $this")
        super.onSaveInstanceState(outState)
    }
}