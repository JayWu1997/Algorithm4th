package cn.yongjie.ch1.section3.stack.stackImplByList;

import java.util.Iterator;

public class StackByListImpl<T> implements StackByList<T>{
    private int itemNum;
    private Node top;

    @Override
    public void push(T t) {
        Node newNode = new Node();
        newNode.item = t;
        newNode.next = top;
        top = newNode;
        itemNum++;
    }

    @Override
    public T pop() {
        T result = top.item;
        top = top.next;
        itemNum--;
        return result;
    }

    public T peek(){
        return top.item;
    }

    @Override
    public int size() {
        return itemNum;
    }

    @Override
    public boolean isEmpty() {
        return top ==null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node topTemp = top;
            @Override
            public boolean hasNext() {
                return topTemp !=null;
            }

            @Override
            public T next() {
                Node node = topTemp;
                topTemp = topTemp.next;
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
