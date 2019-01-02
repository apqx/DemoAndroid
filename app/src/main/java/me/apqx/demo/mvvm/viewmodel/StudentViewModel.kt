package me.apqx.demo.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.apqx.demo.mvvm.model.Student
import me.apqx.demo.mvvm.model.StudentRepository

class StudentViewModel : ViewModel() {
    private val studentRepository = StudentRepository()

    val studentLiveData: MutableLiveData<Student> = studentRepository.getStudent()

}