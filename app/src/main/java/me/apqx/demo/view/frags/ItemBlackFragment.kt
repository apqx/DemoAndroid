package me.apqx.demo.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_item_black.*
import kotlinx.android.synthetic.main.frag_item_red.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import java.lang.ref.SoftReference


class ItemBlackFragment(val myId: Int) : BaseFragment<BasePresenter<IBaseView>>() {

    constructor() : this(0)

    private lateinit var viewSoftReference: SoftReference<View>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (this::viewSoftReference.isInitialized && viewSoftReference.get() != null) {
            return viewSoftReference.get()
        }
        val view = inflater.inflate(R.layout.frag_item_black, container, false)
        viewSoftReference = SoftReference(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_item_frag_black_content.setOnClickListener(this)
        if (savedInstanceState != null && savedInstanceState.containsKey("key")) {
            tv_item_frag_black_content.text = savedInstanceState.getString("key")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("key", tv_item_frag_black_content.text.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_item_frag_black_content -> {
                tv_item_frag_black_content.text = "Hello $myId"
            }
        }
    }
}