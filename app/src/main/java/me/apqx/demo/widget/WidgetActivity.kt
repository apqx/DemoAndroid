package me.apqx.demo.widget

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityWidgetBinding
import me.apqx.demo.widget.dialog.CusDialogExtend
import me.apqx.demo.widget.dialog.CusDialogInstance
import me.apqx.demo.widget.recycler.RecyclerActivity

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

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_recycler -> {
                startActivity(Intent(this, RecyclerActivity::class.java))
            }
        }
    }
}