package cn.yongjie.ch1.section3.stack.stackImplByList;

public interface StackByList<T> extends Iterable<T>{
    void push(T t);
    T pop();
    int size();
    boolean isEmpty();
}
