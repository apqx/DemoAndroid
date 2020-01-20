package me.apqx.demo.old.mvvm.test.mvc

import android.app.Activity
import android.os.Bundle
import me.apqx.demo.R

/**
 * 接收UI事件，控制Model处理
 */
class WeatherActivity : Activity(), OnBtnClickListener {
    private lateinit var model: WeatherModel
    private lateinit var view: WeatherView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_databinding)
        view = WeatherView(this, this)
        model = WeatherModel(view)
    }

    /**
     * View被点击时，调用Model处理数据
     */
    override fun onClick() {
        model.queryWeather()
    }
}