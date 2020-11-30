package com.zeyu.demo.Threads;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: SpringBootTest
 * @description: 生产者消费者示例
 * @author: chenhu
 * @create: 2020-11-25 10:51
 **/
@Slf4j
public class ProducerConsumer {

    public static void main(String[] args) {
        MessegQuey m = new MessegQuey(5);

        int id = 1;
        Thread thread = new Thread(() -> {
            log.debug("生产了：你好我是第" + id + "条消息");

            m.put(new Message(id, "你好我是第" + id + "条消息"));
        }, "生产者");
        thread.start();


        new Thread(() -> {
            while (true) {
                LockSupport.unpark(thread);
                Message peek = m.peek();
                log.debug(peek.data.toString());
            }

        }, "消费者").start();
    }
}


//创建中间对象
class MessegQuey {
    //定义消息队列
    private static LinkedList<Message> list = new LinkedList();
    //定义一个限制集合存入数量的变量
    private int size = 0;

    public MessegQuey(int size) {
        this.size = size;
    }

    //获取消息方法(消费者）
    public Message peek() {
        synchronized (list) {
            //判断集合是否为空
            while (list.isEmpty()) {
                try {
                    //为空就等待
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //获取第一个元素，并移出
            Message message = list.removeFirst();
            //唤醒其他线程
            list.notifyAll();
            return message;
        }
    }

    //存入消息方法（生产者）
    public void put(Message message) {
        synchronized (list) {
            //判断集合大小有没有到上限
            while (list.size() == size) {
                try {
                    //达到了上限就等待
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //存入对象
            list.addLast(message);
            //唤醒其他线程
            list.notifyAll();
        }
    }
}


class Message {
    //消息编号
    private Integer id;
    //消息内容
    Object data;

    public Message(Integer id, Object data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }
    public Object getData() {
        return data;
    }
}