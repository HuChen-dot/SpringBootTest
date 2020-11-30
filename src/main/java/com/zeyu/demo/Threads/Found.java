package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: SpringBootTest
 * @description: 创建线程三种方式
 * @author: chenhu
 * @create: 2020-11-20 08:40
 **/
@Slf4j
public class Found {

    public void newThread() throws InterruptedException {
        //子线程
        new Thread("new Thread方式创建线程") {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 10; i++) {
                        log.debug(i + "");
                    }
                }
            }
        }.start();

    }

    public void runnable() {
        //方式1
        Runnable reun = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 10; i++) {
                        log.debug(i + "");
                    }
                }
            }
        };
        //子线程
        new Thread(reun, "方式1线程").start();

        //方式2
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                log.debug(i + "");
//            }
//        }, "方式2线程").start();

    }

    public void runnable1() throws ExecutionException, InterruptedException {
        //方式1
        //声明FutureTask对象并指定返回值，需要传入Callable（接口）对象（相当于Runnable对象）
        FutureTask<Integer> task = new FutureTask(() -> {
            int sun = 0;
            for (int i = 0; i < 10; i++) {
                sun += i;
                log.debug(i + "");
            }
            return sun;
        });
        //传入FutureTask对象
        new Thread(task, "带返回值的线程").start();
        //通过传入FutureTask对象获取方法执行返回值，如果线程没有执行完会阻塞在这里，等待线程执行的结果
        Integer integer = task.get();
        System.err.println(integer);

        //方式2
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                log.debug(i + "");
            }
        }, "方式2线程").start();

    }

    public static void main(String[] args) throws InterruptedException {

        Found Found = new Found();
        Found.newThread();
        Found.runnable();

    }
}
