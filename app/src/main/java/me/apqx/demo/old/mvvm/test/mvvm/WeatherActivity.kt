package me.apqx.demo.old.mvvm.test.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 使用DataBinding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        // 创建ViewModel，LiveData就保存在ViewModel中
        val weatherModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        // 绑定视图和数据
        dataBinding.weather = weatherModel.weatherLiveData.value
        // 当数据发生变化时，这里会得到通知
        weatherModel.weatherLiveData.observe(this, Observer<String> {
            // 弹出天气信息，注意这里的回调方法运行在LiveData发生变化的线程里
            Toast.makeText(this, weatherModel.weatherLiveData.value, Toast.LENGTH_SHORT).show()
        })
        // DataBinding可以直接使用id获取View实例
        dataBinding.btn.setOnClickListener {
            // 调用ViewModel执行点击操作
            weatherModel.queryWeather()
        }
    }
}