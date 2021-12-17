package cn.jay.ch1_base.stack.stackImplByList;

public interface StackByList<T> extends Iterable<T>{
    void push(T t);
    T pop();
    int size();
    boolean isEmpty();
}