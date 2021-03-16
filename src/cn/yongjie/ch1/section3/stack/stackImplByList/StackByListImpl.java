package cn.yongjie.ch1.section3.stack.stackImplByList;

import java.util.Iterator;

public class StackByListImpl<T> implements StackByList<T>{
    private int itemNum;
    private Node head;

    @Override
    public void push(T t) {
        Node newNode = new Node();
        newNode.item = t;
        newNode.next = head;
        head = newNode;
        itemNum++;
    }

    @Override
    public T pop() {
        T result = head.item;
        head = head.next;
        itemNum--;
        return result;
    }

    @Override
    public int size() {
        return itemNum;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node headTemp = head;
            @Override
            public boolean hasNext() {
                return headTemp!=null;
            }

            @Override
            public T next() {
                Node node = headTemp;
                headTemp = headTemp.next;
                return node.item;
            }
        };
    }

    class Node {
        T item;
        Node next;
    }

    public static void main(String[] args) {
        StackByList<String> stack = new StackByListImpl<>();
        for(int i = 0; i < 20; i ++){
            stack.push("Item:" + (i+1));
            System.out.println("添加Item：" + (i+1) + "\t\t当前栈个数：" + stack.size());
        }
        for(String s: stack){
            System.out.println("出栈：" + stack.pop() + "\t\t当前栈个数：" + stack.size());
        }
    }
}
