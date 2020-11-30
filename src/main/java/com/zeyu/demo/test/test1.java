package com.zeyu.demo.test;


import com.zeyu.demo.pojo.Ce;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class test1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

//        jiaoyiUser jiaoyiUser1 = new jiaoyiUser("zhangsan", "上海");
//        jiaoyiUser jiaoyiUse2 = new jiaoyiUser("lisi", "北京");
//        jiaoyiUser jiaoyiUser3 = new jiaoyiUser("wangwu", "北京");
//        jiaoyiUser jiaoyiUser4 = new jiaoyiUser("zhaoliu", "广州");
//        jiaoyiUser jiaoyiUser5 = new jiaoyiUser("tianqi", "东莞");
//        jiaoyilei[] jiaoyilei = {
//                new jiaoyilei(jiaoyiUser1, 2001, 526541),
//                new jiaoyilei(jiaoyiUse2, 2002, 15),
//                new jiaoyilei(jiaoyiUser3, 2003, 4),
//                new jiaoyilei(jiaoyiUser4, 2004, 64),
//                new jiaoyilei(jiaoyiUser5, 2001, 45466),
//                new jiaoyilei(jiaoyiUser5, 2003, 8794),
//        };
        Instant now = Instant.now();
        LongStream.rangeClosed(0, 5000000L)
                .sequential()
                .reduce(0, (x, y) -> x + y);
        Instant now1 = Instant.now();
        System.err.println(Duration.between(now, now1).toMillis());
        //1:找出2001年的交易，并按照金额低到高排序
//        Arrays.stream(jiaoyilei)
//                .filter((item) -> item.getYear() == 2001)
//                .sorted((e1, e2) -> e1.getValue() - e2.getValue())
//                .forEach((item) -> System.err.println(item));
//
//        //2:根据城市分组
//        Arrays.stream(jiaoyilei)
//                .map((item) -> item.getJiaoyiUser().getCkpy())
//                .distinct().forEach((x) -> System.err.println(x));
//
//        //3:查看来自北京的交易员，并按名字排序
//        Arrays.stream(jiaoyilei)
//                .map((item) -> item.getJiaoyiUser())
//                .filter((item) -> "北京".equals(item.getCkpy()))
//                .sorted((e1, e2) -> -e1.getName().compareTo(e2.getName()))
//                .forEach((e) -> System.err.println(e));
//
//        //4:查看是否有在天津工作的交易员
//        boolean b = Arrays.stream(jiaoyilei)
//                .anyMatch((item) -> "天津".equals(item.getJiaoyiUser().getCkpy()));
//        System.err.println("是否又在天津工作的交易员" + b);
//
//        //5:计算在北京工作的交易员交易总额
//        Integer reduce = Arrays.stream(jiaoyilei)
//                .filter((item) -> "北京".equals(item.getJiaoyiUser().getCkpy()))
//                .map((item) -> item.getValue())
//                .reduce(0, (x, y) -> x + y);
//        System.err.println("交易总额是：" + reduce);
//
//        //6:查询所有交易额的最高金额
//        Optional<Integer> max = Arrays.stream(jiaoyilei)
//                .map(item -> item.getValue())
//                .max((e1, e2) -> e1 - e2);
//        System.err.println("最大交易额是：" + max.get());
//
//        //7:获取最小的交易额
//        Optional<Integer> min = Arrays.stream(jiaoyilei)
//                .map(item -> item.getValue())
//                .min((e1, e2) -> e1 - e2);
//        System.err.println("最小交易额是：" + min.get());
    }
}
