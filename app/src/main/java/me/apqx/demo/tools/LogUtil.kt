package me.apqx.demo.tools

import android.util.Log

object LogUtil {
    private const val tag = "apqx"

    /**
     * 常规
     */
    fun d(str: String) {
        Log.d(tag, str)
    }

    /**
     * 异常错误
     */
    fun e(str: String) {
        Log.e(tag, str)
    }

    /**
     * 一些值得注意的事件
     * 1，生命周期
     */
    fun i(str: String) {
        Log.i(tag, str)
    }

    fun v(str: String) {
        Log.v(tag, str)
    }

    fun w(str: String) {
        Log.w(tag, str)
    }


}