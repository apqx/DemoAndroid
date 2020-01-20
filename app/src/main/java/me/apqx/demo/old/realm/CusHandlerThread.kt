package me.apqx.demo.old.realm

import android.os.Looper

class CusHandlerThread : Thread() {
    override fun run() {
        Looper.prepare()

        Looper.loop()
    }
}