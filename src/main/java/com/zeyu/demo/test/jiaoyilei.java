package com.zeyu.demo.test;

import lombok.Data;

@Data
public class jiaoyilei {
    private jiaoyiUser jiaoyiUser;
    private int year;
    private int value;

    public jiaoyilei() {
    }

    public jiaoyilei(com.zeyu.demo.test.jiaoyiUser jiaoyiUser, int year, int value) {
        this.jiaoyiUser = jiaoyiUser;
        this.year = year;
        this.value = value;
    }
}
