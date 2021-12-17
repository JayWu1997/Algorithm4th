package cn.jay.ch1_base.bag;

public interface BagByList<T> extends Iterable<T>{
    void add(T t);
    int size();
    boolean isEmpty();
}
