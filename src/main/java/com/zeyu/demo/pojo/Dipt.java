package com.zeyu.demo.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dipt {
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
