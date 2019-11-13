package me.apqx.demo.widget.view;

public interface OnTabSelectListener<T> {
    /**
     * 点击了某个tab
     */
    void onTabClick(T t);

    /**
     * 点击了下拉列表里的编辑按钮
     */
    void onEditClick();
}
