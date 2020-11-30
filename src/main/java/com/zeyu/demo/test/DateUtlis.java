package com.zeyu.demo.test;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: SpringBootTest
 * @description: 时间工具类
 * @author: chenhu
 * @create: 2020-11-19 11:58
 **/
public class DateUtlis {
    public static void main(String[] args) {

        String ss = "2020-11-05T16:00:00.000Z";
//        String s = sTransitions(ss);
//        Pattern p = Pattern.compile("[zZ]$");
//        Matcher m = p.matcher(ss);
//        boolean b = m.find();
//        System.err.println(b);
//        Date date = datetoStr(ss);
//        System.err.println(date);
        String s = sTransitions(ss);
        System.err.println(s);
        LocalDateTime parse2 = LocalDateTime.parse(sTransitions(ss), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.err.println(parse2);
//        LocalDate parse2 = LocalDate.parse(ss, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.err.println(parse2);
//        LocalDateTime localDateTime = LocalDateToLocalDateTime(parse2);
//        System.err.println(localDateTime);


    }

    /**
     * 把不标准的字符串转换成标准的字符串
     */
    public static final String sTransitions(String times) {
        times = times.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = format.parse(times);
            time = subDay(time, -1);
            System.err.println("date:" + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = defaultFormat.format(time);
        return result;

    }

    /**
     * @Description: 将当前date时间增加或者减少（天）
     * @Param: [date：时间, day：增加或者减少的天数]
     * @return: java.util.Date
     * @Author: chenhu
     * @Date: 2020/11/19
     */
    public static Date subDay(Date date, int day) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_MONTH, day);
        return rightNow.getTime();
    }

    /**
     * 字符串转换成日期格式(年月日时分秒）
     */
    @SneakyThrows
    public static final Date datetoStr(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            throw new ParseException("日期转换错误", e.getErrorOffset());
        }

    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());//Tue Mar 27 14:17:17 CST 2018
        return date;
    }

    /**
     * LocalDate转换为Date
     *
     * @param localDate
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());//Tue Mar 27 14:17:17 CST 2018
        return date;
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        System.out.println(localDateTime.toString());//2018-03-27T14:07:32.668
        System.out.println(localDateTime.toLocalDate() + " " + localDateTime.toLocalTime());//2018-03-27 14:48:57.453

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//This class is immutable and thread-safe.@since 1.8
        System.out.println(dateTimeFormatter.format(localDateTime));//2018-03-27 14:52:57
        return localDateTime;
    }

    /**
     * Date转换为LocalDate
     *
     * @param date
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    /**
     * LocalDateTime转换为LocalDate
     *
     * @param localDateTime
     */
    public static LocalDate LocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * LocalDate转换为LocalDateTime
     *
     * @param localDate
     */
    public static LocalDateTime LocalDateToLocalDateTime(LocalDate localDate) {
        Long targetTimeStamp = Long.valueOf(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
        LocalDateTime localDateTime = Instant.ofEpochMilli(targetTimeStamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return localDateTime;
    }
}