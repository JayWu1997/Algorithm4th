package cn.jay.ch1.queue;

public interface QueueByList<T> extends Iterable<T>{
    void enqueue(T t);
    T dequeue();
    int size();
}
