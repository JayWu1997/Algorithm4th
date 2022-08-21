package cn.jay.ch1_base.queue;

public interface Queue<T> extends Iterable<T>{
    void enqueue(T t);
    T dequeue();
    int size();
}
