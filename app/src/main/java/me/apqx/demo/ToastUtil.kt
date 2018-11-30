package me.apqx.demo

import android.content.Context
import android.os.Handler
import android.widget.Toast
import java.util.concurrent.CopyOnWriteArrayList

object ToastUtil {
    lateinit var handler: Handler
    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
        handler = Handler()
    }

    fun showToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    fun showToastAsync(string: String) {
        handler.post {
            showToast(string)
        }
    }
}