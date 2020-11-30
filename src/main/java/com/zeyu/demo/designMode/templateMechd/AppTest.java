package com.zeyu.demo.designMode.templateMechd;


abstract class MyTemplate {
    public abstract void exeuct();

    public void show() {
        System.err.println("开始");
        exeuct();
        System.err.println("结束");
    }
}

class Myshow extends MyTemplate {

    @Override
    public void exeuct() {
        System.err.println("这是模板方法");
    }
}

public class AppTest {
    public static void main(String[] args) {
        Myshow Myshow = new Myshow();
        Myshow.show();
    }
}
