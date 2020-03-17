package me.apqx.demo.mvvm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * 这里的函数都不能在主线程中执行
 */
@Dao
interface StudentDAO {

    /**
     * 查询语句，返回的类型，就是数据表Bean类型列表，空列表，或者列表的第一个item，或者null
     */
    @Query("SELECT * FROM student WHERE id IN (:idList)")
    fun loadStudentsByIds(idList: List<Int>): List<Student>

    @Query("SELECT * FROM student")
    fun loadAllStudents(): List<Student>

    @Query("SELECT * FROM student")
    fun loadSingle(): Student?

    /**
     * PrimaryKey 相等即可删除，未找到则不删除，不会抛出异常
     */
    @Delete
    fun delete(vararg students: Student)

    /**
     * 删除所有item
     */
    @Query("DELETE FROM student")
    fun deleteAllStudents()

    @Insert
    fun insert(vararg students: Student)

    @Insert
    fun insert(vararg scores: Score)
}