package cn.jay.ch2_sort.sort;

import cn.jay.ch2_sort.sort.base.Sort;

public class A3_Shell implements Sort {

    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        // 递增序列
        while(h < N/3) h = h*3 + 1;
        while(h > 0) {
            for (int i = h; i < N; i++) {
                // 从第h位开始插入
                for(int j = i; j>=h && less(a[j], a[j-h]); j-=h){
                    Comparable temp = a[j];
                    a[j] = a[j-h];
                    a[j-h] = temp;
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) throws Exception{
        A3_Shell shell = new A3_Shell();
        shell.executeSort(2, 100000, 1000);
    }
}
