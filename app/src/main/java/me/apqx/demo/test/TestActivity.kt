package me.apqx.demo.test

import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_test.*
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.Inet4Address
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        for (i in 0..4) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
            tv_tab.addTab(tv_tab.newTab().setCustomView(R.layout.item_tab))
        }
        wv_web.loadUrl("https://baidu.com")
        wv_web.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                LogUtil.d("onProgressChanged $newProgress")
            }

            override fun onJsTimeout(): Boolean {
                return super.onJsTimeout()
            }
        }
    }

    fun onClick(view: View) {
//        tv_tab.removeTabAt(tv_tab.tabCount - 1)
        for (file in File("/data/").listFiles()) {
            LogUtil.d(file.absolutePath)
        }
    }
}
