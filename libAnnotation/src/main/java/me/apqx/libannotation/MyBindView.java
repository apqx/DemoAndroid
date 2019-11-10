package me.apqx.libannotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解处理器，类似于ButterKnife
 */
// 使用次注解的目标是成员
@Target(ElementType.FIELD)
// 编译后，注解将无效
@Retention(RetentionPolicy.SOURCE)
public @interface MyBindView {
    int resId() default 0;
}
