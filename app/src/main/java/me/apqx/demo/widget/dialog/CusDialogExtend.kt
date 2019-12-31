
package me.apqx.demo.widget.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.DialogCusBinding

class CusDialogExtend : Dialog {

    lateinit var dataBinding: DialogCusBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.inflate<DialogCusBinding>(LayoutInflater.from(context), R.layout.dialog_cus, null, false)
        setContentView(dataBinding.root)
        LogUtil.d("dialog onCreate")
    }

    override fun show() {
        LogUtil.d("dialog before show()")
        super.show()
        LogUtil.d("dialog after show()")
    }
}