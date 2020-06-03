package me.apqx.demo.view

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_theme.*
import me.apqx.demo.R
import me.apqx.libbase.util.LogUtil

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_RED)
        setContentView(R.layout.activity_theme)
        tb_theme_night.setOnCheckedChangeListener { _, isChecked ->
            LogUtil.d("theme night = $isChecked")

        }
        recreate()
    }
}