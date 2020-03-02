package me.apqx.demo.mvpj;

public class BasePresenter<V extends IBaseView> {
    private V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }
}
