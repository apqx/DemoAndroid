package me.apqx.demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.apqx.libtools.view.DisplayUtil

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        DisplayUtil.setStatusBarTransparent(this)
    }


    public fun onClick(view: View) {
        when (view.id) {
            R.id.btn_light -> {
                DisplayUtil.setStatusDarkIcon(this, true)
                DisplayUtil.setStatusBarColor(this, Color.WHITE)
            }
            R.id.btn_dark -> {
                DisplayUtil.setStatusDarkIcon(this, false)
                DisplayUtil.setStatusBarColor(this, Color.GRAY)

            }
        }
    }
}