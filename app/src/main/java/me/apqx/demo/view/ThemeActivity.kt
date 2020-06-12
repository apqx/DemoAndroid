package me.apqx.demo.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_theme.*
import me.apqx.demo.R

import me.apqx.libbase.util.ToastUtil
import me.apqx.libtools.log.LogUtil

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        btn_dark.setOnClickListener {
            // 执行了设置暗黑模式，如果主题和设置之前不一样，当前Activity会被自动重建，应用新的主题设置
            // 之前的Activity会在再次获取到焦点时，自动重建，应用新的主题设置
            // 新启动的Activity会自动使用新设置的主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        btn_day.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        btn_auto.setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

            } else {
                ToastUtil.showToast("System < Android 10")
            }
        }

        sw_switch.post {
            sw_switch.isChecked = isInDarkMode(this)
        }


        btn_new.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }


    }

    // 检查当前APP是否处于暗黑模式，
    fun isInDarkMode(context: Context): Boolean {
        val mode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val result = mode == Configuration.UI_MODE_NIGHT_YES
        LogUtil.d("isInDarkModel = $result")
        return result
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtil.e("onRestoreInstanceState")
    }
}