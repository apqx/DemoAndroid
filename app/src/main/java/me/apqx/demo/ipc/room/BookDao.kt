package me.apqx.demo.ipc.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.apqx.demo.ipc.aidl.bean.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM Book")
    fun getAll(): List<Book>

    @Insert
    fun insertAll(books: List<Book>)

    @Delete
    fun delete(book: Book)
}