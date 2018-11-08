package me.apqx.demo.ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {
    override fun onBind(intent: Intent?): IBinder? {

    }
}