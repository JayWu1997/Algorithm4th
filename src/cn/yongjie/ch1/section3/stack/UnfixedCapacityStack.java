package cn.yongjie.ch1.section3.stack;

public interface UnfixedCapacityStack<T> extends Iterable<T>{

    void push(T item);
    T pop();
    int getSize();
    void resize(int newSize);
    boolean isEmpty();
    int getCapacity();

}
