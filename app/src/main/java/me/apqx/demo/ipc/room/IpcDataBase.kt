package me.apqx.demo.ipc.room

import androidx.room.Database
import androidx.room.RoomDatabase
import me.apqx.demo.ipc.aidl.bean.Book

@Database(entities = [Book::class], version = 1)
abstract class IpcDataBase : RoomDatabase(){
    abstract fun bookDao(): BookDao
}