package me.apqx.demo.old.jetpack.livedata.adapter

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("selected")
fun bindStudentSelected(view: View, selected: Boolean) {
    view.setBackgroundColor(if (selected) Color.RED else Color.WHITE)
}