package com.zeyu.demo.test;

import com.zeyu.demo.data.user;
import net.bytebuddy.asm.Advice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;


public class OptionalTest {
    public static void main(String[] args) throws Exception {
        //获取时区集合
        ZoneId.getAvailableZoneIds()
                .forEach(item -> System.err.println(item));
        //指定时区获取当前年月日时分秒
        LocalDateTime nowShangHai = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.err.println("指定上海时区的时间" + nowShangHai);

        //获取年月日
        LocalDate dateNow = LocalDate.now();
        System.err.println("年月日：" + dateNow);
        //获取时分秒
        LocalTime timeNow = LocalTime.now();
        System.err.println("时分秒：" + timeNow);
        //获取年月日时分秒
        LocalDateTime dateTimeNow = LocalDateTime.now();

        System.err.println("获取时间戳" + Instant.now().toEpochMilli());

        //格式化时间

        //获取格式化时间工具
        DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.err.println("自定义把时间格式化成字符串：" + dateTimeNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.err.println("使用提供的枚举类格式化成字符串：" + dateTimeNow.format(DateTimeFormatter.ISO_TIME));

        System.err.println("使用toString格式化成字符串：" + dateTimeNow.toString());

        //格式化时间(把字符串格式化成时间）
        LocalDateTime parse = LocalDateTime.parse("2020-10-20 15:20:56", fa);
        System.err.println("把字符串格式化成时间" + parse);

        //获取两个时间的相隔时间
        LocalTime inst = LocalTime.now();
        Thread.sleep(1000);
        LocalTime inst1 = LocalTime.now();
        System.err.println("获取两个时间的相隔小时" + Duration.between(inst, inst1).toHours());
        System.err.println("获取两个时间的相隔分钟" + Duration.between(inst, inst1).toMinutes());
        System.err.println("获取两个时间的相隔秒" + Duration.between(inst, inst1).getSeconds());
        System.err.println("获取两个时间的相隔毫秒" + Duration.between(inst, inst1).toMillis());

        //获取两个日期的相隔时间
        LocalDate dateTi = LocalDate.now();
        LocalDate parse1 = LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.err.println("获取两个日期的相隔的年" + Period.between(parse1, dateTi).getYears());
        System.err.println("获取两个日期的相隔的月" + Period.between(parse1, dateTi).getMonths());
        System.err.println("获取两个日期的相隔的天数" + Period.between(parse1, dateTi).getDays());

        //其他操作
        System.err.println("检查此日期时间是否等于指定的日期时间" + dateTimeNow.isEqual(parse));
        System.err.println("检查此日期时间是否在指定日期时间之后" + dateTimeNow.isAfter(parse));
        System.err.println("检查此日期时间是否在指定日期时间之前" + dateTimeNow.isBefore(parse));

        //获取时间中的年月日时分秒
        System.err.println("获取时间中的年" + dateTimeNow.getYear());
        System.err.println("获取时间中的月" + dateTimeNow.getMonthValue());
        System.err.println("获取时间中的日" + dateTimeNow.getDayOfMonth());
        System.err.println("获取时间中的时" + dateTimeNow.getHour());
        System.err.println("获取时间中的分" + dateTimeNow.getMinute());
        System.err.println("获取时间中的秒" + dateTimeNow.getSecond());

        //对时间进行加操作
        LocalDateTime localDateTime1 = dateTimeNow.plusYears(2);//对当前时间加2年、
        System.err.println("对当前时间加2年：" + localDateTime1);

        LocalDateTime localDateTime5 = dateTimeNow.plusMonths(2);//对当前月数加2个月
        System.err.println("对当前时间加2个月：" + localDateTime5);

        LocalDateTime localDateTime = dateTimeNow.plusDays(3);//对当前时间加3天
        System.err.println("对当前时间加3天：" + localDateTime);

        LocalDateTime localDateTime2 = dateTimeNow.plusWeeks(1);//对当前时间加一个星期（七天）
        System.err.println("对当前时间加一个星期（七天）：" + localDateTime2);

        LocalDateTime localDateTime4 = dateTimeNow.plusHours(2);//对当前时间加2小时
        System.err.println("对当前时间加2小时：" + localDateTime4);

        LocalDateTime localDateTime6 = dateTimeNow.plusMinutes(2);//对当前时间加2分钟
        System.err.println("对当前时间加2分钟：" + localDateTime6);

        LocalDateTime localDateTime3 = dateTimeNow.plusSeconds(3);//对当前时间加3秒
        System.err.println("对当前时间加3秒：" + localDateTime3);

        //对当前时间减操作
        LocalDateTime localDateTime7 = dateTimeNow.minusYears(2);//对当前时间加2年、
        System.err.println("对当前时间减2年：" + localDateTime7);

        LocalDateTime localDateTime8 = dateTimeNow.minusMonths(2);//对当前月数加2个月
        System.err.println("对当前时间减2个月：" + localDateTime8);

        LocalDateTime localDateTime9 = dateTimeNow.minusDays(3);//对当前时间加3天
        System.err.println("对当前时间减3天：" + localDateTime9);

        LocalDateTime localDateTime10 = dateTimeNow.minusWeeks(1);//对当前时间加一个星期（七天）
        System.err.println("对当前时间减一个星期（七天）：" + localDateTime10);

        LocalDateTime localDateTime11 = dateTimeNow.minusHours(2);//对当前时间加2小时
        System.err.println("对当前时间减2小时：" + localDateTime11);

        LocalDateTime localDateTime12 = dateTimeNow.minusMinutes(2);//对当前时间加2分钟
        System.err.println("对当前时间减2分钟：" + localDateTime12);

        LocalDateTime localDateTime13 = dateTimeNow.minusSeconds(3);//对当前时间加3秒
        System.err.println("对当前时间减3秒：" + localDateTime13);


    }

    public static Date zoneToLocalTime(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        //java.util.Date对象
        Date date = (Date) sdf.parse(dateStr);
        System.err.println(date);
        //2009-09-16
//        String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
//        System.out.println(formatStr);
        //2009-09-16 11:26:23
        String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(formatStr2);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatStr2);
    }
}
