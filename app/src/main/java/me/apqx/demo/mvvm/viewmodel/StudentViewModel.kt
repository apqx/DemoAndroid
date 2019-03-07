package me.apqx.demo.mvvm.viewmodel

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.apqx.demo.mvvm.bean.Student

class StudentViewModel : ViewModel() {

    val studentLiveData: MutableLiveData<Student> = MutableLiveData()

    fun refreshData() {
        studentLiveData.value = Student("${System.currentTimeMillis()}", 0)
    }
}