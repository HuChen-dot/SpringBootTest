package com.zeyu.demo.ftaay;

import java.util.LinkedList;

/**
 * 自己做的栈
 *
 * @param <T>
 */
public class Skacks<T> {
    private LinkedList<T> list = new LinkedList<>();

    //栈的添加方法
    public boolean add(T t) {
        list.addFirst(t);
        return true;
    }

    /**
     * 栈的输出方法
     */
    public T get() {
        return list.pollFirst();
    }

}
