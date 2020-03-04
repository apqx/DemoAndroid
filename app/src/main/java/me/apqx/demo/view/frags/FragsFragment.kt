package me.apqx.demo.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_fragments.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView

class FragsFragment : BaseFragment<BasePresenter<IBaseView>>() {
    private var flag = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_fragments, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_add_fragment.setOnClickListener(this)
        btn_hide_fragment.setOnClickListener(this)
        btn_replace_fragment.setOnClickListener(this)
        btn_show_fragment.setOnClickListener(this)
        btn_remove_fragment.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_fragment -> {
                addFragment()
            }
            R.id.btn_replace_fragment -> {

            }
            R.id.btn_hide_fragment -> {

            }
            R.id.btn_show_fragment -> {

            }
            R.id.btn_remove_fragment -> {

            }
        }
    }

    private fun addFragment() {
        fragmentManager?.beginTransaction()
                ?.add(R.id.vg_frag_container, generateFragment())
                ?.commit()
    }

    private fun generateFragment(): Fragment {
        return if (flag++ % 2 == 0) ItemRedFragment() else ItemGreenFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}