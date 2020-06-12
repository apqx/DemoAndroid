package me.apqx.demo.presenter

import me.apqx.demo.adapter.RecyclerFragVpAdapter
import me.apqx.demo.mvp.BasePresenter

import me.apqx.demo.view.recycler.ChipsRecyclerFrag
import me.apqx.demo.view.recycler.ListRecyclerFrag
import me.apqx.demo.view.recycler.RecyclerFragment
import me.apqx.libtools.log.LogUtil

class RecyclerPresenter : BasePresenter<RecyclerFragment>() {
    fun initData() {
        LogUtil.d("RecyclerPresenter initData")
        var list = ArrayList<RecyclerFragVpAdapter.Item>()
        list.add(RecyclerFragVpAdapter.Item("List", ListRecyclerFrag()))
        list.add(RecyclerFragVpAdapter.Item("Chips", ChipsRecyclerFrag()))
        view?.showChips(list)
    }
}