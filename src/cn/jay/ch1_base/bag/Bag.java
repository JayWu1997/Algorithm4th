package cn.jay.ch1_base.bag;


public interface Bag<T> extends Iterable<T> {

    /**
     * 向 bag 中添加元素
     * @param t 待添加的元素
     */
    void add(T t);

    /**
     * 返回当前 bag 中的元素个数
     * @return int 元素个数
     */
    int size();

    /**
     * 判断当前 bag 是否为空
     * 使用 {@link #size()}实现
     * @return 为空返回 true, 否则返回 false
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}
