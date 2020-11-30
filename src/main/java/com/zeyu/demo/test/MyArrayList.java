package com.zeyu.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 自定义ArrayList集合
 *
 * @param <T>
 */
public class MyArrayList<T> {
    private int count = 10;
    private Object[] arr = new Object[count];
    private int i;

    //无参构造
    public MyArrayList() {

    }

    //带参构造
    public MyArrayList(int count) {
        this.count = count;
    }

    //添加方法
    public void add(T obj) {
        if (i == arr.length * 0.8) {
            arr = Arrays.copyOf(arr, arr.length * 3 / 2);
        }
        arr[i] = (T) obj;
        i++;
    }

    //获取方法
    public T get(int i) {
        return (T) arr[i];
    }

    //删除方法
    public void remoke(int i) {
        for (int j = i; j < arr.length - 1; j++) {
            arr[j] = arr[j + 1];
        }
    }

    //选择插入位置的添加的方式（直接把被替换的添加在后面）
    public void sriAdd(int i, T obj) {
        if (i > size()) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        Object ob = arr[i];
        arr[i] = obj;
        for (int j = 0; j < arr.length; j++) {
            if (arr[i] == null) {
                arr[i] = ob;
            }
        }
    }

    //选择插入位置的添加的方式(依次向后移动）
    public void Add(int i, T obj) {
        if (i > size()) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        for (int j = size(); j >= i; j--) {
            arr[j + 1] = arr[j];
        }
        arr[i] = obj;
    }

    //获取自定义集合的长度
    public int size() {
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                size++;
            }
        }
        return size;
    }

    //toString方法
    public String toString() {
        StringBuffer buffer = new StringBuffer("[");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                buffer.append(arr[i]);
                buffer.append(",");
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("]");
        return buffer.toString();
    }

    public void toString2() {
        System.err.println(Arrays.toString(arr));

    }
}

class AppTest {
    public static void main(String[] args) {
        MyArrayList<Integer> my = new MyArrayList();
        my.add(1);
        my.add(2);
        my.add(3);
        my.add(4);
        my.add(5);
        my.add(6);
        System.err.println(my.size());
        my.Add(7, 200);
        Integer integer = my.get(1);
        System.err.println(integer);
//        my.toString2();
        System.err.println(my.toString());
    }
}
