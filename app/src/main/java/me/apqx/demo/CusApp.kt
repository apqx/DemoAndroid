package me.apqx.demo

import android.app.Application

class CusApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(applicationContext)
    }
}