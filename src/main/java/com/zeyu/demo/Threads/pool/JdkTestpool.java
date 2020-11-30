package com.zeyu.demo.Threads.pool;

import ch.qos.logback.core.pattern.FormatInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskThread;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: SpringBootTest
 * @description: jdk提供的线程池测试
 * @author: chenhu
 * @create: 2020-11-29 16:58
 **/
@Slf4j
public class
JdkTestpool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


//        pool.schedule(() -> log.debug("执行"), 2, TimeUnit.SECONDS);
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        //获取想要执行任务的时间
        //-------------------------小时----------分钟------------秒-------------毫秒------------星期
        LocalDateTime time = now.withHour(00).withMinute(18).withSecond(01).withNano(00).with(DayOfWeek.MONDAY);
        //判断当前时间是否大于任务执行的时间
        if (now.compareTo(time) > 0) {
            time = time.plusWeeks(1);
        }

        //获取当前时间和想要执行时间的时间差并转换成毫秒
        long initailDeley = Duration.between(now, time).toMillis();
        System.err.println(now);
        System.err.println(time);
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        log.debug("开始" + initailDeley);
        //获得下一天执行的时间
        long nextDay = 1000 * 60 * 60 * 24;
        //第一个参数：Runnble;第二个参数：延时时间；第三个参数：执行的间隔；第四个参数：时间单位
        pool.scheduleAtFixedRate(() -> log.debug("执行"), initailDeley, nextDay, TimeUnit.MILLISECONDS);
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        pool.execute(() -> System.err.println("无返回值"));
//        List<Future<String>> task1 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Future<String> task = pool.submit(() -> {
//                log.debug("你好");
//                return "nihao";
//            });
//            task1.add(task);
//        }
//        task1.forEach((f) -> {
//            try {
//                System.err.println("循环：" + f);
//                System.err.println(f.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
//        pool.shutdown();
//        pool.shutdownNow();
//
//        Future<String> task = pool.submit(() -> "带返回值");
//        System.err.println(task.get());
//            try {
//                //获取线程执行的结果
//                task.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

//        ExecutorService pool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 3; i++) {
//            Future<String> task = pool.submit(() -> {
//                log.debug("你好");
//                return "nihao";
//            });
//            try {
//                //获取线程执行的结果
//                task.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.err.println("你");

//        ExecutorService pool = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 3; i++) {
//            pool.execute(() -> {
//                log.debug("你好");
//            });
//        }
    }

}

