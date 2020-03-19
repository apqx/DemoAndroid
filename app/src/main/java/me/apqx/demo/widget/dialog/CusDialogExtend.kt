package me.apqx.demo.widget.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import me.apqx.libbase.util.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.DialogCusBinding
import me.apqx.libbase.util.DisplayUtil
import java.lang.Exception

class CusDialogExtend : Dialog {
    @BindView(R.id.tv_hint)
    lateinit var tvHint: TextView

    public var hint = ""

    lateinit var dataBinding: DialogCusBinding

    constructor(context: Context) : super(context, R.style.TransparentDialog) {
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
        setCanceledOnTouchOutside(true)
        setPositionBottom(this)
        setAnim(this)
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

    /**
     * 设置Dialog的大小
     */
    private fun setSize(dialog: Dialog) {
        val window = dialog.window!!
        val lp = window.attributes
        lp.height = DisplayUtil.dpToPx(context, 250f)
        lp.width = DisplayUtil.dpToPx(context, 200f)
        // 当子View的尺寸设置为MATCH_PARENT时，Dialog并不会自动全屏显示，它有自己的最大尺寸，如果想要全屏显示，应该在这里设置
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = lp
    }

    /**
     * 设置Dialog的位置
     */
    private fun setPositionBottom(dialog: Dialog) {
        val window = dialog.window!!
        val layoutParams = window.attributes

        // Dialog默认居中显示，可以设置为TOP或BOTTOM
        layoutParams.gravity = Gravity.BOTTOM
        // 当默认居中时，y设置无效
        // 当TOP时，y是向下的偏移量
        // 当BOTTOM时，y是向上的偏移量
        // y是负数时无效
//        layoutParams.y = 100

        window.attributes = layoutParams
    }

    private fun setAnim(dialog: Dialog) {
        dialog.window?.setWindowAnimations(R.style.dialogWindowAnim)
    }


}