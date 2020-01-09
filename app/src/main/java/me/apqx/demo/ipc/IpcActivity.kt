package me.apqx.demo.ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.apqx.demo.tools.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.ActivityIpcBinding
import me.apqx.demo.ipc.aidl.IBookManager
import me.apqx.demo.ipc.aidl.ICallback
import me.apqx.demo.ipc.aidl.bean.Book

class IpcActivity : AppCompatActivity() {
    var iBookManager: IBookManager? = null
    var dataBinding : ActivityIpcBinding? = null

    val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtil.d("onServiceDisconnect")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtil.d("onServiceConnect")
            iBookManager = IBookManager.Stub.asInterface(service)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_ipc)

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_bind -> {
                bindService(Intent(this, RemoteService::class.java), serviceConnection, Context.BIND_AUTO_CREATE)
            }
            R.id.btn_unbind -> {
                unbindService(serviceConnection)
            }
            R.id.btn_add -> {
                val dbList = iBookManager!!.getBooks()
//                val publisher = Publisher(0, "demoPublisher")
                val list = Array(2) {
//                    Book(dbList.size + it, "book$it", 10, publisher)
                    Book(dbList.size + it, "book$it", 10)
                }.asList()
                LogUtil.d("btn add = $list")
                iBookManager?.addBook(list)
            }
            R.id.btn_delete -> {
                LogUtil.d("btn clear")
                iBookManager?.clearBooks()
            }
            R.id.btn_getAll -> {
                LogUtil.d("btn getAll")
                val list = iBookManager?.getBooks()
                runOnUiThread {
                    dataBinding!!.tvResult.text = list.toString()
                }
            }
            R.id.btn_callback -> {
                LogUtil.d("btn callback")
                iBookManager!!.registerCallBack(object : ICallback.Stub() {
                    override fun showToast(str: String?) {
                        runOnUiThread {
                            Toast.makeText(this@IpcActivity, str, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}