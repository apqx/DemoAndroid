package me.apqx.demo.jetpack.livedata.bean

import android.graphics.Color

data class Student(var name: String, var age: Int, val img: Int, var select: Boolean = false) {

    fun getBgColor(): Int {
        return if (select) Color.RED else Color.WHITE
    }
}