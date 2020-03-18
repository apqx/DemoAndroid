package me.apqx.demo.mvp

import me.apqx.libbase.util.ToastUtil

interface IBaseView {
    public fun showTips(tips: String) {
        ToastUtil.showToast(tips)
    }
}