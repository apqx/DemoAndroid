package me.apqx.demo.old.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.R

class ServiceActivity : AppCompatActivity() {
    var cusIntent1: Intent? = null
    var cusIntent2: Intent? = null
    val serviceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtil.d("onServiceDisconnected ${name?.packageName}")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtil.d("onServiceConnected ${name?.packageName}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
    }

    fun onClick(view: View) {
        when(view.id) {
            R.id.btn_bind -> {
                LogUtil.d("bind")
                cusIntent1 = Intent(this, Service1::class.java)
                cusIntent2 = Intent(this, Service2::class.java)
                bindService(cusIntent1, serviceConnection, Service.BIND_AUTO_CREATE)
                bindService(cusIntent2, serviceConnection, Service.BIND_AUTO_CREATE)
            }

            R.id.btn_unbind -> {
                LogUtil.d("unbind")
                unbindService(serviceConnection)
            }
        }
    }
}