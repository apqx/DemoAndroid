package me.apqx.demo.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView

class CusBtn : androidx.appcompat.widget.AppCompatTextView {
    private lateinit var path: Path
    private val paint: Paint by lazy {
        Paint().apply {
            color = Color.RED
            isAntiAlias = true
        }
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
    }

    override fun draw(canvas: Canvas?) {
        if (!this::path.isInitialized) {
            path = Path()
            path.moveTo(10f, 0f)
            path.lineTo(width.toFloat() - 10f, 0f)
            path.lineTo((width - 40).toFloat(), height.toFloat())
            path.lineTo(0f, height.toFloat())
            path.close()
        }
        canvas!!.clipPath(path)
        super.draw(canvas)
    }
}