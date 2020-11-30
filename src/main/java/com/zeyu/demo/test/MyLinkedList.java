package com.zeyu.demo.test;


class AppTest2{
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.add("aa");
        linkedList.add("bb");
        linkedList.add("cc");
        linkedList.remove("cc");
        System.err.println(linkedList.toString());
    }
}


public class MyLinkedList {
    private Node haad;// 头部
    private Node last;// 下一个

    class Node {
        private Object object;// 存储内容
        private Node next;// 头部信息

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    // 添加元素
    public void add(Object obj) {
        Node node = new Node();
        node.setObject(obj);
        if (haad == null) {
            haad = node;
        }
        if (last == null) {
            last = node;
        } else {
            last.setNext(node);
            last = node;
        }
    }

    /**
     * 删除元素
     */
    public void remove(Object obj) {
        Node farlt = haad;// 记录当前的对象
        Node last = null;// 记录上一个对象
        // 如果删除的元素在头部，先return出去
        if (farlt.getObject().equals(obj)) {
            farlt = farlt.getNext();
            haad = farlt;
            return;
        }
        if (farlt.getObject() == null) {
            System.err.println("是null");
        } else {
            System.err.println("不是");
        }
        while (!(farlt.getObject().equals(obj))) {
            if (farlt.getNext() == null) {
                return;
            }
            last = farlt;
            farlt = farlt.getNext();
        }
        last.setNext(farlt.getNext());
        farlt = null;
    }

    /**
     * 计算链表中的元素个数
     */
    public int size() {
        int size = 1;
        Node ferlt = haad;
        while (ferlt.getNext() != null) {
            size++;
            ferlt = ferlt.getNext();
        }
        return size;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Node ferlt = haad;
        if (ferlt != null) {
            buffer.append(ferlt.getObject());
            while (ferlt.getNext() != null) {
                buffer.append(ferlt.getNext().getObject());
                ferlt = ferlt.getNext();
            }
        }
        return buffer.toString();
    }
}

