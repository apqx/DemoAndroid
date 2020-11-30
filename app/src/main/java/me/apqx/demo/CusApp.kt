package me.apqx.demo

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import me.apqx.libtools.log.LogUtil
import me.apqx.libtools.notify.ToastUtil


class CusApp : MultiDexApplication() {

    private val actLifeCycleCallback = object : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
            LogUtil.i("activity life onActivityPaused $activity")
        }

        override fun onActivityResumed(activity: Activity?) {
            LogUtil.i("activity life onActivityResumed $activity")
        }

        override fun onActivityStarted(activity: Activity?) {
            LogUtil.i("activity life onActivityStarted $activity")
        }

        override fun onActivityDestroyed(activity: Activity?) {
            LogUtil.i("activity life onActivityDestroyed $activity")
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            LogUtil.i("activity life onActivitySaveInstanceState $activity")
        }

        override fun onActivityStopped(activity: Activity?) {
            LogUtil.i("activity life onActivityStopped $activity")
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            LogUtil.i("activity life onActivityCreated $activity")
        }
    }

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        registerActivityLifecycleCallbacks(actLifeCycleCallback)

        LogUtil.d("process = ${getCurrentProcessName()}")

//        screenShotManager.startListen()
//        screenShotManager.setListener(object : ScreenShotManager.OnScreenShotListener {
//            override fun onShot(imagePath: String?) {
//                val bitmap = BitmapFactory.decodeFile(imagePath)
//                LogUtil.d("screenShot ${bitmap.width} : ${bitmap.height}")
//                showPopWindow()
//            }
//        })

        ToastUtil.init(this)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        } else {
            LeakCanary.install(this)
        }

        readManifest()

    }

    private fun getCurrentProcessName(): String {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        for (appProcess in activityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return "Process Not Found"
    }

    /**
     * 读取AndroidManifest文件
     */
    private fun readManifest() {
        val applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        val channel = applicationInfo.metaData.getString("channel")
        LogUtil.e("READ ANDROID_MANIFEST chanel = $channel")
    }

    override fun onTerminate() {
        super.onTerminate()
//        screenShotManager.stopListen()
        unregisterActivityLifecycleCallbacks(actLifeCycleCallback)
    }
}