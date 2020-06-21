package me.apqx.demo.view

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.frag_text.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView

import me.apqx.libtools.log.LogUtil
import me.apqx.libtools.view.DisplayUtil

class TextFragment: BaseFragment<BasePresenter<IBaseView>>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_top.setOnClickListener(this)
        btn_bottom.setOnClickListener(this)

        vg_inner_scroll_text.setOnClickListener(this)

        et_test.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                LogUtil.d("onTextChanged $s")
            }
        })
    }

    override fun onClick(v: View?) {
        LogUtil.d("TextFragment onClick")
        when (v?.id) {
            R.id.sv_text, R.id.vg_inner_scroll_text -> {
                DisplayUtil.hideSoftInputKeyboard(v)
                DisplayUtil.clearEditFocus(v)
            }

            R.id.btn_bottom -> {
                findNavController().navigate(TextFragmentDirections.actionTextFragmentToTabViewFragment())
            }

            R.id.btn_top -> {
                et_test.text = SpannableStringBuilder("${et_test.text} 1")
                // 启动自己的新实例
                findNavController().navigate(TextFragmentDirections.actionTextFragmentSelf())
            }
        }

    }
}