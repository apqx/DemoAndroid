package me.apqx.demo.ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.room.Room

import me.apqx.demo.ipc.aidl.IBookManager
import me.apqx.demo.ipc.aidl.ICallback
import me.apqx.demo.ipc.aidl.bean.Book
import me.apqx.demo.ipc.room.IpcDataBase
import me.apqx.libtools.log.LogUtil

class RemoteService : Service() {
    var db: IpcDataBase? = null
    val mBinder = object : IBookManager.Stub() {

        @Synchronized
        override fun getBooks(): List<Book> {
            LogUtil.d("server getBooks")
            return db!!.bookDao().getAll()
        }

        @Synchronized
        override fun addBook(books: List<Book>) {
            LogUtil.d("server addBook")
            db!!.bookDao().insertAll(books)
        }

        @Synchronized
        override fun clearBooks() {
            LogUtil.d("server clearBooks")
            db!!.bookDao().getAll().forEach {
                db!!.bookDao().delete(it)
            }
        }

        override fun registerCallBack(iCallback: ICallback?) {
            LogUtil.d("server registerCallBack")
            iCallback?.showToast("callback")
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, IpcDataBase::class.java, "ipcDb.db").build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        LogUtil.d("service onBind")
        return mBinder
    }
}