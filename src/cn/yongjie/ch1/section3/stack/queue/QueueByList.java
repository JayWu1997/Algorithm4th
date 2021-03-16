package cn.yongjie.ch1.section3.stack.queue;

public interface QueueByList<T> extends Iterable<T>{
    void enqueue(T t);
    T dequeue();
    int size();
}
