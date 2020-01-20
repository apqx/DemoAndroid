package me.apqx.demo.old.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.apqx.demo.old.mvvm.bean.Student

class StudentViewModel : ViewModel() {

    val studentLiveData: MutableLiveData<Student> = MutableLiveData()

    fun refreshData() {
        studentLiveData.value = Student("${System.currentTimeMillis()}", 0)
    }
}