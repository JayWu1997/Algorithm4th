package cn.jay.ch1_base.queue;

import java.util.Iterator;

// 头出尾进,尾指向头
public class QueueImpl<T> implements Queue<T> {
    private Node in;
    private Node out;
    private int nodeNum;

    @Override
    public void enqueue(T t) {
        Node oldIn = in;
        in = new Node();
        in.item = t;
        if (size() == 0)
            out = in;
        else
            oldIn.next = in;
        nodeNum++;
    }

    @Override
    public T dequeue() {
        T t = out.item;
        out = out.next;
        nodeNum--;
        return t;
    }

    @Override
    public int size() {
        return nodeNum;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int nodeNumTemp = nodeNum;
            Node outTemp = out;

            @Override
            public boolean hasNext() {
                return nodeNumTemp != 0;
            }

            @Override
            public T next() {
                if (outTemp == null) return null;
                T result = outTemp.item;
                outTemp = outTemp.next;
                nodeNumTemp--;
                return result;
            }
        };
    }

    class Node {
        T item;
        Node next;
    }

    public static void main(String[] args) {
        Queue<String> queue = new QueueImpl<>();

        // 测试装载
        for (int i = 1; i <= 10; i++)
            queue.enqueue("String No" + i);

        // 测试iterator（）
        System.out.println("当前队列内的内容");
        for (String s : queue)
            System.out.println(s);

        // 测试出队
        System.out.println();
        for (String s : queue) System.out.println("出队:" + queue.dequeue() + "\t\t当前queue大小：" + queue.size());
    }
}
