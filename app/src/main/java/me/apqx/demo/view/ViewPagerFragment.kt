package me.apqx.demo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_view_pager.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.view.adapter.SimplePagerAdapter

class ViewPagerFragment : BaseFragment<BasePresenter<IBaseView>>() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_view_pager, container, false)
    }

    private lateinit var simplePagerAdapter: SimplePagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_vp.setOnClickListener(this)
        simplePagerAdapter = SimplePagerAdapter(requireContext())
        pi_pager_indicator.bindViewPager(vp_demo)
        vp_demo.adapter = simplePagerAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_vp -> {
                simplePagerAdapter.newData()
            }
        }
    }

}