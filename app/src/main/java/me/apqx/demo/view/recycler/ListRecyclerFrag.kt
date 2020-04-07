package me.apqx.demo.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag_list_recycler.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.view.adapter.ListItemTouchHelperCallback
import me.apqx.demo.view.adapter.RecyclerListAdapter


class ListRecyclerFrag : BaseFragment<BasePresenter<IBaseView>>() {

    private lateinit var adapter: RecyclerListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_list_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_left.setOnClickListener(this)
        btn_right.setOnClickListener(this)

        adapter = RecyclerListAdapter()
        rv_list.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(ListItemTouchHelperCallback())
        itemTouchHelper.attachToRecyclerView(rv_list)
        initData()
    }

    private fun initData() {
        val list = ArrayList<String>().apply {
            for (i in 0..100)
                add("Tom$i")
        }
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_left -> {
                rv_list.setPadding(100, 0, 100, 0)
            }
            R.id.btn_right -> {

            }
        }
    }
}