package me.apqx.demo.old.realm.bean

import io.realm.RealmObject

open class Student() : RealmObject() {
    var id: Int? = null
    var name: String? = null
    var course: Course? = null
    constructor(id: Int?, name: String?, course: Course?) : this() {
        this.id = id
        this.name = name
        this.course = course
    }

    override fun toString(): String {
        return "id = $id, name = $name, course = $course"
    }
}