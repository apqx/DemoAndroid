package me.apqx.demo.widget

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_widget.*
import kotlinx.android.synthetic.main.layout_tab.view.*
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityWidgetBinding
import me.apqx.demo.widget.dialog.CusDialogExtend
import me.apqx.demo.widget.dialog.CusDialogInstance
import me.apqx.demo.widget.list.ListActivity
import me.apqx.demo.widget.recycler.RecyclerActivity
import me.apqx.demo.widget.view.OnTabSelectListener
import me.apqx.demo.widget.view.RelativeActivity
import me.apqx.demo.widget.view.TabBean
import java.util.RandomAccess
import kotlin.random.Random

class WidgetActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityWidgetBinding
    lateinit var dialogExtend: CusDialogExtend
    lateinit var dialogInstance: CusDialogInstance
    var tabList = ArrayList<TabBean<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_widget)
        dialogExtend = CusDialogExtend(this)
        dialogInstance = CusDialogInstance(this)
        dataBinding.btnShowDialog.setOnClickListener{
//            dialogExtend.showAsDropDown()
            dialogInstance.show()
        }
        LogUtil.d("${ViewConfiguration.get(this).scaledTouchSlop}")


        refreshTab(8)
        dataBinding.inTab.dropdown_tab.setOnTabSelectListener(object : OnTabSelectListener<String> {
            override fun onTabClick(t: String?) {
                LogUtil.d("----onTabClick $t")
            }

            override fun onEditClick() {
                LogUtil.d("----onEditClick")
            }
        })


        cs_switcher.setTabText("美元", "元")
        cs_switcher.setOnSwitcherSelectListener {
            LogUtil.d(if (it) "select left" else "select right")
        }

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_recycler -> {
                startActivity(Intent(this, RecyclerActivity::class.java))
            }
            R.id.btn_relative -> {
                startActivity(Intent(this, RelativeActivity::class.java))
            }
            R.id.btn_list -> {
                startActivity(Intent(this, ListActivity::class.java))
            }
            R.id.btn_refreshTab -> {
                refreshTab()
                changeSwitcherColor()
            }
        }
    }


    var green = false
    private fun changeSwitcherColor() {
        if (green) {
            fl_switcherContainer.setBackgroundColor(Color.RED)
            cs_switcher.setSelectedTextColor(Color.RED)
        } else {
            fl_switcherContainer.setBackgroundColor(Color.GREEN)
            cs_switcher.setSelectedTextColor(Color.GREEN)
        }
        green = !green
    }

    private fun refreshTab(vararg countArray: Int) {
        var tabCount = 0
        if (countArray.isEmpty()) {
            tabCount = Random(SystemClock.currentThreadTimeMillis()).nextInt(1, 8)
        } else{
            tabCount = countArray[0]
        }
        tabList.clear()
        for (i in 0 .. tabCount) {
            tabList.add(TabBean("tab$i", "tab$i"))
        }
        dataBinding.inTab.dropdown_tab.setData(tabList)
    }
}