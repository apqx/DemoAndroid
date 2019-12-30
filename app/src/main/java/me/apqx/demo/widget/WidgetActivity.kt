package me.apqx.demo.widget

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_widget.*
import kotlinx.android.synthetic.main.layout_tab.view.*
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.ToastUtil
import me.apqx.demo.databinding.ActivityWidgetBinding
import me.apqx.demo.widget.dialog.CusDialogExtend
import me.apqx.demo.widget.dialog.CusDialogInstance
import me.apqx.demo.widget.list.ListActivity
import me.apqx.demo.widget.recycler.RecyclerActivity
import me.apqx.demo.widget.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.random.Random

const val REQUEST_CODE_OVERLAY_PERMISSION = 1

class WidgetActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityWidgetBinding
    private lateinit var dialogExtend: CusDialogExtend
    private lateinit var dialogInstance: CusDialogInstance
    private var tabList = ArrayList<TabBean<String>>()
    private lateinit var pagerAdapter: CusHorizontalAdapter

    lateinit var ftb: View
    lateinit var layoutParams: WindowManager.LayoutParams

    val strList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_widget)
        dialogExtend = CusDialogExtend(this)
        dialogInstance = CusDialogInstance(this)
        dataBinding.btnShowDialog.setOnClickListener {
            //            dialogExtend.showAsDropDown()
            dialogInstance.show()
        }
        LogUtil.d("${ViewConfiguration.get(this).scaledTouchSlop}")

        initTab()

        initCustomSwitcher()

        initClickableText()

        initHorizontalPager()

        setResult(100)

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                LogUtil.d("edit afterTextChanged $p0")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                LogUtil.d("edit beforeTextChanged $p0")

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                LogUtil.d("edit onTextChanged $p0")
                // 设置EditText的text会导致这里也会调用，出现递归

            }
        })
        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                LogUtil.d("edit onEditorAction ${p0?.text} $p1")
                if (p1 == EditorInfo.IME_ACTION_SEARCH || p1 == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    LogUtil.d("edit onEditorAction press search")
                    return true
                }
                return false
            }
        })


    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    private fun initHorizontalPager() {
        pagerAdapter = CusHorizontalAdapter(this, strList)
        in_hp.setAdapter(pagerAdapter)
        in_hp.setLoopAnimEnabled(true)

        for (i in 0 until 8) {
            strList.add(i.toString())
        }
    }

    private fun initClickableText() {
        var text = "尊敬的用户，在您使用金投网前，为了让您更好地了解我们是如何保护用户的个人信息的，请您仔细阅读《隐私政策》和《服务协议》。" +
                "\n" +
                "如您同意并接受全部条款，请点击\"同意\"开始接受我们的服务。"
        text = DisplayUtils.halfToFull(text)
        var spannableString = SpannableString(text)
        val regex1 = "《隐私政策》"
        val regex2 = "《服务协议》"
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                LogUtil.d("click $regex1")
                ToastUtil.showToast("click $regex1")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
            }
        }, text.indexOf(regex1), text.indexOf(regex1) + regex1.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                LogUtil.d("click $regex2")
                ToastUtil.showToast("click $regex2")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
            }
        }, text.indexOf(regex2), text.indexOf(regex2) + regex2.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        tv_clickableSpanString.text = spannableString
        tv_clickableSpanString.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun initCustomSwitcher() {
        cs_switcher.setTabText("美元", "元")
        cs_switcher.setOnSwitcherSelectListener {
            LogUtil.d(if (it) "select left" else "select right")
        }
    }

    private fun initTab() {
        refreshTab(3)
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
            R.id.btn_add -> {
                startActivity(Intent(this, RelativeActivity::class.java))
            }
            R.id.btn_list -> {
                startActivity(Intent(this, ListActivity::class.java))
            }
            R.id.btn_refreshTab -> {
                refreshTab()
                changeSwitcherColor()
                refreshGridPager()
            }
            R.id.btn_showFloating -> {
                showFloating()
            }
            R.id.btn_hideFloating -> {
                hideFloating()
            }
            R.id.btn_click -> {
                showToast()
                EventBus.getDefault().postSticky("stickyEvent")
                EventBus.getDefault().postSticky("stickyEven1")
                EventBus.getDefault().post("normalEvent")
            }
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(str: String) {
        LogUtil.d("${javaClass.simpleName} EventBus onEvent $str")
    }

    var count = 0
    private fun showToast() {
        ToastUtil.showToast(R.string.app_name)
    }

    private fun refreshGridPager() {
        for (i in 0 until strList.size) {
            strList[i] = strList[i] + strList[i]
        }

        pagerAdapter.notifyDataSetChanged()
    }

    private fun hideFloating() {
        if (this::ftb.isInitialized) {
            windowManager.removeViewImmediate(ftb)
        }
    }

    /**
     * 显示一个全局的浮动窗口，需要申请权限，如果是Application内的悬浮窗，则不需要申请权限，这是由type决定的
     */
    private fun showFloating() {
        if (!checkOverlayPermission()) {
            requestOverlayPermission()
            return
        }
        if (!this::ftb.isInitialized) {
//            val view = LayoutInflater.from(this).inflate(R.layout.window_pop, null, false)
            ftb = LayoutInflater.from(this).inflate(R.layout.window_pop, null, false)
            // 如果是应用内悬浮窗，type为TYPE_APPLICATION即可
            // 如果是全局悬浮窗，targetSdk在Android O之前，type为TYPE_SYSTEM_ALERT，Android O开始，为TYPE_APPLICATION_OVERLAY
//            val type = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
//            else WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            val type = WindowManager.LayoutParams.TYPE_APPLICATION
            val flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            // Window的坐标系似乎是屏幕中心，和某个设置有关
            layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT
                    , 0, 0, type, flags, PixelFormat.TRANSPARENT)

            ftb.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            LogUtil.d("click ftb ACTION_DOWN")
                        }
                        MotionEvent.ACTION_MOVE -> {
                            LogUtil.d("click ftb ACTION_MOVE")

                        }
                        MotionEvent.ACTION_UP -> {
                            LogUtil.d("click ftb ACTION_UP")

                        }
                    }
                    return true
                }
            })
        }
        // 显示悬浮窗
        windowManager.addView(ftb, layoutParams)
    }

    /**
     * 请求悬浮窗权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    private fun requestOverlayPermission() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        startActivityForResult(intent, REQUEST_CODE_OVERLAY_PERMISSION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_OVERLAY_PERMISSION -> {
                if (checkOverlayPermission()) {
                    ToastUtil.showToast("Get Overlay Permission")
                    showFloating()
                } else {
                    ToastUtil.showToast("Get Overlay Permission Failed!")
                }
            }
        }
    }

    /**
     * 检查是否具有悬浮窗权限</br>
     * Android M之前，只要在AndroidManifest中声明了android.permission.SYSTEM_ALERT_WINDOW，即有权限
     * Android M之后，必须手动请求用户授权
     */
    private fun checkOverlayPermission(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            true
        } else {
            // 这个方法只有在Android M之后的SDK才可以用，因为正是在M之后，才必须手动申请这个权限
            Settings.canDrawOverlays(this)
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
        } else {
            tabCount = countArray[0]
        }
        tabList.clear()
        for (i in 0 until tabCount) {
            tabList.add(TabBean("分k$i", "分k$i"))
        }
        dataBinding.inTab.dropdown_tab.setData(tabList)
    }
}