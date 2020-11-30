package com.zeyu.demo.Threads.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @program: SpringBootTest
 * @description: 读写锁
 * @author: chenhu
 * @create: 2020-11-30 15:55
 **/
@Slf4j
public class ReadAndWrit {
    public static void main(String[] args) {
        //创建读写锁对象
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        //获取读锁
        ReentrantReadWriteLock.ReadLock r = rw.readLock();
        //获取写锁
        ReentrantReadWriteLock.WriteLock w = rw.writeLock();

        //创建读写锁对象
        StampedLock lock = new StampedLock();

        new Thread(() -> {
            //使用读锁
            long stamp = lock.readLock();
            try {
                log.debug("读锁测试");
            } finally {
                //解开读锁
                lock.unlockRead(stamp);
            }
        }).start();

        new Thread(() -> {
            //使用读锁
            long stamp = lock.writeLock();
            try {
                log.debug("写锁测试");
            } finally {
                //解开读锁
                lock.unlockWrite(stamp);
            }
        }).start();

    }
}
