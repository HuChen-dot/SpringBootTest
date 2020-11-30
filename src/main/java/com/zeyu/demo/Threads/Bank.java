package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: SpringBootTest
 * @description: 乐观锁，--取钱示例
 * @author: chenhu
 * @create: 2020-11-26 18:47
 **/
@Slf4j
public class Bank {
    public static void main(String[] args) {
        AccoutCas acc = new AccoutCas(10000);
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                acc.reduceBalance(10);
            }));
        }

        list.forEach(item -> {
            item.start();
        });
        list.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.debug(acc.getBalance().toString());
    }
}

//ReentrantLock锁实现
class AccoutUnSeff implements Accout {
    private Integer balance;
    ReentrantLock lock = new ReentrantLock();

    public AccoutUnSeff(Integer balance) {
        this.balance = balance;
    }

    //取钱方法
    public Integer getBalance() {
        return balance;
    }

    //减少余额
    public void reduceBalance(int balance) {
        lock.lock();
        try {
            this.balance -= balance;
        } finally {
            lock.unlock();
        }
    }
}

//乐观锁思想实现
class AccoutCas implements Accout {
    //使用原子工具类
    private volatile AtomicInteger balance;

    public AccoutCas(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    //取钱方法
    public Integer getBalance() {
        return balance.get();
    }

    //减少余额
    public void reduceBalance(int balance) {
        this.balance.updateAndGet(item -> item - balance);
    }
}

interface Accout {
    //取钱方法
    public Integer getBalance();

    //减少余额
    public void reduceBalance(int balance);

}