package me.apqx.demo.old.jetpack

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityJetpackBinding
import me.apqx.demo.old.jetpack.databinding.DataBindingActivity
import me.apqx.demo.old.jetpack.lifecycle.LifecycleActivity
import me.apqx.demo.old.jetpack.livedata.LiveDataActivity

class JetpackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityJetpackBinding = DataBindingUtil.setContentView<ActivityJetpackBinding>(this, R.layout.activity_jetpack)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_dataBinding -> {
                startActivity(Intent(this, DataBindingActivity::class.java))
            }
            R.id.btn_lifecycle -> {
                startActivity(Intent(this, LifecycleActivity::class.java))
            }
            R.id.btn_livedata -> {
                startActivity(Intent(this, LiveDataActivity::class.java))
            }
            R.id.btn_workManager -> {
            }
        }
    }
}

