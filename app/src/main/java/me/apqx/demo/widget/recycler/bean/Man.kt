package me.apqx.demo.widget.recycler.bean

import android.graphics.Bitmap

const val DATA_TYPE_NORMAL = 0
const val DATA_TYPE_HORIZONTAL = 1

data class Man(var name: String, var iconUrl: String, var dataType: Int = DATA_TYPE_NORMAL)