package com.zeyu.demo.test;

import lombok.Data;

@Data
public class jiaoyiUser {
    private String name;
    private String ckpy;

    public jiaoyiUser() {

    }

    public jiaoyiUser(String name, String ckpy) {
        this.name = name;
        this.ckpy = ckpy;
    }
}
