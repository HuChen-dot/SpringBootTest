package com.zeyu.demo.designMode.combination;

//1：部门，，，员工，，，部门可以添加子部门和员工
//2：员工不能添加任何部门或员工
//理解：
//  把部门当成菜单，把员工当作菜单项

import com.zeyu.demo.test.MyArrayList;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

abstract class Component {
    private String name;//名称
    protected Stack stack = new Stack();

    public Component(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public abstract void print(String s);

    //部门方法
    public void add(Component meun) {
        throw new RuntimeException("不可调用此方法");
    }

    public String getDescribe() {
        throw new RuntimeException();
    }

    //员工方法
    public Integer getAge() {

        throw new RuntimeException();
    }

}

//部门
class Dicp extends Component {
    private String describe;//部门描述
    private List<Component> list = new ArrayList<>(10);

    public Dicp(String name, String describe) {
        super(name);
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<Component> getList() {
        return list;
    }

    public void setList(List<Component> list) {
        this.list = list;
    }

    //添加
    @Override
    public void add(Component meun) {
        list.add(meun);
    }

    @Override
    public void print(String s) {
        System.err.println(s + "\t" + getName() + ":" + describe);
        Iterator<Component> iterator = list.iterator();
        while (iterator.hasNext()) {
            Component next = iterator.next();
            next.print(s + "\t");
        }
    }
}


//员工
class Staff extends Component {
    private Integer age; //员工年龄

    public Staff(String name, Integer age) {
        super(name);
        this.age = age;
    }

    @Override
    public void print(String s) {
        System.err.println(s + "\t" + getName() + ":" + age);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class MyIterator implements Iterator {
    Stack<Iterator<Component>> sc = new Stack();

    public MyIterator(Iterator<Component> co) {
        sc.push(co);
    }

    @Override
    public boolean hasNext() {
        if (sc.empty()) {
            return false;
        }
        Iterator<Component> pk = sc.peek();
        if (!pk.hasNext()) {
            sc.pop();
            return hasNext();
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        Iterator<Component> peek = sc.peek();
        Component next = peek.next();
        if (next instanceof Dicp) {
            sc.push(((Dicp) next).getList().iterator());
        }
        return next;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Dicp Dicp1 = new Dicp("总公司", "这是总公司");
        Dicp Dicp2 = new Dicp("分公司", "这是分公司");
        Dicp Dicp3 = new Dicp("分公司2", "这是分公司2");
        Dicp Dicp4 = new Dicp("财务部门", "财务部门");
        Staff Staff1 = new Staff("张三", 18);
        Staff Staff2 = new Staff("李四", 23);
        Staff Staff3 = new Staff("王五", 45);
        Staff Staff4 = new Staff("赵六", 11);

        Dicp3.add(Dicp4);
        Dicp3.add(Staff1);
        Dicp2.add(Staff2);
        Dicp4.add(Staff3);
        Dicp4.add(Staff4);
        Dicp1.add(Dicp2);
        Dicp1.add(Dicp3);
//        Dicp1.print("");
        MyIterator it = new MyIterator(Dicp1.getList().iterator());

        while (it.hasNext()) {
            Component next = (Component) it.next();
            System.err.println(next.getName());
        }

    }

    public void print(Iterator<Component> iterator) {
        while (iterator.hasNext()) {
            Component next = iterator.next();
            if (next instanceof Dicp) {
                Dicp pd = (Dicp) next;
                System.err.println(pd.getName() + ":" + pd.getDescribe());
                Iterator<Component> iterator1 = pd.getList().iterator();
                print(iterator1);
            } else {
                Staff pd = (Staff) next;
                System.err.println("\t" + pd.getName() + ":" + pd.getAge());
            }
        }
    }
}
