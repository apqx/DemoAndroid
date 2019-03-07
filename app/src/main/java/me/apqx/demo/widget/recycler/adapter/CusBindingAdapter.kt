package me.apqx.demo.widget.recycler.adapter

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import androidx.databinding.BindingAdapter
import me.apqx.demo.R

@BindingAdapter("imgUrl")
fun bindImgFromResId(view: View, resUrl: String) {
    if (TextUtils.isEmpty(resUrl)) {
        // 默认加载资源
        view.setBackgroundResource(R.mipmap.ic_launcher)
    } else{
        view.setBackgroundColor(Color.RED)
    }
}