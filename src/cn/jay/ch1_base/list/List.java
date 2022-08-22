package cn.jay.ch1_base.list;

public interface List<E> extends Iterable<E>{

    void add(E e);

    E set(int index, E e);

    boolean remove(E e);

    void remove(int index);

    E get(int index);

    boolean contains(E e);

    int indexOf(E e);

    int size();

    boolean isEmpty();
}
