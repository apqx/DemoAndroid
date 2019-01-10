package me.apqx.demo.test

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import me.apqx.demo.R

class TestActivity : AppCompatActivity() {
    lateinit var  tv_tab: TabLayout;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tv_tab = findViewById<TabLayout>(R.id.tv_tab)
        for (i in 0..4) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
            tv_tab.addTab(tv_tab.newTab().setCustomView(R.layout.item_tab))
        }

    }

    fun onClick(view: View) {
        tv_tab.removeTabAt(tv_tab.tabCount - 1)
    }
}
