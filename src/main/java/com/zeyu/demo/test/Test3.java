package com.zeyu.demo.test;

import com.zeyu.demo.data.user;
import tk.mybatis.mapper.genid.GenId;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Test3 {
    //定义一个集合，集合中存放user对象
    //根据user的年龄对集合进行排序
    public static void main(String[] args) throws IOException {
        //初始化几个user对象
//        user user = new user("张三", 1, 22);
//        user user1 = new user("张三", 1, 33);
//        user user2 = new user("王五", 3, 55);
//        user user3 = new user("马六", 4, 79);
//        //数据入集合
//        List<user> list = new ArrayList<>();
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
////        Collections.sort(list);
//        list.sort((o1, o2) -> o1.age - o2.age);
//
////        for (user user : list) {
////            System.err.println(user);
////        }
////        list.forEach(use -> System.out::println)
////        System.err.println(list);


//        读取文件
        File f = new File("D:\\ceshi.txt");
        FileInputStream stream = null;
        InputStreamReader reader = null;
        BufferedReader bf = null;
        try {
            stream = new FileInputStream(f);
            reader = new InputStreamReader(stream);
            bf = new BufferedReader(reader);
            String s2 = null;
            while ((s2 = bf.readLine()) != null) {
                System.err.println(s2.length());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bf.close();
            reader.close();
            stream.close();
        }


        //写入文件
//        FileOutputStream ff=new FileOutputStream(f,true);
        FileWriter d = new FileWriter(f, true);
        BufferedWriter writer = new BufferedWriter(d);
        String ss = "你好世界";
        writer.write(ss);
        writer.flush();
        writer.close();
        d.close();
        byte[] bytes = ss.getBytes("gbk");
        Arrays.stream(new byte[][]{bytes}).forEach(item -> System.err.println(Arrays.toString(item)));


    }
}
