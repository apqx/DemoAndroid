package me.apqx.demo

import android.util.Log

object LogUtil {
    fun d(str: String) {
        Log.d("apqx", str)
    }

    fun e(str: String) {
        Log.e("apqx", str)
    }
}