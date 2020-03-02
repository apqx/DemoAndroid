package me.apqx.demo.mvp

import me.apqx.demo.old.tools.ToastUtil

interface IBaseView {
    public fun showTips(tips: String) {
        ToastUtil.showToast(tips)
    }
}