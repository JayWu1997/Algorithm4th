package cn.jay.ch1_base.stack.unfixedcapacitystackwitharray;

public interface ArrayStack<T> extends Iterable<T>{

    void push(T item);
    T pop();
    T peek();
    int size();
    boolean isEmpty();
    int getCapacity();

}
