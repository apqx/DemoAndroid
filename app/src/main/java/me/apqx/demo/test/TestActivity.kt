package me.apqx.demo.test

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.apqx.demo.R

class TestActivity : AppCompatActivity() {

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val handlerThread = HandlerThread("")
        val handler = Handler(handlerThread.looper)
        handlerThread.start()
        handler.post{

        }
    }
}