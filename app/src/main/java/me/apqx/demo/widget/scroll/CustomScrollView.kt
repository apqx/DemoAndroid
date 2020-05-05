package me.apqx.demo.widget.scroll

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.ScrollView
import me.apqx.demo.R
import me.apqx.libbase.util.LogUtil
import kotlin.math.roundToInt

class CustomScrollView : ScrollView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val maxTranslationY = 100
    private lateinit var imageView: ImageView

    private val START_BY_TOP = 1
    private val START_BY_BOTTOM = 2
    private val START_BY_BOTH = 2

    var startY = 0f
    var startBy = 0
    var ivOriginalHeight = 0
    var parentOriginalHeight = 0

    lateinit var valueAnimator: ValueAnimator

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {


        return super.onInterceptTouchEvent(ev)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (!this::imageView.isInitialized) {
            imageView = findViewById(R.id.imageView)
            ivOriginalHeight = imageView.height
            parentOriginalHeight = getChildAt(0).height
            LogUtil.d("ivOriginalHeight = $ivOriginalHeight")
        }
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                stopAnim()
                if (isAtTop() && isAtBottom()) {
                    startY = ev.y
                    startBy = START_BY_BOTH
                } else if (isAtTop()) {
                    startY = ev.y
                    startBy = START_BY_TOP
                } else if (isAtBottom()) {
                    startY = ev.y
                    startBy = START_BY_BOTTOM
                } else {
                    startY = -1f
                }
                LogUtil.d("action_down ${ev.y}")
            }
            MotionEvent.ACTION_MOVE -> {
                LogUtil.d("scrollY=$scrollY, height=$height, itemHeight=${getChildAt(0).height}")
                LogUtil.d("isTop ${isAtTop()} : isBottom ${isAtBottom()}")
                if (startY == -1f) {
                    if (isAtTop() && isAtBottom()) {
                        startY = ev.y
                        startBy = START_BY_BOTH
                    } else if (isAtTop()) {
                        startY = ev.y
                        startBy = START_BY_TOP
                    } else if (isAtBottom()) {
                        startY = ev.y
                        startBy = START_BY_BOTTOM
                    }
                } else {
                    if (isAtTop() && isAtBottom()) {

                    } else if (isAtTop() && startBy != START_BY_TOP) {
                        startY = ev.y
                        startBy = START_BY_TOP
                    } else if (isAtBottom() && startBy != START_BY_BOTTOM) {
                        startY = ev.y
                        startBy = START_BY_BOTTOM
                    }
                }
                if (startY != -1f) {
                    val gap = ev.y - startY
                    LogUtil.d("action_move ${ev.y} : startY=$startY gap=$gap")
                    if (isAtTop() && gap > 0) {
                        // 从顶部向下滑动
                        resizeImgHeight(ivOriginalHeight + generateTransY(gap))
                        return false
                    } else if (isAtBottom() && gap < 0) {
                        // 从底部向上滑
                        resizeImgHeight(ivOriginalHeight - generateTransY(-gap))
                        return false
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                // 使用动画恢复尺寸
                if (imageView.height != ivOriginalHeight)
                    startAnim()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isAtBottom() = scrollY + height == parentOriginalHeight
            || height >= parentOriginalHeight

    private fun isAtTop() = scrollY == 0

    private fun startAnim() {
        stopAnim()
        valueAnimator = ValueAnimator.ofInt(imageView.height, ivOriginalHeight)
        valueAnimator.interpolator = OvershootInterpolator()
        valueAnimator.addUpdateListener {
            resizeImgHeight(it.animatedValue as Int)
            LogUtil.d("${it.animatedFraction} : ${it.animatedValue}")
        }
        valueAnimator.setDuration(600)
        valueAnimator.start()
    }

    private fun stopAnim() {
        if (this::valueAnimator.isInitialized) {
            valueAnimator.cancel()
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {

        return super.onTouchEvent(ev)
    }

    private fun generateTransY(gap: Float): Int {
        var result = Math.sqrt(gap.toDouble() * maxTranslationY)
        LogUtil.d("gap=$gap, result=$result")
        return result.roundToInt();
//        return gap.toInt()
    }

    private fun resizeImgHeight(height: Int) {
        val layoutParams = imageView.layoutParams
        layoutParams.height = height
        imageView.layoutParams = layoutParams
    }
}