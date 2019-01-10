package me.apqx.demo.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityWidgetBinding

class WidgetActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityWidgetBinding
    lateinit var dialogExtend: CusDialogExtend
    lateinit var dialogInstance: CusDialogInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_widget)
        dialogExtend = CusDialogExtend(this)
        dialogInstance = CusDialogInstance(this)
        dataBinding.btnShowDialog.setOnClickListener{
//            dialogExtend.show()
            dialogInstance.show()
        }
    }
}