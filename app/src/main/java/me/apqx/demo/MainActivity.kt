package me.apqx.demo

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import me.apqx.demo.bean.Student
import me.apqx.demo.databinding.ActivityMainBinding

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val tom = Student("tom", 10)
        binding.student = tom
    }
}