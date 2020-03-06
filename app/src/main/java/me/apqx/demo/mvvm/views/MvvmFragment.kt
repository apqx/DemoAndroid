package me.apqx.demo.mvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.frag_mvvm.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.mvvm.viewmodels.DemoViewModel
import me.apqx.demo.old.tools.LogUtil

class MvvmFragment : BaseFragment<BasePresenter<IBaseView>>() {

    private val model: DemoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_mvvm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_mvvm_change.setOnClickListener(this)

        model.strList.observe(this, Observer {
            LogUtil.d("MvvmFragment notifyData $it")
            if (it.isNotEmpty())
                tv_mvvm_hint.text = it[0]
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_mvvm_change -> {
                model.loadStrList()
            }
        }
    }
}