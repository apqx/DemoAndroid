package me.apqx.demo.widget.view;

public class TabBean<T> {
    private String tabStr;
    private T data;

    public TabBean(String tabStr, T data) {
        this.tabStr = tabStr;
        this.data = data;
    }

    public String getTabStr() {
        return tabStr;
    }

    public void setTabStr(String tabStr) {
        this.tabStr = tabStr;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
