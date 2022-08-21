package cn.jay.ch1_base.bag;

import java.util.Iterator;


public class ListBag<T> implements Bag<T> {
    private Node head;
    private int nodeNum;


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node headTemp = head;

            @Override
            public boolean hasNext() {
                return headTemp != null;
            }

            @Override
            public T next() {
                T item = headTemp.item;
                headTemp = headTemp.next;
                return item;
            }
        };
    }

    @Override
    public void add(T t) {
        Node oldHead = head;
        head = new Node();
        head.item = t;
        head.next = oldHead;
        nodeNum++;
    }

    @Override
    public int size() {
        return nodeNum;
    }

    @Override
    public boolean isEmpty() {
        return nodeNum == 0;
    }

    class Node {
        T item;
        Node next;
    }

    public static void main(String[] args) {
        Bag<String> bag = new ListBag<>();
        for (int i = 1; i <= 10; i++) {
            bag.add("String No" + i);
            System.out.println("装入：" + "String No" + i);
        }

        System.out.println("--------结果--------");
        for (String s : bag)
            System.out.println(s);
    }
}
