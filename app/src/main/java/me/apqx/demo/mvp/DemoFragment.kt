package me.apqx.demo.mvp

import android.os.Bundle
import android.view.View

open class DemoFragment : BaseFragment<DemoPresenter>() {
    override fun initPresenter(): DemoPresenter? {
        presenter = DemoPresenter();
        presenter?.attachView(this)
        return presenter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}