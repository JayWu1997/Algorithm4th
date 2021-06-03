package cn.jay.ch3.a2_bsoast;

import cn.jay.ch3.a0_base.OrderedSymbolTable;

import java.util.Arrays;

/**
 * @ClassName: BinarySearchOrderedSymbleTable
 * @Description: 有序符号表和数组实现的二分查找表，优点是查找快，缺点是插入慢
 * @Author: jay wu
 * @Date: 2021/5/29 19:59
 * @Version: 1.0
 */
public class BinarySearchOrderedArraySymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private int size;

    private K[] keys;

    private V[] values;

    private int capacity = 8;

    BinarySearchOrderedArraySymbolTable(){}

    /**
     * @Method BinarySearchOrderedSymbolTable
     * @Description 初始化二叉搜索符号表
     * @param capacity 初始容量
     */
    BinarySearchOrderedArraySymbolTable(int capacity){
        if (capacity > 8) {
            this.capacity = capacity;
        }
    }

    private void resize(){
        if(size > capacity/2){
            keys = Arrays.copyOf(keys, 2*capacity);
            values = Arrays.copyOf(values, 2*capacity);
        }
        else if(size < capacity/4 && size > 8){
            keys = Arrays.copyOf(keys, capacity/2);
            values = Arrays.copyOf(values, capacity/2);
        }
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        return keys[size-1];
    }

    @Override
    public K floor(K k) {
        //TODO
    }

    @Override
    public K ceiling(K k) {
        //TODO
    }

    // 返回k在keys中的排名，k不一定要在符号表中
    @Override
    public int rank(K k) {
        if(k.compareTo(keys[0]) <= 0) return 0;
        else if(k.compareTo(keys[size-1]) > 0) return size;
        else return rankHelp1(k, 0, size - 1);
    }

    public int rankHelp1(K k, int left, int right){
        int mid = (left+right)/2;
        if(k.compareTo(keys[mid]) == 0) return mid;
        else if(k.compareTo(keys[mid]) > 0){
            if(k.compareTo(keys[mid+1]) < 0) return mid+1;
            else return rankHelp1(k, );
        }
    }

    @Override
    public K getKeyByRank(int rank) {
        //TODO
    }

    @Override
    public void deleteMin() {
        //TODO
    }

    @Override
    public void deleteMax() {
        //TODO
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        //TODO
    }

    @Override
    public void put(K k, V v) {

        // 延迟初始化
        if(size == 0){
            keys = (K[]) new Comparable[this.capacity];
            values = (V[]) new Object[this.capacity];
        }

        // 开始插入
        int rank = rank(k);
        for (int i = size; i > rank-1; i--) {
            keys[i] = k[i-1];
            values[i] = values[i-1];
        }
        keys[rank] = k;
        values[rank] = v;

        size++;
        resize();
    }

    @Override
    public V get(K k) {
        //TODO
    }

    @Override
    public void delete(K k) {
        //TODO
    }

    @Override
    public int size() {
        //TODO
    }

    @Override
    public Iterable<K> keys() {
        //TODO
    }
}
