package me.apqx.demo.widget.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import me.apqx.demo.R
import me.apqx.demo.tools.LogUtil

class LoadingDialog : Dialog {
    @BindView(R.id.tv_loading_hint)
    lateinit var tv_hint: TextView

    constructor(context: Context) : super(context, R.style.TransparentDialog) {
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
    }

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        ButterKnife.bind(this)
    }

    public fun show(hint: String) {
        if (!isShowing) {
            show()
        }
        tv_hint.visibility = if (TextUtils.isEmpty(hint)) View.GONE else View.VISIBLE
        tv_hint.text = hint
        LogUtil.d("dialog hint visibility = ${tv_hint.visibility}")
    }
}