package me.apqx.demo

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import me.apqx.demo.databinding.ActivityMainBinding
import me.apqx.demo.device.usb.UsbActivity
import me.apqx.demo.ipc.IpcActivity
import me.apqx.demo.jetpack.JetpackActivity
import me.apqx.demo.mvvm.view.MVVMActivity
import me.apqx.demo.realm.RealmActivity
import me.apqx.demo.service.ServiceActivity
import me.apqx.demo.test.TestActivity
import me.apqx.demo.tools.ToolsActivity
import me.apqx.demo.widget.WidgetActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private var input = ""
    private var lastInputTimeMills = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(str: String) {
        LogUtil.d("EventBus onEvent $str")
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_jetpack -> {
                startActivity(Intent(this, JetpackActivity::class.java))
            }
            R.id.btn_ipc -> {
                startActivity(Intent(this, IpcActivity::class.java))
            }
            R.id.btn_tools -> {
                startActivity(Intent(this, ToolsActivity::class.java))
            }
            R.id.btn_test -> {
                startActivity(Intent(this, TestActivity::class.java))
            }
            R.id.btn_mvvm -> {
                startActivity(Intent(this, MVVMActivity::class.java))
            }
            R.id.btn_widget -> {
                startActivity(Intent(this, WidgetActivity::class.java))
            }
            R.id.btn_service -> {
                startActivity(Intent(this, ServiceActivity::class.java))
            }
            R.id.btn_realm -> {
                startActivity(Intent(this, RealmActivity::class.java))
            }
            R.id.btn_usb -> {
                startActivity(Intent(this, UsbActivity::class.java))
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 监听键盘输入输入内容
        val currentTime = System.currentTimeMillis()
        LogUtil.d("input key = ${event?.characters}")
        // 只监听数字
        if (keyCode in 7..16) {
            if (currentTime - lastInputTimeMills < 500) {
                // 输入间隔小于500ms，判定为一次连续输入
                input += keyCode - 7
                LogUtil.d("continue input, curInput = $input")
            } else {
                // 输入间隔大于500ms，判定为一次新的输入
                input = (keyCode - 7).toString()
                LogUtil.d("new input, curInput = $input")
            }
            tv_hint.text = input
            lastInputTimeMills = currentTime
        } else {
            LogUtil.d("input not num, do nothing")
        }
        return super.onKeyDown(keyCode, event)
    }
}