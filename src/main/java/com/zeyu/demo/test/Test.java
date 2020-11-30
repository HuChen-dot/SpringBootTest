package com.zeyu.demo.test;

import com.zeyu.demo.pojo.Chanpin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {

        //获取类上的注解和内容
        Class<Chanpin> chanpinClass = Chanpin.class;
        TestAnnotation annotation = chanpinClass.getAnnotation(TestAnnotation.class);
        if (annotation != null) {
            System.err.println(annotation.content() + annotation.num());
        }

        //根据注解更改类的属性值
        Field[] declaredFields = chanpinClass.getDeclaredFields();
        Chanpin chanpin = new Chanpin();
        for (Field declaredField : declaredFields) {

            TestAnnotation annotation1 = declaredField.getAnnotation(TestAnnotation.class);
            String content = "kk";
            if (annotation1 != null) {
                content = annotation1.content();
                declaredField.setAccessible(true);
                declaredField.set(chanpin, content);
                System.err.println(content);
            }

        }


    }

}
