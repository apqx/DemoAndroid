package me.apqx.demo.widget.recycler.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.apqx.demo.widget.recycler.bean.Student

class StudentViewModel : ViewModel() {

    private val list: MutableList<Student> = ArrayList()
    val studentLiveData = MutableLiveData<MutableList<Student>>()

    init {
        studentLiveData.value = list
    }

    fun refreshNew() {
        for (i in 0 until 10) {
            list.add(Student("${list.size + 1}", ""))
        }
        studentLiveData.value = list
    }
}