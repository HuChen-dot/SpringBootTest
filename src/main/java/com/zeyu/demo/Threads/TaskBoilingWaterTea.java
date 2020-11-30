package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.FutureTask;

/**
 * @program: SpringBootTest
 * @description: 有返回值的线程完成烧水泡茶程序
 * @author: chenhu
 * @create: 2020-11-22 18:14
 **/
@Slf4j
public class TaskBoilingWaterTea {
    /**
     * 烧水泡茶程序：洗水壶（1秒钟）；烧开水（10秒钟）；洗茶壶（1秒钟）；拿茶叶（2秒钟）；泡茶（1秒钟）
     */
    public static void main(String[] args) {

        FutureTask<Boolean> task1 = new FutureTask(() -> {
            log.debug("洗水壶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.debug("烧开水");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });

        FutureTask<String> task2 = new FutureTask(() -> {
            log.debug("洗水壶");
            log.debug("洗茶壶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("拿茶叶");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //等待他task1线程返回结果
            Boolean aBoolean = task1.get();
            //如果返回结果是true代表水烧好了
            if (aBoolean) {
                log.debug("泡茶");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("茶泡好了");
            }
            return "茶泡好了";
        });
        new Thread(task2, "task1").start();
        new Thread(task1, "task1").start();
    }
}
