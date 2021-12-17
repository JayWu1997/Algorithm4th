package cn.jay.ch2_sort.sort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @ClassName: MaxPQ
 * @Description: 最大优先队列的实现
 * @Author: jay wu
 * @Date: 2021/4/24 0:56
 * @Version: 1.0
 */
public class A7_MaxPQ<Key extends Comparable<Key>>{

    // 优先队列容积
    private int N;

    // 优先队列内元素个数
    private int size = 0;

    // 用于存储元素
    private Key[] a;

    A7_MaxPQ(int N){
        this.N = N + 1;
        a = (Key[]) new Comparable[N+1];
    }

    /**
     * @Method      insert
     * @Description 向优先队列中插入元素
     * @param v     待插入的元素
     */
    public void insert(Key v) {
        if(size < N){
            a[++size] = v;
            swim(size);
        }
        else{
            //TODO 在优先队列已经满了情况下的插入操作
        }
    }

    /**
     * @Method      max
     * @Description 返回最大元素
     * @Return Key  最大元素
     */
    public Key max() {
        return a[1];
    }

    /**
     * @Method      delMax
     * @Description 返回并删除最大元素
     * @Return Key  最大元素
     */
    public Key delMax() {
        Key result = a[1];
        exch(1, size); // 交换头尾元素
        a[size--] = null; // 删除最大元素
        sink(1); // 将交换过来的头元素进行sink操作
        return result;
    }

    /**
     * @Method      less
     * @Description 比较 v 和 m 的大小， v>m 返回true，否则返回false
     * @param v
     * @param w
     * @Return boolean
     * @Exception
     */
    public boolean less(Key v, Key w){
        return v.compareTo(w) < 0;
    }

    /**
     * @Method exch
     * @Description 交换数组 a 中的 第i和第j个元素
     * @param i
     * @param j
     */
    public void exch(int i, int j){

        Key temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * @Method isEmpty
     * @Description 判断当前队列是否为空，为空则返回true，否则返回false
     * @Return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @Method size
     * @Description 返回当前队列元素个数
     * @Return int
     */
    public int size() {
        return size;
    }

    /**
     * @Method swim
     * @Description 上浮第i个元素
     * @param i
     */
    public void swim(int i) {
        for(; i >= 2 && less(a[i/2], a[i]); i/=2){
            exch(i, i/2);
        }
    }

    /**
     * @Method sink
     * @Description 下沉第 i 个元素
     * @param i
     */
    public void sink(int i) {
        while(2*i <= size){
            int k = 2*i;
            if(k < size && less(a[k], a[k+1])) k++;
            if(less(a[k], a[i])) break;
            exch(i, k);
            i = k;
        }
    }

    public boolean isSorted(){
        for(int i = 0; i < N - 2; i++){
            if(less(delMax(), max()))
                return false;
        }
        return true;
    }

    public void show(){
        for(int i = 0; i < N - 1; i++)
            System.out.print("" + delMax() + " ");
    }

    public static Integer[] getParams() throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("numbers.txt")));
        String line;
        if((line = in.readLine()) != null) {
            String[] numbers = line.split(" ");
            Integer[] a = new Integer[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                a[i] = Integer.valueOf(numbers[i]);
            }
            return a;
        }
        return null;
    }

     public static void main(String[] args) throws Exception{
        Integer[] a = getParams();
        A7_MaxPQ<Integer> maxPQ = new A7_MaxPQ<>(a.length);

        long beginTime = System.currentTimeMillis();
        for(int i = 0; i < a.length; i++)
            maxPQ.insert(a[i]);
        maxPQ.isSorted();
        long endTime = System.currentTimeMillis();

        System.out.println("" + (endTime-beginTime) + "ms");


    }
}
