package com.huang.codelearning.java.demo;

import com.huang.codelearning.java.annotation.MyAnnotation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射操作获取注解
 */
public class AnnotationDemo {
    private static Logger logger = LoggerFactory.getLogger(AnnotationDemo.class);

    @Test
    public void test(){
        String myAnnotation = getMyAnnotation("com.huang.codelearning.java.pojo.WebSitePo");
        logger.info(myAnnotation);
    }

    /**
     * @description 反射获取自定义注解
     * @param classRefer 类引用
     * @return 注解属性值
     */
    public String getMyAnnotation(String classRefer){
        try {
            Class<?> clazz = Class.forName(classRefer);
            if (clazz.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
                return annotation.owner();
            }
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        }
        return "";
    }
}
