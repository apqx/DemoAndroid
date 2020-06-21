package me.apqx.demo.mvp

import me.apqx.libtools.notify.ToastUtil

interface IBaseView {
    public fun showTips(tips: String) {
        ToastUtil.showToast(tips)
    }
}