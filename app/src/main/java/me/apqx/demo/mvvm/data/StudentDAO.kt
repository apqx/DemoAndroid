package me.apqx.demo.mvvm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO {
    @Query("SELECT * FROM student WHERE id IN (:idList)")
    fun loadStudentsByIds(idList: List<Int>): Student

    // TODO: 什么样的数据判断为相等，然后删除
    @Delete
    fun delete(vararg students: Student)

    @Insert
    fun insert(vararg students: Student)

    @Insert
    fun insert(vararg scores: Score)
}