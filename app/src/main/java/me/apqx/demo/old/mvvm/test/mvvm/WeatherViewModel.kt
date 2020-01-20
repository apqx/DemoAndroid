package me.apqx.demo.old.mvvm.test.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 执行数据查询并报告结果
 */
class WeatherViewModel : ViewModel() {
    private val weatherModel = WeatherModel()

    // LiveData保存天气数据
    var weatherLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        // 初始化数据
        weatherLiveData.value = ""
    }

    fun queryWeather() {
        val weather = weatherModel.queryWeather()
        weatherLiveData.value =  "clear"
    }
}
