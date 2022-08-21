package cn.jay.ch1_base.stack;

import java.util.Iterator;

/*
    不定容栈，元素始终在栈大小的四分之一到二分之一
    使用数组实现
 */
public class ArrayStackImpl<T> implements Stack<T> {

    private int cap;  //容量
    private T[] array; //元素数组
    private int index = 0; //当前栈顶索引

    ArrayStackImpl(int cap) {
        if (cap < 4) cap = 4;
        this.cap = cap;
        array = (T[]) new Object[cap];
    }

    @Override
    public void push(T item) {

        if (index >= cap / 2) // 检查是否栈溢出
            resize(2 * cap);
        array[index++] = item;
    }

    @Override
    public T pop() {
        // 判断栈是否为空
        if (isEmpty())
            return null;

        T item = array[--index];
        //避免对象游离
        array[index] = null;

        //检查是否需要缩小数组
        if (index > 0 && index < cap / 4)
            resize(cap / 2);
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty())
            return null;
        return array[index - 1];
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int nowIndex = 0; //当前下标

            @Override
            public boolean hasNext() {
                return !(nowIndex == index - 1);
            }

            @Override
            public T next() {
                return array[nowIndex++];
            }
        };
    }

    /**
     * 扩大数组array
     */
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        if (index + 1 >= 0) System.arraycopy(array, 0, newArray, 0, index + 1);
        array = newArray;
        cap = newSize;
    }

    public int getCapacity() {
        return cap;
    }

    public static void main(String[] args) {
        ArrayStackImpl<String> stack = new ArrayStackImpl<>(1);
        for (int i = 0; i < 100; i++) {
            stack.push(i + "");
            System.out.println(i + "-" + stack.getCapacity());
        }

        for (String s : stack)

            for (int i = 100; i > 0; i--) {
                System.out.println(stack.pop() + "-" + stack.getCapacity());
            }
    }
}
