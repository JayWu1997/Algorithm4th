package cn.jay.ch1.section3.stack.unfixedcapacitystackwitharray;

import java.util.Iterator;

/*
    不定容栈，元素始终在栈大小的四分之一到二分之一
    使用数组实现
 */
public class UnfixedCapacityStackWithArrayImpl<T> implements UnfixedCapacityStackWithArray<T> {

    private int cap;  //容量
    private T[] array; //元素数组
    private int index = 0; //当前栈顶索引

    UnfixedCapacityStackWithArrayImpl(int cap){
        if(cap < 4) cap=4;
        this.cap = cap;
        array = (T[]) new Object[cap];
    }

    @Override
    public void push(T item) {

        if(index >= cap/2) // 检查是否栈溢出
            resize(2*cap);
        array[index++] = item;
    }

    @Override
    public T pop() {
        //检查是否需要缩小数组
        T item = array[--index];
        array[index] = null; //避免对象游离
        if(index>0 && index < cap/4)
            resize(cap/2);
        return item;
    }

    @Override
    public int getSize() {
        return index;
    }

    @Override
    public void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        if (index + 1 >= 0) System.arraycopy(array, 0, newArray, 0, index + 1);
        array = newArray;
        cap = newSize;
    }

    @Override
    public boolean isEmpty() {
        return index==0;
    }

    @Override
    public int getCapacity() {
        return cap;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int nowIndex = 0; //当前下标
            @Override
            public boolean hasNext() {
                return !(nowIndex==index-1);
            }

            @Override
            public T next() {
                return array[nowIndex++];
            }
        };
    }

    public static void main(String[] args) {
        UnfixedCapacityStackWithArray<String> stack = new UnfixedCapacityStackWithArrayImpl<>(1);
        for (int i=0; i<100; i++){
            stack.push(i+"");
            System.out.println(i+"-"+stack.getCapacity());
        }

        for(String s : stack)

        for(int i=100; i>0; i--){
            System.out.println(stack.pop()+"-"+stack.getCapacity());
        }
    }
}
