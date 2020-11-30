package com.zeyu.demo.designMode.decorator;

/**
 * 食物
 */
abstract class Food {
    private String content;

    public Food(String content) {
        this.content = content;
    }

    //价格
    public abstract double price();

    //描述
    public String describe() {
        return content;
    }

    ;
}

/**
 * 手抓饼
 */
class grasping extends Food {

    public grasping() {
        super("手抓饼");
    }

    //价格
    @Override
    public double price() {
        return 5;
    }

    //描述
    @Override
    public String describe() {
        return super.describe();
    }
}

//调料
abstract class flavour extends Food {

    public flavour(String content) {
        super(content);
    }
}

/**
 * 生菜
 */
class lettuce extends flavour {
    Food food;

    public lettuce(Food food) {
        super("生菜");
        this.food = food;
    }

    @Override
    public double price() {
        return food.price() + 1;
    }

    //描述
    @Override
    public String describe() {
        return food.describe() + "生菜";
    }
}

/**
 * 培根
 */
class Bacon extends flavour {
    Food food;

    public Bacon(Food food) {
        super("培根");
        this.food = food;
    }

    @Override
    public double price() {
        return food.price() + 3;
    }

    //描述
    @Override
    public String describe() {
        return food.describe() + "培根";
    }
}

/**
 * 装饰器模式
 */


public class AppTest {
    public static void main(String[] args) {
        Food f1 = new grasping();
        Food f2 = new lettuce(f1);
        Food f3 = new Bacon(f2);
        Food f4 = new Bacon(f3);
        System.err.println(f4.describe() + "--" + f4.price());
    }
}
