package cn.jay.ch2.sort;

import cn.jay.ch2.sort.base.SortTemplate;

/**
 * @ClassName: A8_HeapSort
 * @Description: 堆排序,从小到大，见算法第四版 2.4.5
 * @Author: jay wu
 * @Date: 2021/4/24 20:34
 * @Version: 1.0
 */
public class A8_HeapSort implements SortTemplate {
    @Override
    public void sort(Comparable[] a) {
        // i的初始值为最后一个元素的父节点
        for(int i = (a.length - 1)/2; i >= 0; i--){
            sink(a, i, a.length);
        }

        for(int i = a.length - 1; i > 0; i--){
            exch(a, 0, i);
            sink(a, 0, i);
        }
    }

    public void sink(Comparable[] a, int i, int N){
        while(2*i + 1 <= N - 1){
            int k = 2*i + 1;
            if(k < N - 1 && less(a[k], a[k+1])) k++;
            if(less(a[k], a[i])) break;
            exch(a, i, k);
            i = k;
        }
    }

    public static void main(String[] args) throws Exception{
        A8_HeapSort heapSort = new A8_HeapSort();
        heapSort.executeSort();
    }
}
