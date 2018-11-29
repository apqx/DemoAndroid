package me.apqx.demo.jetpack.livedata.bean

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    val studentLiveData = MutableLiveData<MutableList<Student>>()
}