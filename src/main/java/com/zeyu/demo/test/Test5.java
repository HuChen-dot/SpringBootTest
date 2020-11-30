package com.zeyu.demo.test;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GetFile {

    public static String get() throws IOException {
        Reader r = new FileReader("D://1.txt");
        BufferedReader b = new BufferedReader(r);
        String buffer = null;
        StringBuffer buffer1 = new StringBuffer("");
        while ((buffer = b.readLine()) != null) {
            buffer1.append(buffer);
        }
        return buffer1.toString();
    }
}

public class Test5 {
    public static void main(String[] args) throws IOException {
        String s = GetFile.get();
        String[] split = s.split("[^a-zA-Z]+");//按照非字母分割
        int z = 0;// 建立一个接收有几个相同字符的变量
        int x = 0;
        String content = "";
        for (String s1 : split) {
            while (s.indexOf(s1) != -1) {
                s = s.substring(s.indexOf(s1) + 1);
                z++;
            }
            if (z > x) {
                x = z;
                content = s1;
            }
        }
        System.err.println(content);
    }
}
