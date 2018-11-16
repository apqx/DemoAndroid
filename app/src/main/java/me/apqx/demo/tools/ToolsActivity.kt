package me.apqx.demo.tools

import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityToolsBinding
import java.lang.reflect.AccessibleObject.setAccessible
import android.os.Build



class ToolsActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityToolsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<ActivityToolsBinding>(this, R.layout.activity_tools)
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
        return "screenWidth = ${point.x}\n" +
                "screenHeight = ${point.y}\n" +
                "dpi = ${resources.displayMetrics.densityDpi}\n" +
                "smallScreen = ${isInboxTypeMSmall()}"
    }

    fun isInboxTypeMSmall(): Boolean {
        var typeName = ""
        val fields = Build::class.java.declaredFields// 反射机制
        for (field in fields) {
            try {
                field.isAccessible = true
                if (field.name.contains("MODEL")) { //大屏
                    typeName = field.get("").toString()
                    break
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        if (typeName.contains("INBOX310")) return false
        if (typeName.contains("INPAD070")) return true
        if (typeName.contains("INBOX")) return false
        return if (typeName.contains("INPAD")) true else true
    }
}