package me.apqx.libannotation.model;

import javax.lang.model.type.TypeMirror;

/**
 * 定义使用注解定义的View，包括View的类型，View的名字，View的Id
 */
public class ViewBinding {

    private TypeMirror viewType;
    private String viewName;
    private int viewId;

    public ViewBinding(TypeMirror viewType, String viewName, int viewId) {
        this.viewType = viewType;
        this.viewName = viewName;
        this.viewId = viewId;
    }

    public TypeMirror getViewType() {
        return viewType;
    }

    public void setViewType(TypeMirror viewType) {
        this.viewType = viewType;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
}
