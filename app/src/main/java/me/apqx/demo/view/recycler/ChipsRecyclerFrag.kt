package me.apqx.demo.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.frag_chips_recycler.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.view.adapter.ChipsItemTouchHelperCallback
import me.apqx.demo.view.adapter.RecyclerChipsAdapter

class ChipsRecyclerFrag : BaseFragment<BasePresenter<IBaseView>>() {
    private lateinit var adapter: RecyclerChipsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_chips_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_chips_change.setOnClickListener(this)

        adapter = RecyclerChipsAdapter()
        val layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP)
//        val layoutManager = LinearLayoutManager(requireContext())
        rv_chips.adapter = adapter
        rv_chips.layoutManager = layoutManager
        ItemTouchHelper(ChipsItemTouchHelperCallback()).attachToRecyclerView(rv_chips)
        initData()
    }

    private fun initData() {
        val list = ArrayList<String>()
        list.add("标签")
        list.add("大标签")
        list.add("很大的标签")
        list.add("小标签")
        list.add("常规常规常规的标签")
        list.add("标签")
        list.add("标签")
        list.add("大标签")
        list.add("小标签")
        list.add("标签")
        list.add("正常的标签")
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_chips_change -> {

                adapter.notifyItemChanged(0)
            }
        }
    }
}