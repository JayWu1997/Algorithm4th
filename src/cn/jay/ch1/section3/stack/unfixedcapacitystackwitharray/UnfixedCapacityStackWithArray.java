package cn.jay.ch1.section3.stack.unfixedcapacitystackwitharray;

public interface UnfixedCapacityStackWithArray<T> extends Iterable<T>{

    void push(T item);
    T pop();
    int getSize();
    void resize(int newSize);
    boolean isEmpty();
    int getCapacity();

}
