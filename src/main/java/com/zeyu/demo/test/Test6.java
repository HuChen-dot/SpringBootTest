package com.zeyu.demo.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test6 {

    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        ReentrantLock lock3 = new ReentrantLock();
        ReentrantLock lock4 = new ReentrantLock();
        ReentrantLock lock5 = new ReentrantLock();


        new Tes(lock1, lock2).start();
        new Tes(lock2, lock3).start();
        new Tes(lock3, lock4).start();
        new Tes(lock4, lock5).start();
        new Tes(lock5, lock1).start();

    }

}

@Slf4j
class Tes extends Thread {
    ReentrantLock lock1;
    ReentrantLock lock2;

    public Tes(ReentrantLock lock1, ReentrantLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (lock1.tryLock()) {
                try {
                    if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                        try {
                            log.debug("哲学家吃饭");
                        } finally {
                            lock2.unlock();
                        }
                    }
                } finally {
                    lock1.unlock();
                }
            }
        }
    }
}
