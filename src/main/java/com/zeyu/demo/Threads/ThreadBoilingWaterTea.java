package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: SpringBootTest
 * @description: 没有返回值线程，完成烧水泡茶程序
 * @author: chenhu
 * @create: 2020-11-22 18:02
 **/
@Slf4j
public class ThreadBoilingWaterTea {
    /**
     * 烧水泡茶程序：洗水壶（1秒钟）；烧开水（10秒钟）；洗茶壶（1秒钟）；拿茶叶（2秒钟）；泡茶（1秒钟）
     */


    public static void main(String[] args) {

        //洗水壶，烧开水线程
        Thread t1 = new Thread(() -> {
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
        }, "t1");
        //洗茶壶，拿茶叶，泡茶线程
        Thread t2 = new Thread(() -> {
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
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("泡茶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("茶泡好了");
        }, "t2");
        t2.start();
        t1.start();

    }

}
