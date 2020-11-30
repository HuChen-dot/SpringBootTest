package com.zeyu.demo.designMode.agency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 接口
 */
interface Icion {
    int sum(int a, int b);

    int svn(int a, int b);
}

/**
 * 接口实现类
 */
class IcionImpl implements Icion {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int svn(int a, int b) {
        return a / b;
    }
}


/**
 * 动态代理类
 */
class MyAgency implements InvocationHandler {
    Object icon;

    public MyAgency(Object icion) {
        this.icon = icion;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("开始执行，参数是：" + Arrays.toString(args));
        Object invoke = method.invoke(icon, args);
        return invoke;
    }
}

/**
 * 代理模式
 */
public class AppTest {
    public static void main(String[] args) {
        //第一个参数：当前运行类的类加载器
        //第二个参数：需要动态代理的接口（jdk动态代理，需要有接口的支持）
        //第三个参数：自定义的实现了InvocationHandler 接口的实现类；
        Icion o = (Icion) Proxy.newProxyInstance(AppTest.class.getClassLoader(),
                new Class[]{Icion.class}, new MyAgency(new IcionImpl()));
        System.err.println(o.sum(1, 2));
    }
}
