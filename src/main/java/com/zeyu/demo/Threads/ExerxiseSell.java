package com.zeyu.demo.Threads;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @program: SpringBootTest
 * @description: 习题---售票
 * @author: chenhu
 * @create: 2020-11-22 21:43
 **/
@Slf4j
public class ExerxiseSell {
    public static void main(String[] args) {
        //模拟多人售票
        TicketWindow ti = new TicketWindow(10000);
        List<Integer> list = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                int sell = ti.sell(getranod());
                list.add(sell);
                if (sell == 0) {
                    log.debug("余票不足");
                } else {
                    log.debug("成功购票；购票数：{}", sell);
                }

            }).start();
        }
        System.err.println(ti.getCount());
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.err.println("卖出的数量：" + reduce);
        System.err.println("相加：" + (ti.getCount() + reduce));
    }

    static Random random = new Random();

    public static int getranod() {
        return random.nextInt(5) + 1;
    }
}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    /**
     * @Description: 获取余票
     * @Param: []
     * @return: int
     * @Author: chenhu
     * @Date: 2020/11/22
     */
    public int getCount() {
        return this.count;
    }

    /**
     * @Description: 售票
     * @Param: [amount]
     * @return: int
     * @Author: chenhu
     * @Date: 2020/11/22
     */
//    public int sell(int amount) {
//        if (count >= amount) {
//            count -= amount;
//            return amount;
//        } else {
//            return 0;
//        }
//    }
    public int sell(int amount) {
        if (count >= amount) {
            synchronized (this) {
                if (count >= amount) {
                    count = count - amount;
                    return amount;
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }
}