package me.apqx.demo.realm

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema

class CusRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

    }
}