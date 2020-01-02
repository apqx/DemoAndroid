package me.apqx.demo.widget.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import butterknife.BindView
import butterknife.ButterKnife
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.DialogCusBinding
import java.lang.Exception

class CusDialogExtend : Dialog {
    @BindView(R.id.tv_hint)
    lateinit var tvHint: TextView

    public var hint = ""

    lateinit var dataBinding: DialogCusBinding

    constructor(context: Context) : super(context) {
    }
    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
    }
    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        dataBinding = DataBindingUtil.inflate<DialogCusBinding>(LayoutInflater.from(context), R.layout.dialog_cus, null, false)
//        var view = LayoutInflater.from(context).inflate(R.layout.dialog_cus, null, false)
        setContentView(R.layout.dialog_cus)
        ButterKnife.bind(this)
//        tvHint = findViewById(R.id.tv_hint)
        LogUtil.d("dialog onCreate $tvHint")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LogUtil.d("dialog onAttachedToWindow $tvHint")
    }

    override fun show() {
        LogUtil.d("dialog before show() isShowing = $isShowing")
        super.show()
        LogUtil.d("dialog after show() isShowing = $isShowing")
        try {
            tvHint.text = "show"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}