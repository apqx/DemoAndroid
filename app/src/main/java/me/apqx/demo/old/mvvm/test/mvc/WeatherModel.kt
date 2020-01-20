package me.apqx.demo.old.mvvm.test.mvc

/**
 * 执行数据查询并异步报告结果
 */
class WeatherModel(private val callBack: Callback) {

    fun queryWeather() {
        val weather =  "clear"
        callBack.querySuccess(weather)
    }
}

/**
 * 结果回调接口
 */
interface Callback {
    fun querySuccess(weather: String)
}