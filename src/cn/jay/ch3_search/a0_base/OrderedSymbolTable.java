package cn.jay.ch3_search.a0_base;


/**
 * @ClassName: OrderedSymbolTable
 * @Description: 有序符号表
 * @Author: jay
 * @Date: 2021/5/29 19:18
 */
public interface OrderedSymbolTable<K extends Comparable<K>,V> extends UnorderedSymbolTable<K, V> {
    K min();

    K max();

    K floor(K k);

    K ceiling(K k);

    int rank(K k);

    K select(int rank);

    void deleteMin();

    void deleteMax();

    int size(K lo, K hi);

    Iterable<K> keys(K lo, K hi);

    Iterable<K> keys();
}
