package cn.jay.ch1_base.stack;

public interface Stack<T> extends Iterable<T>{
    void push(T t);
    T pop();
    T peek();
    int size();
    boolean isEmpty();

}
