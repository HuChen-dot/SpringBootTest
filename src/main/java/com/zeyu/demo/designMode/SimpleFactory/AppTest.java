package com.zeyu.demo.designMode.SimpleFactory;

/**
 * 动物
 */
interface Animal {
    void move();
}

/**
 * 人
 */
class Person implements Animal {
    @Override
    public void move() {
        System.err.println("人是两条腿跑");
    }
}

/**
 * 狗
 */
class Doc implements Animal {
    @Override
    public void move() {
        System.err.println("狗是两条腿跑");
    }
}

/**
 * 大雁
 */
class wildGoose implements Animal {
    @Override
    public void move() {
        System.err.println("大雁是两条翅膀飞");
    }
}

class Factory {
    public static Animal getAnim(int i) {
        Animal animal = null;
        switch (i) {
            case 1:
                animal = new Person();
                break;
            case 2:
                animal = new Doc();
                break;
            case 3:
                animal = new wildGoose();
        }
        return animal;
    }
}

/**
 * 简单工厂的优点：
 * 当需要生产的类比较少时，更加简单明了；
 * <p>
 * 缺点是：
 * 当需要增加新的类时，需要更改工厂类，同时在工厂里面新增一个分支；
 * 当类非常多时，工厂类会非常的臃肿；
 */

public class AppTest {
    public static void main(String[] args) {
        Animal anim = Factory.getAnim(1);
        anim.move();
        Animal anim1 = Factory.getAnim(2);
        anim1.move();
        Animal anim2 = Factory.getAnim(3);
        anim2.move();
    }
}
