package me.apqx.demo.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import me.apqx.libbase.util.LogUtil
import kotlin.random.Random

class SimplePagerAdapter(val context: Context) : PagerAdapter() {
    private val list: MutableList<View> = generateList()

    override fun getCount(): Int {
        LogUtil.d("getCount ${list.size}")
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        LogUtil.d("instantiateItem ${list.size} -> $position")
        val view = list[position]
        container.addView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        LogUtil.d("destroyItem ${list.size} -> $position")
        container.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getItemPosition(`object`: Any): Int {
        // 返回POSITION_NONE表示，当执行notifyDataSetChanged()时，所有View会被销毁重建，否则View是不会刷新的
        return POSITION_NONE
    }

    fun newData() {
        list.clear()
        list.addAll(generateList())
        LogUtil.d("newData ${list.size}")
        notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        LogUtil.d("notifyDataSetChanged ${list.size}")
    }

    private fun generateList() : MutableList<View> {
        val list = ArrayList<View>()
        for (i in 0 until Random(System.currentTimeMillis()).nextInt(4, 10)) {
            val view = TextView(context)
            view.text = "$i"
            view.gravity = Gravity.CENTER
            view.setBackgroundColor(if (i % 2 == 0) Color.RED else Color.GREEN)
            list.add(view)
        }
        return list
    }
}