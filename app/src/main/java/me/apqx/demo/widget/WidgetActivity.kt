package me.apqx.demo.widget

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityWidgetBinding
import me.apqx.demo.widget.dialog.CusDialogExtend
import me.apqx.demo.widget.dialog.CusDialogInstance
import me.apqx.demo.widget.list.ListActivity
import me.apqx.demo.widget.recycler.RecyclerActivity
import me.apqx.demo.widget.view.RelativeActivity

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
        LogUtil.d("${ViewConfiguration.get(this).scaledTouchSlop}")

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_recycler -> {
                startActivity(Intent(this, RecyclerActivity::class.java))
            }
            R.id.btn_relative -> {
                startActivity(Intent(this, RelativeActivity::class.java))
            }
            R.id.btn_list -> {
                startActivity(Intent(this, ListActivity::class.java))
            }
        }
    }
}