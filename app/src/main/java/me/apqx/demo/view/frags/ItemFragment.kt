package me.apqx.demo.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_item_black.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.libbase.util.LogUtil


class ItemFragment() : BaseFragment<BasePresenter<IBaseView>>() {

    companion object {
        private val tagInt = "tag"
        fun newInstance(id: Int): ItemFragment {
            val bundle = Bundle()
            bundle.putInt(tagInt, id)
            val fragment = ItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_item_black, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_item_frag_content.setOnClickListener(this)
        val key = arguments?.getInt("key")
        tv_item_frag_content.text = "$key"
        LogUtil.d("ItemFragment int = ${arguments?.getInt(tagInt)}")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("key", tv_item_frag_content.text.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_item_frag_content -> {
                arguments?.getInt(tagInt).also {
                    tv_item_frag_content.text = it.toString()
                }
            }
        }
    }
}