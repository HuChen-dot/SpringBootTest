package com.zeyu.demo.Threads.pool;


import com.zeyu.demo.Threads.TestPoll;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: SpringBootTest
 * @description: 自定义线程池
 * @author: chenhu
 * @create: 202011-27 18:34
 **/
@Slf4j
public class MyTestPoll {
    //任务队列
    private BlockingQueue<Runnable> taskQue;

    //线程集合
    private HashSet<Work> works = new HashSet<Work>();
    //核心线程数
    private Integer coreSize;

    //等待时间
    private long timeout;

    //等待时间单位
    private TimeUnit unit;

    public MyTestPoll(Integer coreSize, long timeout, TimeUnit unit, Integer capacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        //初始化任务队列
        taskQue = new BlockingQueue(capacity);
    }

    public MyTestPoll(Integer coreSize, long timeout, TimeUnit unit, Integer capacity, Integer timeoutque) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        //初始化任务队列
        taskQue = new BlockingQueue(capacity, timeoutque, unit);
    }

    //线程执行启动方法
    public void exexoup(Runnable runnable) {
        synchronized (works) {
            //当任务数不超过核心线程数时直接执行
            if (works.size() < coreSize) {
                //直接启动对象
                Work work = new Work(runnable);
                //把当前新创建的word加进线程队列
                works.add(work);
                work.start();
            } else {
                //当任务数超过核心线程数时把任务暂存到任务列表中
//                taskQue.put(runnable);
                taskQue.putTime(runnable);
            }
        }
    }


    //内部线程类
    class Work extends Thread {
        Runnable task;

        public Work(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
//            while (task != null || (task = taskQue.get()) != null) {
            while (task != null || (task = taskQue.get(timeout, unit)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //任务执行完毕把任务置空
                    task = null;
                }
            }
            synchronized (this) {
                //把当前线程从线程集合中移除
                works.remove(this);
            }
        }
    }
}

@Slf4j
//任务队列类
class BlockingQueue<T> {
    //阻塞队列
    private Deque<T> deque = new ArrayDeque();

    //对象锁
    private ReentrantLock lock = new ReentrantLock();

    //队列容量
    private Integer capacity;

    //消费者条件变量
    private Condition fullwaitSet = lock.newCondition();

    //生产者条件变量
    private Condition emptyWaieSet = lock.newCondition();

    //队列存入任务时超时时间
    private Integer timeout;

    //队列超时时间单位
    private TimeUnit unit;

    public BlockingQueue(Integer capacity, Integer timeout, TimeUnit unit) {
        this.capacity = capacity;
        this.timeout = timeout;
        this.unit = unit;
    }

    public BlockingQueue(Integer capacity) {
        this.capacity = capacity;
    }


    //带超时的获取线程方法
    public T get(long timeOut, TimeUnit unit) {
        lock.lock();
        try {
            //把时间转换成纳秒
            long nanos = unit.toNanos(timeOut);
            while (deque.isEmpty()) {
                try {
                    //返回剩余时间，解决了虚假唤醒问题
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = fullwaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头部获取并删除元素
            T t = deque.removeFirst();
            //唤醒生产者线程
            emptyWaieSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //获取元素方法
    public T get() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    //没有可以获取的元素，消费者等待
                    fullwaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列头部获取并删除元素
            T t = deque.removeFirst();
            //唤醒生产者线程
            emptyWaieSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //存入元素方法
    public void put(T enpty) {
        lock.lock();
        try {
            while (this.size() == this.capacity) {
                try {
                    //如果阻塞队列中的元素已满，则生产者等待
                    emptyWaieSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //像队列尾部添加元素
            deque.addLast(enpty);
            //唤醒消费者线程
            fullwaitSet.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //存入元素方法(带超时拒绝的存入方法）
    public void putTime(T enpty) {
        lock.lock();
        try {
            log.debug(timeout.toString());
            long nanos = unit.toNanos(timeout);
            while (this.size() == this.capacity) {
                try {
                    //如果超时时间小于等于0，则返回不在加入任务
                    if (nanos <= 0) {
                        return;
                    }
                    //如果阻塞队列中的元素已满，则生产者等待
                    nanos = emptyWaieSet.awaitNanos(nanos);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //像队列尾部添加元素
            deque.addLast(enpty);
            //唤醒消费者线程
            fullwaitSet.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //获取队列大小
    public int size() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }
}

//测试类
@Slf4j
class Test {
    public static void main(String[] args) {
        MyTestPoll TestPoll = new MyTestPoll(2, 1000, TimeUnit.MILLISECONDS, 10, 1);
        for (int i = 0; i < 12; i++) {
            TestPoll.exexoup(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("打印");
            });
        }

    }
}