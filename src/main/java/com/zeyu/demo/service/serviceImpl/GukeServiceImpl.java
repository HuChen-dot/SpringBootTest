package com.zeyu.demo.service.serviceImpl;


import com.zeyu.demo.designMode.combination.Test;
import com.zeyu.demo.mapper.GukeMapper;
import com.zeyu.demo.pojo.Dipt;
import com.zeyu.demo.pojo.Guke;
import com.zeyu.demo.service.GukeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
public class GukeServiceImpl implements GukeService {

    Map<Integer, Dipt> maps = new ConcurrentHashMap<>(89);
    List<Dipt> lipt = new ArrayList<>(33);

    public void s(List<Dipt> list) {
        maps = list.stream().sorted((o1, o2) -> o1.getId() - o2.getId()
        ).collect(Collectors.toMap(Dipt::getId, dipt -> dipt));

    }

    public List<Dipt> s1(List<Dipt> list) {
        for (Dipt dipt : list) {
            //查找
            Dipt dipt1 = maps.get(dipt.getParentId());
            if (dipt1 == null) {
                lipt.add(dipt);
                continue;
            }
            dipt1.getDp().add(dipt);
        }
        return lipt;
    }


    @Autowired
    GukeMapper gukeMapper;

    @Override
    public Dipt getGukeById(Integer id) {

        List<Dipt> list = new ArrayList<>(10);
        Dipt Dipt1 = new Dipt(1, "总公司", "这是总公司", 0);
        Dipt Dipt2 = new Dipt(2, "分公司1", "这是分公司1", 1);
        Dipt Dipt3 = new Dipt(3, "分公司2", "这是分公司2", 1);
        Dipt Dipt4 = new Dipt(4, "分公司3", "这是分公司3", 1);
        Dipt Dipt5 = new Dipt(5, "开发部", "这是开发部", 2);
        Dipt Dipt6 = new Dipt(6, "财务部", "这是财务部", 3);
        Dipt Dipt7 = new Dipt(7, "测试部", "这是测试部", 4);
        Dipt Dipt8 = new Dipt(8, "鼓励部", "这是鼓励部", 3);
        Dipt Dipt9 = new Dipt(9, "人事部", "这是人事部", 2);
        list.add(Dipt7);
        list.add(Dipt2);
        list.add(Dipt9);
        list.add(Dipt3);
        list.add(Dipt5);
        list.add(Dipt6);
        list.add(Dipt8);
        list.add(Dipt1);
        list.add(Dipt4);
        GukeServiceImpl Test = new GukeServiceImpl();
        Dipt itretr = Test.itretr(list);
        Test.itretr2(list, itretr);
//        s(list);
//        List<Dipt> dipts = s1(list);
        return itretr;
//        Guke gukeById = gukeMapper.getGukeById(1l);
//        System.err.println(gukeById);
//        throw new IndexOutOfBoundsException("wer");
//        return gukeById;
    }

    public Dipt itretr(List<Dipt> list) {
        Dipt dt = null;
        for (Dipt dipt : list) {
            if (dipt.getParentId() == 0) {
                dt = new Dipt();
                dt.add(dipt);
            }
        }
        return dt;
    }

    public boolean itretr2(List<Dipt> list, Dipt dt2) {
        boolean is = false;
        for (Dipt dipt1 : dt2.get()) {
            for (Dipt dipt : list) {
                if (dipt1.getId() == dipt.getParentId()) {
                    dipt1.add(dipt);
                    is = true;
                }
            }
            if (!is) {
                return true;
            }
            itretr2(list, dipt1);
        }
        return is;
    }
}
