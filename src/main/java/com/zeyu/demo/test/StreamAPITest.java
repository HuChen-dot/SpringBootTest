package com.zeyu.demo.test;

import com.zeyu.demo.data.user;
import com.zeyu.demo.pojo.Guke;

import java.rmi.ServerError;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 练习StreamAPI
 */
public class StreamAPITest {
    public static void main(String[] args) {
        user user = new user("张三", 1, 22);
        user user1 = new user("张三", 1, 33);
        user user2 = new user("王五", 3, 55);
        user user3 = new user("马六", 4, 79);
        List<user> list = new ArrayList<>();
        list.add(user);
//        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        DoubleSummaryStatistics collect1 = list.stream()
                .collect(Collectors.summarizingDouble((use) -> use.getAge()));
        System.err.println("获取集合中年龄最大值" + collect1.getMax());
        System.err.println("获取集合中年龄最小值" + collect1.getMin());
        System.err.println("获取集合中年龄最平均值" + collect1.getAverage());
        System.err.println("获取集合的大小长度" + collect1.getCount());
        System.err.println("获取集合中年龄的总和" + collect1.getSum());

        list.stream().map((usr) -> usr.getName())
                .collect(Collectors.joining(","));

//        collect1.entrySet().
//
//                forEach((item) -> System.err.println(item.getValue().size()));


        Stream<user> stream = list.stream();
//        stream.distinct()
//                .filter((e) -> e.age > 1)
//                .forEach((x) -> System.err.println(x));
        Collection<Integer> collect = stream.map((x) -> x.getAge())
                .collect(Collectors.toCollection(HashSet::new));
        System.err.println(collect);

//        stream.flatMap((item)->item.getAge()

        List<Integer> ine = new ArrayList<>();
        for (com.zeyu.demo.data.user user4 : list) {
            Integer dd = 0;
            dd = user4.getAge();
            ine.add(dd);
        }
        System.err.println(ine);
        boolean b = list.stream().allMatch((item) -> item.getAge() < 30);
        System.err.println(b);
        double s = 1.0;
        double s1 = 2.0;
        System.err.println(Double.compare(s, s1));

        System.err.println(list.stream().min((o1, o2) -> o1.getAge() - o2.getAge()));
        Integer reduce = list.stream()
                .map((item -> item.getAge()))
                .reduce(0, (x, y) -> x + y);
        System.err.println(reduce);

        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        entries.forEach((item) -> {
            System.err.println(item.getKey() + ":" + item.getValue());
        });

        Set<Integer> integers = map.keySet();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.err.println(map.get(iterator.next()));
        }
    }

}
