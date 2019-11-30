package me.apqx.demo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration
import me.apqx.demo.realm.CusRealmMigration

class CusApp : MultiDexApplication() {
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
            LogUtil.d("onActivityCreated ${activity?.javaClass?.simpleName}")
        }
    }
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(actLifeCycleCallback)

        ToastUtil.init(applicationContext)
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
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterActivityLifecycleCallbacks(actLifeCycleCallback)
    }
}