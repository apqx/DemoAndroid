package me.apqx.demo.old.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import me.apqx.libtools.log.LogUtil


class Service2 : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtil.d("onStartCommand $this")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        LogUtil.d("onCreate $this")
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        LogUtil.d("onBind $this")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        LogUtil.d("onUnbind $this")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        LogUtil.d("onDestroy $this")
        super.onDestroy()
    }
}