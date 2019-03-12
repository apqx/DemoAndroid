package me.apqx.demo.mvvm.test.mvc

import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.Toast
import me.apqx.demo.R

/**
 * 接收UI点击事件，并能控制UI
 */
class WeatherView(private var activity: Activity, onBtnClickListener: OnBtnClickListener) : Callback {
    private val btn = activity.findViewById<Button>(R.id.btn)

    init {
        // 收到点击事件
        btn.setOnClickListener {
            // 通知Controller执行操作
            onBtnClickListener.onClick()
        }
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    /**
     * UI显示天气
     */
    fun showWeather(weather: String) {
        Toast.makeText(activity, weather, Toast.LENGTH_SHORT).show()
    }

    override fun querySuccess(weather: String) {
        showWeather(weather)
    }
}

/**
 * 用于通知Controller点击事件的监听器
 */
interface OnBtnClickListener {
    fun onClick()
}