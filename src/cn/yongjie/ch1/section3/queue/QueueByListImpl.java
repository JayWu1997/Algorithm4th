package cn.yongjie.ch1.section3.queue;

import java.util.Iterator;

// 头出尾进,尾指向头
public class QueueByListImpl<T> implements QueueByList<T>{
    private Node in;
    private Node out;
    private int nodeNum;

    @Override
    public void enqueue(T t) {
        Node oldIn = in;
        in = new Node();
        in.item = t;
        if(size() == 0)
            out = in;
        else
            oldIn.next = in;
        nodeNum++;
    }

    @Override
    public T dequeue() {
        if(size() == 0){
            System.out.println("队列为空");
            return null;
        }
        else{
            T t = out.item;
            out = out.next;
            nodeNum--;
            return t;
        }

    }

    @Override
    public int size() {
        return nodeNum;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node outTemp = out;
            @Override
            public boolean hasNext() {
                return nodeNum!=0;
            }

            @Override
            public T next() {
                T result = outTemp.item;
                outTemp = outTemp.next;
                return result;
            }
        };
    }

    class Node{
        T item;
        Node next;
    }

    public static void main(String[] args) {
        QueueByList<String> queue = new QueueByListImpl<>();
        for(int i = 1; i <= 10; i++){
            queue.enqueue("String No" + i);
        }
        for(String s : queue) System.out.println("出队:" + queue.dequeue() + "\t\t当前queue大小：" + queue.size());
    }
}
