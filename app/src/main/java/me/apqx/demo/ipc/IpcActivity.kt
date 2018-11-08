package me.apqx.demo.ipc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityIpcBinding

class IpcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityIpcBinding>(this, R.layout.activity_ipc)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_bind -> {

            }
            R.id.btn_unbind -> {

            }
            R.id.btn_send -> {

            }
            R.id.btn_invoke -> {

            }
        }
    }
}