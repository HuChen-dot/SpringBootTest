package com.zeyu.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test7 {

    public static void main(String[] args) {
        String s1 = "你好，我是陈虎，你是谁？";
        String s2 = "你好，我是泽宇，我是谁？他是谁，你是谁，我是陈虎";

        String[] split = s1.split("[,，.。?]+");
        String[] split1 = s2.split("[,，.。?]+");

        String[] sp = split;
        if (split.length > split1.length) {
            sp = split1;
        }

        //记录最短长度
        double length = sp.length;
        List<String> strings = Arrays.asList(split);
        List<String> strings1 = Arrays.asList(split1);
        List arrList = new ArrayList(strings);
        List arrList1 = new ArrayList(strings1);
        arrList.retainAll(arrList1);
        double dd = arrList.size();
        System.err.println(dd / length * 100 + "%");

    }
}
