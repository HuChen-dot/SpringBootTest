package com.zeyu.demo.Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: SpringBootTest
 * @description: 测试
 * @author: chenhu
 * @create: 2020-11-20 09:47
 **/
public class SplitList {

    /**
     * @Description: 把一个集合分成多个size大小的集合
     * @Param: [arraylisyt：需要分的集合, size：分完以后每个集合的大小]
     * @return: java.util.Stack<java.util.List < java.lang.Integer>>
     * @Author: chenhu
     * @Date: 2020/11/22
     */
    private static List<List<Integer>> splikArray(List<Integer> arraylisyt, int size) {
        List<List<Integer>> lists = new ArrayList<>();
        int listSize = arraylisyt.size();
        //子集合的长度
        int toIndex = size;
        for (int i = 0; i < arraylisyt.size(); i += size) {
            if (i + size > listSize) {
                toIndex = listSize - i;
            }
            List<Integer> newList = arraylisyt.subList(i, i + toIndex);
            lists.add(newList);
        }
        return lists;
    }

    /**
     * @Description: 把一个集合分成多个size大小的集合
     * @Param: [arraylisyt：需要分的集合, size：分完以后每个集合的大小]
     * @return: java.util.Stack<java.util.List < java.lang.Integer>>
     * @Author: chenhu
     * @Date: 2020/11/22
     */
    private static Stack<List<Integer>> groupList(List<Integer> arraylisyt, int size) {
        Stack<List<Integer>> st = new Stack();
        int listSize = arraylisyt.size();
        //子集合的长度
        int toIndex = size;
        for (int i = 0; i < arraylisyt.size(); i += size) {
            if (i + size > listSize) {
                toIndex = listSize - i;
            }
            List<Integer> newList = arraylisyt.subList(i, i + toIndex);
            st.push(newList);
        }
        return st;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> arraylisyt = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            arraylisyt.add(i);
        }
        System.err.println(arraylisyt.size());
        int pageSize = 10;
        SplitList t2 = new SplitList();
        List<Integer> count = new ArrayList<>();
        Stack<List<Integer>> lists = t2.groupList(arraylisyt, pageSize);
        Object ob = new Object();
        List<FutureTask<List<Integer>>> tas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            FutureTask<List<Integer>> task1 = new FutureTask(() -> {
                List<Integer> count1 = new ArrayList<>();
                Thread.sleep(10000);
                int j = 0;
                while (true) {
                    if (lists.isEmpty()) {
                        break;
                    }
                    List<Integer> pop = null;
                    synchronized (ob) {
                        if (lists.isEmpty()) {
                            break;
                        }
                        pop = lists.pop();
                    }
                    System.err.println("task1:" + pop.toString());
                    for (Integer integer : pop) {
                        j += integer;
                    }
                }
                System.err.println("task1:" + j);
                count1.add(1);
                return count1;
            });
            tas.add(task1);
        }


//        FutureTask<List<Integer>> task2 = new FutureTask(() -> {
//            List<Integer> count1 = new ArrayList<>();
//            int i = 0;
//            while (true) {
//                if (lists.isEmpty()) {
//                    break;
//                }
//                List<Integer> pop = null;
//                synchronized (ob) {
//                    if (lists.isEmpty()) {
//                        break;
//                    }
//                    pop = lists.pop();
//                }
//                System.err.println("task2:" + pop.toString());
//                for (Integer integer : pop) {
//                    i += integer;
//                }
//            }
//            System.err.println("task2:" + i);
//            count1.add(2);
//            return count1;
//        });
        for (FutureTask ta : tas) {
            new Thread(ta).start();
        }
        for (FutureTask<List<Integer>> ta : tas) {
            List<Integer> integers = ta.get();
            count.addAll(integers);
        }

//        new Thread(task2, "task2").start();
//        count.addAll(task1.get());
//        count.addAll(task2.get());
        System.err.println(count.toString());

    }
}
