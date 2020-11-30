package com.zeyu.demo.ftaay;

public class TestSkacks {
    public static void main(String[] args) {
        Skacks<String> skacks = new Skacks();
        skacks.add("a");
        skacks.add("b");
        skacks.add("c");
        skacks.add("d");
        System.err.println(skacks.get());
        System.err.println("*************************************");

    }
}
