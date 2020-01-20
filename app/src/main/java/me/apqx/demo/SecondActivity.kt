package me.apqx.demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.apqx.demo.widget.view.DisplayUtils

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        DisplayUtils.setStatusBarTransparent(this)
    }


    public fun onClick(view: View) {
        when (view.id) {
            R.id.btn_light -> {
                DisplayUtils.setStatusDarkIcon(this, true)
                DisplayUtils.setStatusBarColor(this, Color.WHITE)
            }
            R.id.btn_dark -> {
                DisplayUtils.setStatusDarkIcon(this, false)
                DisplayUtils.setStatusBarColor(this, Color.GRAY)

            }
        }
    }
}