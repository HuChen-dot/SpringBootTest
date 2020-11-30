package com.zeyu.demo.designMode.prototype;

import lombok.Data;

import java.io.*;

@Data
class porsion implements Serializable {
    String name;
    Integer age;

    @Override
    public String toString() {
        return "porsion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//序列化对象
class SerializableTest<T> {

    public T get(T t) throws IOException, ClassNotFoundException {
        //向内存中写入数据
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(out);
        obj.writeObject(t);

        //从内存中读出数据
        ByteArrayInputStream byteArray = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteArray);
        T o = (T) in.readObject();

        return o;
    }
}

/**
 * 原型模式
 */
public class AppTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableTest s = new SerializableTest<porsion>();
        porsion p = new porsion();
        p.setAge(11);
        p.setName("陈虎");

        porsion o = (porsion) s.get(p);
        System.err.println(p == o);
        System.err.println(o);
    }
}
