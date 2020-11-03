package com.huang.codelearning.java.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author huangshibo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String owner() default "huangshibo";
}
