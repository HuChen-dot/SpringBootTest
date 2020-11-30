package com.zeyu.demo.designMode.factoryMethod;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

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

interface AnimalFrctory {
    Animal getAnimal();
}

/**
 * 人工厂
 */
class PersonFrctory implements AnimalFrctory {

    @Override
    public Animal getAnimal() {
        return new Person();
    }
}

/**
 * 狗工厂
 */
class DocFrctory implements AnimalFrctory {

    @Override
    public Animal getAnimal() {
        return new Doc();
    }
}

/**
 * 大雁工厂
 */
class WildGooseFrctory implements AnimalFrctory {

    @Override
    public Animal getAnimal() {
        return new WildGoose();
    }
}
//==================================================================

class Ostrich implements Animal {

    @Override
    public void move() {
        System.err.println("鸵鸟翅膀和腿一起");
    }
}

class OstrichFrctory implements AnimalFrctory {

    @Override
    public Animal getAnimal() {
        return new Ostrich();
    }
}

/**
 * 工厂方法的优点：
 * 工厂方法，拥有简单工厂的全部优点，并且，上层修改了代码，下层并不需要修改和关心，
 * 下层需要扩展新的对象时，上层并不需要改动
 *
 * <p>
 * 缺点是：
 * 如果对象太多的话，就会产生类的爆炸，所以适合，类不多的情况下使用
 */
public class AppTest {
    public static void main(String[] args) {
        PersonFrctory P = new PersonFrctory();
        DocFrctory d = new DocFrctory();
        WildGooseFrctory w = new WildGooseFrctory();
        OstrichFrctory O = new OstrichFrctory();
        Animal animal3 = O.getAnimal();
        animal3.move();
        Animal animal = P.getAnimal();
        animal.move();
        Animal animal1 = d.getAnimal();
        animal1.move();
        Animal animal2 = w.getAnimal();
        animal2.move();

    }
}
