package cn.jay.ch2_sort.sort;

import cn.jay.ch2_sort.sort.base.Sort;

/**
 * @ClassName: A8_HeapSort
 * @Description: 堆排序,从小到大，见算法第四版 2.4.5
 * @Author: jay
 * @Date: 2021/4/24 20:34
 */
public class A8_HeapSort implements Sort {
    @Override
    public void sort(Comparable[] a) {
        // i的初始值为最后一个元素的父节点
        for(int i = (a.length - 1)/2; i >= 0; i--){
            sink(a, i, a.length);
        }

        for(int i = a.length - 1; i > 0; i--){
            Comparable temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            sink(a, 0, i);
        }
    }

    public void sink(Comparable[] a, int i, int N){
        while(2*i + 1 <= N - 1){
            int k = 2*i + 1;
            if(k < N - 1 && less(a[k], a[k+1])) k++;
            if(less(a[k], a[i])) break;
            Comparable temp = a[i];
            a[i] = a[k];
            a[k] = temp;
            i = k;
        }
    }

    public static void main(String[] args) throws Exception{
        A8_HeapSort heapSort = new A8_HeapSort();
        heapSort.executeSort(3, 100000, 1000);
    }
}
