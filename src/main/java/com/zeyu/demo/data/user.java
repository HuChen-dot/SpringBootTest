package com.zeyu.demo.data;

import lombok.Data;

@Data
public class user implements Comparable<user> {
    public String name;
    public int age;
    public int scloe;

    public user() {
    }

    ;

    public user(String name, int age, int scloe) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", scloe=" + scloe +
                '}';
    }

    @Override
    public int compareTo(user o) {

        return o.age - this.age;
    }
}
