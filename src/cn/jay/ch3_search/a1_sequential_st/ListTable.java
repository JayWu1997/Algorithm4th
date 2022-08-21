package cn.jay.ch3_search.a1_sequential_st;

import cn.jay.ch3_search.a0_base.SymbolTable;

import java.util.ArrayList;

/**
 * @ClassName: SequentialST
 * @Description: 由链表实现的顺序符号表
 * @Author: jay
 * @Date: 2021/5/20 17:20
 */

// 关于 泛型继承 的相关问题 https://stackoverflow.com/questions/65913465/unexpected-bound-error-on-generics-with-two-generics
public class ListTable<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private Node first;
    private int size = 0;


    private class Node{
        public K k;
        public V v;
        public Node next;

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListTable<String, Integer> st = new ListTable<String, Integer>();

        System.out.println("**** 测试put（） ****");
        for (int i = 0; i < 100; i++) {
            st.put("key:"+i, i);
            System.out.println(st.size());
        }

        System.out.println("**** 测试get（） ****");
        for (int i = 0; i < 100; i++) {
            System.out.println("get key ("+ i + "): " + st.get("key:"+i));
        }


        System.out.println("**** 测试delete（） ****");
        for (int i = 0; i < 100; i++) {
            st.delete("key:"+i);
            System.out.println("size：" + st.size());
        }


    }

    @Override
    public void put(K s, V integer) {
        Node temp = first;
        while(temp != null){
            if(temp.k.equals(s)) {
                temp.v = integer;
                return;
            }
            temp = temp.next;
        }

        first = new Node(s,integer, first);
        size++;
    }

    @Override
    public V get(K s) {
        Node temp = first;
        while(temp != null){
            if(temp.k.compareTo(s) == 0)
                return temp.v;
            temp = temp.next;
        }
        return null;
    }

    @Override
    public void delete(K s) {
        if(first==null) return;

        if(first.k.compareTo(s) == 0) {
            first = first.next;
            size--;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.k.compareTo(s) == 0) {
                temp.next = temp.next.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        ArrayList<K> result = new ArrayList<>();
        Node temp = first;
        while(temp.next != null){
            result.add(temp.k);
        }
        return  result;
    }

}
