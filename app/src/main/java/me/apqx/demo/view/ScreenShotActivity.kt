package me.apqx.demo.view

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_screenshot.*
import me.apqx.demo.R
import me.apqx.libtools.log.LogUtil
import me.apqx.libtools.view.DisplayUtil

class ScreenShotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screenshot)
        btn_screenshot.setOnClickListener {
            val bitmap = DisplayUtil.getScreenShot(this)
            LogUtil.d("screen = ${bitmap.width}:${bitmap.height}")
            LogUtil.d("iv = ${iv_screenshot.width}:${iv_screenshot.height}")
            iv_screenshot.setImageBitmap(bitmap)
            iv_screenshot.post {
                LogUtil.d("iv = ${iv_screenshot.width}:${iv_screenshot.height}")
            }
        }
    }
}