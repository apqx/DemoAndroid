package me.apqx.demo.old.realm

import io.realm.DynamicRealm
import io.realm.RealmMigration

class CusRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

    }
}