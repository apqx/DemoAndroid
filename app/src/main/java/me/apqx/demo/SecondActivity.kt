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
        DisplayUtils.dealStatusBarTransparent(this, false)
    }


    public fun onClick(view: View) {
        when (view.id) {
            R.id.btn_change -> {
                DisplayUtils.dealStatusBarTransparent(this, true)
            }
        }
    }
}