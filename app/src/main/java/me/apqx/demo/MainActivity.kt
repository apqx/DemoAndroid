package me.apqx.demo

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import me.apqx.demo.jetpack.bean.Student
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.demo.jetpack.view.JetpackActivity

class MainActivity: Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnJetpack.setOnClickListener{
            startActivity(Intent(this, JetpackActivity::class.java))
        }
    }
}