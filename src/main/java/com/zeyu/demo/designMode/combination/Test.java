package com.zeyu.demo.designMode.combination;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


//部门
@Data
class Dipt {
    private List<Dipt> dp = new ArrayList<>(10);
    private int id;//id//部门id
    private String name;//部门名称
    private String describe;//描述
    private int parentId;//父id

    public Dipt() {
    }

    public Dipt(int id, String name, String describe, int parentId) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.parentId = parentId;
    }

    public void add(Dipt dt) {
        dp.add(dt);
    }

    public List<Dipt> get() {
        return dp;
    }
}

//部门树
class DiptTree extends Dipt {


    public DiptTree(int id, String name, String describe, int parentId) {
        super(id, name, describe, parentId);
    }

    public DiptTree() {
    }


}

class Tree extends Dipt {

}


//主程序
public class Test {
    public static void main(String[] args) {
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
        Test Test = new Test();
        Test.s(list);
        List<Dipt> dipts = Test.s1(list);
        for (Dipt t : dipts) {
            System.err.println(
                    t
            );
        }
    }


    //=====================方法一==================================================================
    Map<Integer, Dipt> maps = new ConcurrentHashMap<>(89);
    List<Dipt> lipt = new ArrayList<>(33);

    public void s(List<Dipt> list) {
        //进行排序和转换成map
        maps = list.stream().sorted((o1, o2) -> o1.getId() - o2.getId()
        ).collect(Collectors.toMap(Dipt::getId, dipt -> dipt));

    }

    public List<Dipt> s1(List<Dipt> list) {
        for (Dipt dipt : list) {
            // 如果查询出的列表里面该节点的没有父节点说明是顶级节点
            Dipt dipt1 = maps.get(dipt.getParentId());
            if (dipt1 == null) {
                //将顶级结果加入到结果集中
                lipt.add(dipt);
                continue;
            }
            //将自己加入到父节点中
            dipt1.getDp().add(dipt);
        }
        return lipt;
    }
//=======================方法二====================================================================
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
        Dipt dt = null;
        for (Dipt dipt1 : dt2.get()) {
            for (Dipt dipt : list) {
                if (dipt1.getId() == dipt.getParentId()) {
                    dt = new Dipt();
                    dt.add(dipt);
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
