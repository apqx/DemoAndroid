package me.apqx.demo.old.jetpack.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.old.tools.ToastUtil

class CusWorker(val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        LogUtil.d("doWork start ${inputData.getString("apqx")}")
        ToastUtil.showToast("doWork start")
        Thread.sleep(2000)
        LogUtil.d("doWork done")
        ToastUtil.showToast("doWork done")
        val data = Data.Builder().putString("apqx", "result").build()
//        outputData = data

        return Result.success()
    }
}