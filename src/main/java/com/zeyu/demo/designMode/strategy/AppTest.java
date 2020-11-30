package com.zeyu.demo.designMode.strategy;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

/**
 * 角色父类
 */
abstract class Rost {
    Attack att;

    //角色名
    abstract void name();

    //攻击方式
    public void attactPattern() {
        att.pattern();
    }

    public Attack getAtt() {
        return att;
    }

    public void setAtt(Attack att) {
        this.att = att;
    }
}

/**
 * 攻击方式接口类
 */
interface Attack {
    void pattern();
}

/**
 * 攻击方式
 */
class bow implements Attack {
    @Override
    public void pattern() {
        System.err.println("弓箭攻击伤害15");
    }
}

/**
 * 攻击方式
 */
class sword implements Attack {
    @Override
    public void pattern() {
        System.err.println("剑攻击伤害20");
    }

}

/**
 * 攻击方式
 */
class Fist implements Attack {
    @Override
    public void pattern() {
        System.err.println("拳头攻击伤害5");
    }
}

/**
 * 角色子类
 */
class Knight extends Rost {
    @Override
    void name() {
        System.err.println("我是骑士，我的初始攻击时剑");
        this.setAtt(new sword());
    }

}

/**
 * 角色子类
 */
class Knig extends Rost {
    @Override
    void name() {
        System.err.println("我是国王，我的初始攻击时拳头");
        this.setAtt(new Fist());
    }
}

/**
 * 角色子类
 */
class Queen extends Rost {
    @Override
    void name() {
        System.err.println("我是公主，我的初始攻击时拳头");
        this.setAtt(new Fist());
    }
}

/**
 * 角色子类
 */
class Princess extends Rost {
    @Override
    void name() {
        System.err.println("我是王后，我的初始攻击是弓箭");
        this.setAtt(new bow());
    }

}

class Files {
    static Properties p = new Properties();


    //加载配置文件
    public void getFile() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader
                (Files.class.getClassLoader().getResourceAsStream("test.properties"), "utf-8");
        p.load(inputStreamReader);
    }

    public String getValue(String key) {
        try {
            this.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p.getProperty(key);
    }

    public static void main(String[] args) {
        Files f = new Files();
        System.err.println(f.getValue("1"));
    }
}


class GetObject {

    public static Object get(String key) {
        Files f = new Files();
        try {
            f.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = f.getValue(key);
        Object o = null;
        try {
            Class<?> clazz = Class.forName(value);
            o = clazz.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}

/**
 * 策略模式
 */

public class AppTest {
    public static void main(String[] args) {
        Scanner yu = new Scanner(System.in);
        System.out.println("请选择初始角色:1:公主，2骑士，3：国王，4：王后");
        String next = yu.next();
        Rost o = (Rost) GetObject.get(next);
        o.name();
        while (true) {
            int i = (int) (Math.random() * 10);
            if (i == 4) {
                System.err.println("吃到道具，攻击能力变成剑");
                o.setAtt(new sword());
            }
            if (i == 6) {
                System.err.println("吃到道具，攻击能力变成弓箭");
                o.setAtt(new bow());
            }
            if (i == 8) {
                System.err.println("吃到道具，攻击能力变成拳头");
                o.setAtt(new Fist());
            }
            if (i == 3) {
                System.err.println("游戏结束");
                break;
            }
            o.attactPattern();
        }

    }
}
