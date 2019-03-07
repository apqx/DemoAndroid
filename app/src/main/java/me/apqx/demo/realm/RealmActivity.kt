package me.apqx.demo.realm

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.realm.bean.Course
import me.apqx.demo.realm.bean.Student
import java.util.concurrent.TimeUnit

class RealmActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)
        realm = Realm.getDefaultInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    public fun onClick(view: View) {
        when (view.id) {
            R.id.btn_save -> {
                LogUtil.d("btn_save")
//                saveItem()
//                saveItemAsync()
                saveItemRx()
                printReam()
            }

            R.id.btn_del -> {
                LogUtil.d("btn_del")
                realm.beginTransaction()
                realm.where(Student::class.java).findFirst()?.deleteFromRealm()
                realm.commitTransaction()
                printReam()
            }

            R.id.btn_up -> {
                LogUtil.d("btn_up")

            }
        }
    }

    private fun saveItemRx() {

    }


    private fun saveItemAsync() {
        realm.beginTransaction()
        realm.delete(Student::class.java)
        realm.delete(Course::class.java)
        realm.commitTransaction()

        Thread {
            while (true) {
                val realm = Realm.getDefaultInstance()
                val student = realm.where(Student::class.java).findFirst()
                LogUtil.e(student.toString())
                realm.close()
                Thread.sleep(500)
            }
        }.start()

        Thread {
            var count = 0
            while (true) {
                val realm = Realm.getDefaultInstance()
                var student = realm.where(Student::class.java).findFirst()
                realm.beginTransaction()
                if (student == null) {
                    student = Student(0, "tom", Course(0, "math"))
                    realm.copyToRealm(student)
                }
                student?.id = count++
                realm.commitTransaction()
                LogUtil.d(student.toString())
                realm.close()
                Thread.sleep(300)
            }
        }.start()
    }

    private fun saveItem() {
        realm.beginTransaction()
        for (i in 0 until 10) {
            var course = Course(i, "$i")
            var student = Student(i, "$i", course)
            realm.copyToRealm(student)
        }
        realm.commitTransaction()
    }

    private fun printReam() {
        for (student in realm.where(Student::class.java).findAll()) {
            LogUtil.d(student.toString())
        }
    }


}