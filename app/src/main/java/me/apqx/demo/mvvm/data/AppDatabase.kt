package me.apqx.demo.mvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.apqx.demo.CusApp
import me.apqx.demo.old.tools.LogUtil

const val DATABASE_NAME = "app-db"
@Database(entities = [Score::class, Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                // 使用Application的
                instance ?: buildDatabase(CusApp.context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // 创建数据库文件后调用，可以执行向数据库的数据初始化
                            // 主线程，当这里执行完成后，才会执行onOpen()
                            LogUtil.d("Room onCreate ${Thread.currentThread()}")
                            Thread.sleep(2000)

                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            // 数据库被打开时调用
                            LogUtil.d("Room onOpen ${Thread.currentThread()}")
                        }

                        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                            super.onDestructiveMigration(db)
                            // 数据库完成升级迁移后调用
                        }
                    })
                    .build()
        }
    }
}