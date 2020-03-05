package me.apqx.demo.view.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import java.lang.ref.SoftReference

class TabOtherFragment: BaseFragment<BasePresenter<IBaseView>>() {

    private lateinit var viewSoftReference: SoftReference<View>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (this::viewSoftReference.isInitialized && viewSoftReference.get() != null) {
            return viewSoftReference.get()
        }
        val itemView = inflater.inflate(R.layout.frag_others, container, false)
        viewSoftReference = SoftReference(itemView)
        return itemView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onClick(v: View?) {

    }
}