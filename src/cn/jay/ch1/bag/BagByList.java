package cn.jay.ch1.bag;

public interface BagByList<T> extends Iterable<T>{
    void add(T t);
    int size();
    boolean isEmpty();
}
