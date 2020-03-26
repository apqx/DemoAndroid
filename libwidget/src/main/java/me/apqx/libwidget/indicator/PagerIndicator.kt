package me.apqx.libwidget.indicator

import android.content.Context
import android.database.DataSetObserver
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import me.apqx.libbase.util.DisplayUtil
import me.apqx.libbase.util.LogUtil
import me.apqx.libwidget.R

class PagerIndicator : LinearLayout {
    private val tag = javaClass.simpleName

    private var viewPager: ViewPager? = null
    private var adapter: PagerAdapter? = null

    private var indicatorWidth = DisplayUtil.dpToPx(context, 4f)
    private var indicatorHeight = DisplayUtil.dpToPx(context, 2f)

    constructor(context: Context?) : super(context) {
        LogUtil.d("PagerIndicator constructor1")
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LogUtil.d("PagerIndicator constructor2")

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LogUtil.d("PagerIndicator constructor3")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    public fun bindViewPager(viewPager: ViewPager?) {
        if (viewPager == null) return
        if (this.viewPager == viewPager) return
        this.viewPager = viewPager
        bindViewPagerAdapter()
        viewPager.addOnPageChangeListener(pagerChangeListener)
        viewPager.addOnAdapterChangeListener(pagerAdapterChangeListener)
    }

    private fun bindViewPagerAdapter() {
        adapter = viewPager?.adapter
        if (adapter == null) return
        adapter!!.registerDataSetObserver(dataSetObserver)
        fillIndicator()
    }

    private fun fillIndicator() {
        removeAllViews()
        for (i in 0 until adapter!!.count) {
            val view = View(context)
            // 默认的高度为ViewGroup的高度，默认的宽度
            val layoutParams = LayoutParams(indicatorWidth, ViewGroup.LayoutParams.MATCH_PARENT)
            view.background = resources.getDrawable(R.drawable.selector_indicator_rect)
            view.isSelected = i == viewPager?.currentItem
            addView(view, layoutParams)
        }
    }

    /**
     * Adapter的数据源发生变化时，重新填充Indicator子View
     */
    private val dataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            fillIndicator()
        }
    }

    /**
     * Adapter实例发生变化时，使用新的Adapter
     */
    private val pagerAdapterChangeListener = object : ViewPager.OnAdapterChangeListener {
        override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
            LogUtil.d("$tag onAdapterChanged $oldAdapter : $newAdapter")
            adapter = newAdapter
            bindViewPagerAdapter()
        }
    }

    /**
     * ViewPager页面状态发生变化时，选中Indicator状态
     */
    private val pagerChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            LogUtil.d("$tag onPageSelected $position")
            for (i in 0 until childCount) {
                getChildAt(i).isSelected = i == position
            }
        }
    }
}