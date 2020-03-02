package me.apqx.demo.mvp

open class BasePresenter<V : IBaseView> {
    protected var view: V? = null

    public fun attachView(view: V) {
        this.view = view
    }

    public fun detachView() {
        this.view = null
    }
}