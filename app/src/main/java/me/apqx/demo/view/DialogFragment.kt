package me.apqx.demo.view

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_dialog.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.widget.dialog.CusDialogExtend

class DialogFragment: BaseFragment<BasePresenter<IBaseView>>() {

    private lateinit var cusDialogExtend: CusDialogExtend

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_show_dialog.setOnClickListener(this)
        btn_dialog_dismiss.setOnClickListener(this)

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
            cusDialogExtend = CusDialogExtend(activity!!, R.style.TransparentDialog)
            setPositionBottom(cusDialogExtend)
            setAnim(cusDialogExtend)
        }
        if (cusDialogExtend.isShowing) {
            cusDialogExtend.dismiss()
        }
        cusDialogExtend.show()
    }

    private fun setAnim(dialog: Dialog) {
        dialog.window?.setWindowAnimations(R.style.dialogWindowAnim)
    }

    /**
     * 设置Dialog的位置
     */
    private fun setPositionBottom(dialog: Dialog) {
        val window = dialog.window!!
        val layoutParams = window.attributes

        layoutParams.gravity = Gravity.BOTTOM
        layoutParams.y = 100

        window.attributes = layoutParams
    }
}