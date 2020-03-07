package me.apqx.demo.mvvm.data

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.apqx.demo.old.tools.LogUtil

/**
 * 数据仓库，用于获取数据，单例模式
 */
class StudentRepository {
    /**
     * 异步加载数据，更新LiveData
     */
    fun loadStudent(requestCount: Int, currentCount: Int): MutableList<Student> {
        val list = ArrayList<Student>()
        for (i in 0 until requestCount) {
            list.add(Student(id = currentCount + i, name = "Tom_${currentCount + i}"))
        }
        return list
    }

}