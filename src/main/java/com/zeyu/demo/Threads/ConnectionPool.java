package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicIntegerArray;


/**
 * @program: SpringBootTest
 * @description: 自定义连接池
 * @author: chenhu
 * @create: 2020-11-27 13:58
 **/
public class ConnectionPool {
    //连接数量
    private int count;
    //存放链接的数组
    private Connection[] con;
    //连接状态(0表示空闲，1表示繁忙）
    private AtomicIntegerArray status;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ConnectionPool(int count) {
        this.count = count;
        this.con = new Connection[count];
        this.status = new AtomicIntegerArray(new int[count]);
        //预先创建连接
        for (int i = 0; i < count; i++) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chaoshi?serverTimezone=UTC&useSSL=true&characterEncoding=utf-8", "root", "123456");
                this.con[i] = con;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    //获取连接
    public Connection getConnection() {
        while (true) {
            for (int i = 0; i < count; i++) {
                if (status.get(i) == 0 && status.compareAndSet(i, 0, 1)) {
                    return con[i];
                }
            }
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //归还连接
    public void giveConnection(Connection con) {
        for (int i = 0; i < count; i++) {
            if (this.con[i] == con) {
                status.set(1, 0);
                synchronized (this) {
                    this.notify();
                }
                break;
            }
        }
    }

}

@Slf4j
class r {
    public static void main(String[] args) throws InterruptedException {
        ConnectionPool con = new ConnectionPool(2);
        for (int i = 0; i < 18; i++) {
            new Thread(() -> {
                //获取连接
                Connection connection = con.getConnection();
                try {
                    log.debug("获取连接：{}", connection);
                    log.debug("进行数据库操作");
                } finally {
                    log.debug("释放连接");
                    con.giveConnection(connection);
                }
            }).start();
        }

    }
}
