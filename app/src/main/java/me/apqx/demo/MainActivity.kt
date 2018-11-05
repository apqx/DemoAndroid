package me.apqx.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.demo.jetpack.JetpackActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    fun onClickJetpack(view: View) {
        startActivity(Intent(this, JetpackActivity::class.java))
    }
}