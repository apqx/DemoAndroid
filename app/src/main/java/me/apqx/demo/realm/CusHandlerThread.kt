package me.apqx.demo.realm

import android.os.Looper

class CusHandlerThread : Thread() {
    override fun run() {
        Looper.prepare()

        Looper.loop()
    }
}