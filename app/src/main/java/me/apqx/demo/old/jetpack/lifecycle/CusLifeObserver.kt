package me.apqx.demo.old.jetpack.lifecycle

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import me.apqx.libtools.log.LogUtil


class CusLifeObserver(val context: Context) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Toast.makeText(context, "ON_CREATE", Toast.LENGTH_SHORT).show()
        LogUtil.d("ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Toast.makeText(context, "ON_START", Toast.LENGTH_SHORT).show()
        LogUtil.d("ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Toast.makeText(context, "ON_RESUME", Toast.LENGTH_SHORT).show()
        LogUtil.d("ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Toast.makeText(context, "ON_PAUSE", Toast.LENGTH_SHORT).show()
        LogUtil.d("ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Toast.makeText(context, "ON_STOP", Toast.LENGTH_SHORT).show()
        LogUtil.d("ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Toast.makeText(context, "ON_DESTROY", Toast.LENGTH_SHORT).show()
        LogUtil.d("ON_DESTROY")
    }
}