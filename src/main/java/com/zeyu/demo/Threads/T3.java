package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: SpringBootTest
 * @description: 测试 ReentrantLock获取条件变量
 * @author: chenhu
 * @create: 2020-11-20 10:47
 **/
@Slf4j
public class T3 {
    private static volatile boolean falg = true;

    public static void main(String[] args) {
        //创建一个锁对象
        ReentrantLock lock = new ReentrantLock();
        //通过锁对象的方获取条件变量；可以获得多个条件变量
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                //如果是true就进入阻塞休眠
                while (falg) {
                    try {
                        //进入阻塞释放锁方法
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("打印消息1");
                //改变状态
                falg = true;
                //唤醒等待的线程
                condition1.signalAll();
            } finally {
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() -> {
            lock.lock();
            try {
                //如果是true就进入阻塞休眠
                while (!falg) {
                    try {
                        //进入阻塞释放锁方法
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("打印消息2");
                //改变状态
                falg = false;
                //唤醒等待的线程
                condition1.signalAll();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
