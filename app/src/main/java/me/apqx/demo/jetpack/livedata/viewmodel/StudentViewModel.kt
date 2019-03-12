package me.apqx.demo.jetpack.livedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.apqx.demo.R
import me.apqx.demo.jetpack.livedata.bean.Student

class StudentViewModel : ViewModel() {

    private val list: MutableList<Student> = ArrayList()

    val studentLiveData = MutableLiveData<MutableList<Student>>()

    init {
        refresh()
    }

    fun add() {
        for (i in 0 until 100) {
            list.add(Student("${list.size}", 0, R.mipmap.ic_launcher))
        }
        refresh()
    }

    fun delete() {
        if (list.size > 0) {
            list.removeAt(list.size - 1)
            refresh()
        }
    }

    fun refresh() {
        studentLiveData.value = list
    }
}