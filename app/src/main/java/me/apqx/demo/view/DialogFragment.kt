package me.apqx.demo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.frag_dialog.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView

import me.apqx.demo.widget.dialog.CusDialogExtend
import me.apqx.libtools.log.LogUtil

class DialogFragment: BaseFragment<BasePresenter<IBaseView>>() {

    private lateinit var cusDialogExtend: CusDialogExtend

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_show_dialog.setOnClickListener(this)
        btn_dialog_dismiss.setOnClickListener(this)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                LogUtil.d("SeekBar onProgressChanged $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_show_dialog -> {
                showDialogDefault()
            }
            R.id.btn_dialog_dismiss -> {

            }
        }
    }

    private fun showDialogDefault() {
        if (!this::cusDialogExtend.isInitialized) {
            cusDialogExtend = CusDialogExtend(activity!!)
        }
        if (cusDialogExtend.isShowing) {
            cusDialogExtend.dismiss()
        }
        cusDialogExtend.show()
    }



}