package me.apqx.demo.widget

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.ViewConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
    var dropList = ArrayList<TabBean<String>>()

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


        tabList.add(TabBean("tab0", "tab0"))
        tabList.add(TabBean("tab1", "tab1"))
        tabList.add(TabBean("tab2", "tab2"))
        tabList.add(TabBean("tab3", "tab3"))
        tabList.add(TabBean("tab4", "tab4"))

        dropList.add(TabBean("drop0", "drop1"))
        dropList.add(TabBean("drop1", "drop2"))
        dropList.add(TabBean("drop2", "drop3"))
        dropList.add(TabBean("drop3", "drop4"))
        dataBinding.inTab.dropdown_tab.setData(tabList, dropList)
        dataBinding.inTab.dropdown_tab.setOnTabSelectListener(object : OnTabSelectListener<String> {
            override fun onTabClick(t: String?) {
                LogUtil.d("----onTabClick $t")
            }

            override fun onEditClick() {
                LogUtil.d("----onEditClick")
            }
        })
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
            }
        }
    }

    private fun refreshTab() {
        val tabCount = Random(SystemClock.currentThreadTimeMillis()).nextInt(1, 5)
        tabList.clear()
        for (i in 0 .. tabCount) {
            tabList.add(TabBean("tab$i", "tab$i"))
        }

        dropList.clear()
        for (i in 0 .. tabCount) {
            dropList.add(TabBean("drop$i", "drop$i"))
        }
        dataBinding.inTab.dropdown_tab.setData(tabList, dropList)
    }
}