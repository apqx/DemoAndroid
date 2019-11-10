package me.apqx.libannotation.model;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.TypeElement;

/**
 * 定义使用了注解的类，类的Element，和内部的多个使用注解的View
 */
public class ClassBinding {
    private TypeElement classElement;
    private Set<ViewBinding> viewBindingSet;

    public ClassBinding(TypeElement classElement) {
        this.classElement = classElement;
        viewBindingSet = new HashSet<>();
    }

    public TypeElement getClassElement() {
        return classElement;
    }

    public void setClassElement(TypeElement classElement) {
        this.classElement = classElement;
    }

    public Set<ViewBinding> getViewBindingSet() {
        return viewBindingSet;
    }

    public void addViewBinding(ViewBinding viewBinding) {
        viewBindingSet.add(viewBinding);
    }
}
