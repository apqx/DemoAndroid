package me.apqx.demo

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration
import me.apqx.demo.realm.CusRealmMigration
import me.apqx.demo.tools.ScreenShotManager

class CusApp : MultiDexApplication() {
//    val screenShotManager = ScreenShotManager.newInstance(this)

    private val actLifeCycleCallback = object : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
            LogUtil.d("onActivityPaused ${activity?.javaClass?.simpleName}")
        }

        override fun onActivityResumed(activity: Activity?) {
            LogUtil.d("onActivityResumed ${activity?.javaClass?.simpleName}")
        }

        override fun onActivityStarted(activity: Activity?) {
            LogUtil.d("onActivityStarted ${activity?.javaClass?.simpleName}")
        }

        override fun onActivityDestroyed(activity: Activity?) {
            LogUtil.d("onActivityDestroyed ${activity?.javaClass?.simpleName}")
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            LogUtil.d("onActivitySaveInstanceState ${activity?.javaClass?.simpleName}")
        }

        override fun onActivityStopped(activity: Activity?) {
            LogUtil.d("onActivityStopped ${activity?.javaClass?.simpleName}")
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            LogUtil.d("onActivityCreated $activity")
        }
    }
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(actLifeCycleCallback)

//        screenShotManager.startListen()
//        screenShotManager.setListener(object : ScreenShotManager.OnScreenShotListener {
//            override fun onShot(imagePath: String?) {
//                val bitmap = BitmapFactory.decodeFile(imagePath)
//                LogUtil.d("screenShot ${bitmap.width} : ${bitmap.height}")
//                showPopWindow()
//            }
//        })

        ToastUtil.init(this)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .migration(CusRealmMigration())
                .name("defRealm")
                .build()
        Realm.setDefaultConfiguration(config)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        } else {
            LeakCanary.install(this)
        }

        readManifest()

    }

    /**
     * 读取AndroidManifest文件
     */
    private fun readManifest() {
        val applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        val channel = applicationInfo.metaData.getString("channel")
        LogUtil.e("READ ANDROID_MANIFEST chanel = $channel")
    }

    private fun showPopWindow() {
        val view = LayoutInflater.from(this).inflate(R.layout.window_pop, null, false)
        val popWindow = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onTerminate() {
        super.onTerminate()
//        screenShotManager.stopListen()
        unregisterActivityLifecycleCallbacks(actLifeCycleCallback)
    }
}