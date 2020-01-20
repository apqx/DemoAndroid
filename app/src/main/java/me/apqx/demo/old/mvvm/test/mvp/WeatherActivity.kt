package me.apqx.demo.old.mvvm.test.mvp

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_livedata.*
import me.apqx.demo.R

/**
 * Activity实现操作UI的接口
 */
class WeatherActivity : Activity(), IWeatherActivity {
    private lateinit var iWeatherPresenter: IWeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_databinding)
        // 创建Presenter
        iWeatherPresenter = WeatherPresenter(this)
        btn_add.setOnClickListener {
            // 点击事件发生时，调用Presenter执行操作
            iWeatherPresenter.queryWeather()
        }
    }

    override fun showWeather(weather: String) {

    }


}

/**
 * 定义操作UI的接口
 */
interface IWeatherActivity {
    fun showWeather(weather: String)
}