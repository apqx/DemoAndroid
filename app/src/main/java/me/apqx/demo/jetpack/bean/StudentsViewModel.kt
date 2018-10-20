package me.apqx.demo.jetpack.bean

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentsViewModel: ViewModel() {
    val list = ArrayList<Student>()
    val students: MutableLiveData<List<Student>> by lazy {
        MutableLiveData<List<Student>>()
    }

    init {
        students.value = list
    }
}