package me.apqx.demo.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_recycler.*
import me.apqx.demo.R
import me.apqx.demo.adapter.RecyclerFragVpAdapter
import me.apqx.demo.mvp.BaseFragment
import me.apqx.libbase.util.LogUtil
import me.apqx.demo.presenter.RecyclerPresenter
import java.util.ArrayList

open class RecyclerFragment : BaseFragment<RecyclerPresenter>() {
    private lateinit var vpAdapter: RecyclerFragVpAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_recycler, container, false)
    }

    override fun initPresenter(): RecyclerPresenter {
        val tempPresenter = RecyclerPresenter()
        tempPresenter.attachView(this)
        return tempPresenter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vpAdapter = RecyclerFragVpAdapter(fragmentManager!!)
        vp_recycler.adapter = vpAdapter
        tb_recycler.setupWithViewPager(vp_recycler)
        presenter?.initData()
    }



    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showChips(list: ArrayList<RecyclerFragVpAdapter.Item>) {
        LogUtil.d("RecyclerFragment showChips $list")
        vpAdapter.setData(list)
        vpAdapter.notifyDataSetChanged()
    }
}