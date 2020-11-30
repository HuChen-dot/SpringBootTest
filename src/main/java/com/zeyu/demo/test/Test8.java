package com.zeyu.demo.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test8 {


    public static void main(String[] args) {
//        String d = "2020-11-01T16:00:00.000Z";
////        d = d.replace("Z", " UTC");
//        LocalDate parse = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.err.println(parse);
        String dateTime = "2020-01-13T16:00:00.000Z";
        dateTime = dateTime.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time = format.parse(dateTime);
            String result = defaultFormat.format(time);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Object obj = new Object();
//        xian1 xian1 = new xian1(obj);
//        xian xian = new xian(obj);
//        Thread th = new Thread(xian);
//        Thread th1 = new Thread(xian1);
//        th1.start();
//        th.start();

    }
}
