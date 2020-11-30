package com.zeyu.demo.designMode.abstractFactory;


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
class WildGoose implements Animal {
    @Override
    public void move() {
        System.err.println("大雁是两条翅膀飞");
    }
}

class AnimalFrctory {
    static Animal getAnimal(String a) {
        try {
            Class<?> clazz = Class.forName(a);
            Animal o = (Animal) clazz.newInstance();
            return o;
        } catch (Exception e) {

        }

        return null;
    }
}


//==================================================================


/**
 * 工厂的优点：
 * 只需要让类实现一个抽象类，就可以通过传递类的全地址实例化类
 * 实际中可以把类的全类名和类名写在配置文件中，读取配置文件，实现实例化不同的类
 */
public class AppTest {
    public static void main(String[] args) {
        Animal animal = AnimalFrctory.getAnimal("com.zeyu.demo.designMode.abstractFactory.Person");
        animal.move();
    }
}