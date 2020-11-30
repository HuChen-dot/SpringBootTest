package com.zeyu.demo.data;

@FunctionalInterface
public interface StringTest<T, R> {

    R stringGetValue(T str, T str1);
}
