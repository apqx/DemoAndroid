package me.apqx.demo.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import me.apqx.libbase.util.CanvasUtil
import me.apqx.libbase.util.DisplayUtil
import me.apqx.libtools.log.LogUtil


class CustomDrawView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val bgPaint by lazy {
        Paint().apply {
            color = Color.RED
        }
    }

    private val textPaint by lazy {
        Paint().apply {
            color = Color.GREEN
            style = Paint.Style.STROKE
            isAntiAlias = true
            textSize = 90f
        }
    }

    private val textBoundsRect by lazy {
        Rect()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val str = "测试文本"

        textPaint.getTextBounds(str, 0, str.length, textBoundsRect)

        val textWidth = textBoundsRect.right - textBoundsRect.left
        val textHeight = textBoundsRect.bottom - textBoundsRect.top
        val padding = DisplayUtil.dpToPx(context, 10f)

        LogUtil.d("textBounds $textBoundsRect")
        canvas?.drawRect(0f, 0f, textWidth.toFloat() + padding * 2, textHeight.toFloat() + padding * 2, bgPaint)
        canvas?.drawText(str, CanvasUtil.getDrawTextCenterX(0, (textWidth.toFloat() + padding * 2).toInt(), textBoundsRect).toFloat()
                , CanvasUtil.getDrawTextCenterY(0, (textHeight.toFloat() + padding * 2).toInt(), textBoundsRect).toFloat(), textPaint)


    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }
}