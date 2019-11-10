package me.apqx.libannotation.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * 定义使用了注解的包，包的Element，和内部的多个使用注解的类
 */
public class PackageBinding {
    private PackageElement packageElement;
    private Map<TypeElement, ClassBinding> classBindings;

    public PackageBinding(PackageElement packageElement) {
        this.packageElement = packageElement;
        this.classBindings = new HashMap<>();
    }

    public String getPackageName() {
        return packageElement.getQualifiedName().toString();
    }

    public Collection<ClassBinding> getClassBindings() {
        return classBindings.values();
    }

    public ClassBinding getClassBinding(TypeElement typeElement) {
        return classBindings.computeIfAbsent(typeElement, ClassBinding::new);
    }
}
