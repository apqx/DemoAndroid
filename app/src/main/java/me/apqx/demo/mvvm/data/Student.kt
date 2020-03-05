package me.apqx.demo.mvvm.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
        @PrimaryKey val id: Int = 0,
        val name: String? = "",
        @ColumnInfo(name = "teacher_name") val teacherName: String? = "") {
}