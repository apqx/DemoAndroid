package me.apqx.demo.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityLifecycleBinding

/**
 * AppCompatActivity已经实现了LifecycleOwner接口，可以直接使用Lifecycle
 */
class LifecycleActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityLifecycleBinding>(this, R.layout.activity_lifecycle)
        lifecycle.addObserver(CusLifeObserver(this))
    }
}