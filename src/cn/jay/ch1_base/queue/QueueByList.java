package cn.jay.ch1_base.queue;

public interface QueueByList<T> extends Iterable<T>{
    void enqueue(T t);
    T dequeue();
    int size();
}
