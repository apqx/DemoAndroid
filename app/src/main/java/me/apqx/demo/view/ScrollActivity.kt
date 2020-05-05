package me.apqx.demo.view

import android.animation.IntEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scroll.*
import kotlinx.android.synthetic.main.item_scroll_top.*
import me.apqx.demo.R
import me.apqx.libbase.util.LogUtil
import kotlin.math.roundToInt

class ScrollActivity : AppCompatActivity() {
    var startY = 0f
    var imgOriginalHeight = 0
    val maxTranslationY = 300;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
//        sv_scroll.post {
//            imgOriginalHeight = imageView.height
//        }
//        sv_scroll.setOnTouchListener { view, motionEvent ->
//            when (motionEvent.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    startY = motionEvent.rawY
//                    LogUtil.d("down y=${motionEvent.rawY}")
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    val gap = motionEvent.rawY - startY
//                    LogUtil.d("move y=${motionEvent.rawY} gap=$gap")
//                    if (sv_scroll.scrollY == 0 &&  gap >= 0) {
//                        // 从顶部向下滑动
//                        resizeImgHeight(imgOriginalHeight + generateTransY(gap))
//                        true
//                    }
//                }
//                MotionEvent.ACTION_UP -> {
//                    resizeImgHeight(imgOriginalHeight)
//                }
//            }
//            false
//        }
//        btn_scroll.setOnClickListener{
//            LogUtil.d("btn onclick")
//            val layoutParams = imageView.layoutParams
//            layoutParams.height = layoutParams.height + 100
//            imageView.layoutParams = layoutParams
//        }
    }

    private fun generateTransY(gap: Float): Int {
        var result = Math.sqrt(gap.toDouble() * maxTranslationY)
        LogUtil.d("gap=$gap, result=$result")
        return result.roundToInt();
    }

    private fun resizeImgHeight(height: Int) {
        val layoutParams = imageView.layoutParams
        layoutParams.height = height
        imageView.layoutParams = layoutParams
    }
}