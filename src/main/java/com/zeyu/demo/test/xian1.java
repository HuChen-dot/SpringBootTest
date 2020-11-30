package com.zeyu.demo.test;

public class xian1 implements Runnable {
    private Object obj;

    public xian1(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        int ss = 0;
        synchronized (obj) {
            for (int i = 1; i <= 500; i++) {
                ss++;
                System.err.println(i);
                if (ss == 10) {
                    ss = 0;
                    obj.notifyAll();
                    System.err.println("********************************");
                    try {
                        if (i != 500)
                            obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
