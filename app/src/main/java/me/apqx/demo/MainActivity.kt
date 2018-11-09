package me.apqx.demo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.demo.jetpack.JetpackActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val handler = Handler()
        handler.postAtTime({}, 1000)
    }

    fun onClickJetpack(view: View) {
        startActivity(Intent(this, JetpackActivity::class.java))
    }
}