package me.apqx.demo.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StudentRepository {
    fun getStudent() : MutableLiveData<Student> {
        val studentLiveData = MutableLiveData<Student>()
        studentLiveData.value = Student("Guodong", 23)
        return studentLiveData
    }
}