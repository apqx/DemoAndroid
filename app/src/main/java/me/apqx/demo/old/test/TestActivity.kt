package me.apqx.demo.old.test

import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.R
import java.io.File

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        Thread{
            for (i in 0 until 1000) {
                SystemClock.sleep(1000)
                println(this)
            }
        }.start()
    }

    fun onClick(view: View) {
        for (file in File("/data/").listFiles()) {
            LogUtil.d(file.absolutePath)
        }
    }
}
