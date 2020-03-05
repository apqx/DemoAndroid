package me.apqx.demo.mvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "score")
data class Score(
        @PrimaryKey val id: Int = 0,
        val math: Int = 0,
        val english: Int = 0
) {

}