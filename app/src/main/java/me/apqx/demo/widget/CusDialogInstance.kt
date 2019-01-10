package me.apqx.demo.widget

import android.app.Dialog
import android.content.Context
import kotlinx.coroutines.channels.consumesAll
import me.apqx.demo.R

class CusDialogInstance(val context: Context) {
    lateinit var dialog: Dialog

    fun show() {
        if (!this::dialog.isInitialized) {
            dialog = Dialog(context)

            dialog.setContentView(R.layout.dialog_cus)
        }
        dialog.show()
    }
}