package cn.jay.ch1_base.list;

public interface List<E> extends Iterable<E> {

    /**
     * 添加元素
     * @param e
     */
    void add(E e);

    /**
     * 在指定位置添加元素
     * @param e
     */
    void add(int index, E e);

    /**
     * 替换index下标中的元素e，返回该位置原来的元素
     */
    E set(int index, E e);

    boolean remove(E e);

    E remove(int index);

    E get(int index);

    boolean contains(E e);

    int indexOf(E e);

    /**
     * List中最后一个e元素的坐标
     */
    int lastIndexOf(E e);

    int size();

    boolean isEmpty();

    /**
     * 截取子List，下标范围为 [start, end)
     */
    List<E> subList(int start, int end);
}
