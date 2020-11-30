package com.zeyu.demo.ftaay;

public final class TootFactory {
    public static Toot getToot(String name) {
        Class<?> Clazz = null;
        Toot t = null;
        try {
            Clazz = Class.forName("com.zeyu.demo.ftaay." + name);
            t = (Toot) Clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
