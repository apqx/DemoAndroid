package me.apqx.demo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import me.apqx.demo.realm.CusRealmMigration

class CusApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(applicationContext)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .migration(CusRealmMigration())
                .name("defRealm")
                .build()
        Realm.setDefaultConfiguration(config)
    }
}