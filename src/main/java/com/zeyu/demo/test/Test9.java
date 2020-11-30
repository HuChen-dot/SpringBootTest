package com.zeyu.demo.test;

import jdk.nashorn.tools.Shell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test9 {
    public static void main(String[] args) {
        //双层循环99乘法表
//        for (int i = 1; i <= 9; i++) {
//            for (int j = 1; j <= i; j++) {
//                System.err.print(i + "*" + j + "=" + i * j + "\t");
//            }
//            System.err.println();
//        }

        //单层循环99乘法表
//        int j = 1;
//        for (int i = 1; i <= 9; ) {
//
//            if (j <= i) {
//                System.err.print(i + "*" + j + "=" + i * j + "\t");
//                j++;
//            } else {
//                j = 1;
//                i++;
//                System.err.println();
//            }
//        }
//        List<Integer> notList = new ArrayList<>();
//        notList.add(1);
//        notList.add(1);
//        notList.add(1);
//        notList.add(1);
//        notList.add(1);
//
//        StringBuffer buffer = new StringBuffer("");
//        for (Integer not : notList) {
//            buffer.append(not + ",");
//        }
//        String collect = notList.stream().map(item -> item.toString()).collect(Collectors.joining(","));
//
//        buffer.deleteCharAt(buffer.length() - 1);
//        System.err.println(collect);
//        System.err.println(buffer);
//        LocalDateTime now = LocalDateTime.now();136
//        System.err.println(now);
//        System.err.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//
//        System.err.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        ;
//        Pattern p = Pattern.compile("^1([389][692])[0-9]{8}$");//获得一个Pattern并给定正则表达式
//        Matcher m = p.matcher("13211111111");//通过Pattern 获得Matcher 并给定要匹配的字符串
//        System.err.println(m.matches());

    }
}
