package me.apqx.demo.old.mvvm.test.mvp

/**
 * 实现接口，构造器要求传入View以操作UI
 */
class WeatherPresenter(private val iWeatherActivity: IWeatherActivity) : IWeatherPresenter {

    /**
     * 具体的执行点击事件
     */
    override fun queryWeather() {
        val weatherModel = WeatherModel()
        // 调用Model获取数据，然后跳用View更新UI
        iWeatherActivity.showWeather(weatherModel.queryWeather())
    }
}

/**
 * 定义响应UI事件的接口
 */
interface IWeatherPresenter {
    fun queryWeather()
}