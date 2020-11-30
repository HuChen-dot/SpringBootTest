package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: SpringBootTest
 * @description: 可见性测试
 * @author: chenhu
 * @create: 2020-11-26 10:19
 **/
@Slf4j
public class Visibility {

    static boolean falg = true;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (falg) {
                System.out.println("nihao ");
                log.debug("nihao");
            }
        }).start();
        Thread.sleep(1000);
        falg = false;
    }
}
