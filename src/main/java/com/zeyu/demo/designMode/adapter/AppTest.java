package com.zeyu.demo.designMode.adapter;


import javax.swing.*;

class computer {
    public String show(TowPlug s) {
        return "我只能适配2个插头的插板,刚好我是：" + s.c();
    }
}

interface TowPlug {
    public String c();
}

class TherePlug {
    public String c() {
        return "我是三个插头的插板";
    }
}

class TowPlugAdapter implements TowPlug {
    TherePlug t;

    public TowPlugAdapter(TherePlug t) {
        this.t = t;
    }

    @Override
    public String c() {
        return t.c();
    }
}

/**
 * 适配器模式
 */

public class AppTest {
    public static void main(String[] args) {
        computer computer = new computer();
        String show = computer.show(new TowPlugAdapter(new TherePlug()));
        System.err.println(show);
    }
}
