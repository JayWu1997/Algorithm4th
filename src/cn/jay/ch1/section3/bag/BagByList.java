package cn.jay.ch1.section3.bag;

public interface BagByList<T> extends Iterable<T>{
    void add(T t);
    int size();
    boolean isEmpty();
}
