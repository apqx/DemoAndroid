package me.apqx.demo.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import me.apqx.demo.old.tools.LogUtil

class DemoViewModel : ViewModel() {
    var disposable: Disposable? = null

    val strList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadStrList()
        }
    }

    fun loadStrList() {
        LogUtil.d("DemoViewModel loadStrList")
        if (disposable != null && disposable?.isDisposed == false) {
            disposable?.dispose()
        }
        disposable = Observable.create(ObservableOnSubscribe<Long> {
            it.onNext(System.currentTimeMillis())
        }).subscribeOn(Schedulers.computation())
                .subscribe({
                    LogUtil.d("DemoViewModel loadStrList done $it")
                    // 必须在主线程中调用
//                    strList.value = arrayListOf("$it")
//                     在任意线程中调用，会被发送到主线程
                    strList.postValue(arrayListOf("$it"))
                }, {
                    it.printStackTrace()
                })
    }

}