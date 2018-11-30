package me.apqx.demo.jetpack.workmanager

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityWorkmanagerBinding
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    lateinit var cusWorkRequest: WorkRequest
    lateinit var dataBinding: ActivityWorkmanagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_workmanager)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_do -> {
                // 配置任务限制条件
                val constraints = Constraints.Builder()
                        // 仅在充电时执行，即如果这是一个单次任务，启动时没有处于充电状态，则它不会被执行
                        .setRequiresCharging(true)
                        .build()
                val data = Data.Builder().putString("apqx", "Data").build()
                // 从Worker创建WorkRequest，一个WorkRequest只能执行一次
                cusWorkRequest = OneTimeWorkRequestBuilder<CusWorker>()
                        .setConstraints(constraints)
                         // 2秒后再执行
                        .setInitialDelay(2, TimeUnit.SECONDS)
                        .build()
                // 最小间隔15分钟，低于这个值可能会有问题
//                cusWorkRequest = PeriodicWorkRequestBuilder<CusWorker>(15, TimeUnit.MINUTES)
//                        .setInputData(data)
//                        .build()
                WorkManager.getInstance().getWorkInfoByIdLiveData(cusWorkRequest.id)
                        .observe(this, Observer {
                            // 监听任务执行状态WorkInfo
                            LogUtil.d("workInfo ${it.state} ${it.outputData.getString("apqx")}")
                        })
                // 如果不定义Constraints，则任务会立即执行，
                WorkManager.getInstance().enqueue(cusWorkRequest)
            }
            R.id.btn_stop -> {
                // WorkInfo会被标记为Canceled，如果任务还未开始，则立即终止任务，如果任务已经开始，工作线程会并不会被中断
                WorkManager.getInstance().cancelWorkById(cusWorkRequest.id)
            }
        }
    }
}