package com.zeyu.demo.test;

public class xian implements Runnable {
    private Object obj;

    public xian(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        int ss = 0;
        synchronized (obj) {
            for (int i = 1; i <= 5000; i++) {
                ss++;
                System.err.println(i);
                if (ss == 100) {
                    ss = 0;
                    obj.notifyAll();
                    try {
                        if (i != 5000)
                            obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "hsdfeiugf";
        String substring = s.substring(0, 3);
        System.err.println(substring);
    }
}
