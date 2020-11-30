package com.zeyu.demo.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Test4 {
    //读取文件中的内容，并保存在String字符串中
    public String getFile(String pash) throws IOException {
        FileReader read = new FileReader(pash);
        BufferedReader reader = new BufferedReader(read);
        StringBuffer buffer = new StringBuffer("");
        String comit = "";
        while ((comit = reader.readLine()) != null) {
            buffer.append(comit);
        }
        reader.close();
        read.close();
        return buffer.toString();
    }

    //    把字符串按照单词进行分割，返回数组
    public String[] getStrign(String str) {
        return str.split("[^a-zA-Z]+");
    }

    //对两个数组中内容进行比较，返回相同单词内容的集合
    public List<String> get(String[] str1, String[] str2) {
        //声明一个存储的数组
        List<String> list = new ArrayList<>();
        Map<String, String> map = new ConcurrentHashMap<>(10);
        for (String s : str1) {
            map.put(s, s);
        }
        Arrays.stream(str2)
                .distinct()
                .forEach(item -> {
                    if (map.get(item) != null) {
                        list.add(map.get(item));
                    }
                });

        return list;
    }

    public static void main(String[] args) throws Exception {
        Test4 test4 = new Test4();
        String file = test4.getFile("D:\\ceshi.txt");
        String file2 = test4.getFile("D:\\ceshi1.txt");
        String[] strign = test4.getStrign(file);
        String[] strign1 = test4.getStrign(file2);
        List<String> strings = test4.get(strign, strign1);
        strings.forEach(item -> System.err.println(item));

    }
}
