package me.apqx.demo.old.jetpack.databinding.adapter

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bgRed")
fun bindBgRed(view: View, age: Int) {
    if (age % 2 == 0) {
        view.setBackgroundColor(Color.RED);
    } else{
        view.setBackgroundColor(Color.GREEN)
    }
}