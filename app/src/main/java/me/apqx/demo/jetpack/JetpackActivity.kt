package me.apqx.demo.jetpack

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityJetpackBinding
import me.apqx.demo.jetpack.navigation.NavActivity

class JetpackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityJetpackBinding = DataBindingUtil.setContentView<ActivityJetpackBinding>(this, R.layout.activity_jetpack)
    }

    fun onClickNavigation(view: View) {
        startActivity(Intent(this, NavActivity::class.java))
    }
}

