package me.apqx.demo.old.tools

import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityToolsBinding


class ToolsActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityToolsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<ActivityToolsBinding>(this, R.layout.activity_tools)
        Thread{
            dataBinding.tvInfo.setText(Thread.currentThread().toString())
        }.start()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_refresh -> {
                if (this::dataBinding.isInitialized) {
                    dataBinding.tvInfo.text = getSysInfo()
                }
            }
        }
    }

    private fun getSysInfo(): String {
        var info = ""
        info += getScreenSize()

        return info
    }

    private fun getScreenSize(): String {
        val disPlay = windowManager.defaultDisplay
        val point = Point()
        disPlay.getSize(point)
        val widthPx = point.x
        val heightPx = point.y
        val dm = resources.displayMetrics;
        return "screenWidth = ${point.x}\n" +
                "screenHeight = ${point.y}\n" +
                "widthDp = ${dm.widthPixels}\n" +
                "heightDp = ${dm.heightPixels}\n" +
                "test = ${resources.getString(R.string.test)}"
                "dpi = ${resources.displayMetrics.densityDpi}\n"
    }
}