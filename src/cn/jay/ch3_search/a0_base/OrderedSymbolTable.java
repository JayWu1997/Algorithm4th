package cn.jay.ch3_search.a0_base;


/**
 * @ClassName: OrderedSymbolTable
 * @Description: 有序符号表
 * @Author: jay wu
 * @Date: 2021/5/29 19:18
 * @Version: 1.0
 */
public interface OrderedSymbolTable<K extends Comparable<K>,V> extends UnorderedSymbolTable<K, V> {
    K min();

    K max();

    K floor(K k);

    K ceiling(K k);

    int rank(K k);

    K getKeyByRank(int rank);

    void deleteMin();

    void deleteMax();

    Iterable<K> keys(K lo, K hi);
}
