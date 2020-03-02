package me.apqx.demo.mvp

public class DemoPresenter : BasePresenter<DemoFragment>() {
    public fun initData() {
        view?.showTips("Done")
    }
}