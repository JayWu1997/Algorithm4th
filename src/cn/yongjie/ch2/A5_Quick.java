package cn.yongjie.ch2;

import cn.yongjie.ch2.base.SortTemplate;

public class A5_Quick implements SortTemplate {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    public void sort(Comparable[] a, final int lo, final int hi){
        if(lo >= hi) return;

        // 切分元素坐标
        int index = partition(a, lo, hi);
        sort(a, lo, index - 1);
        sort(a, index + 1, hi);
    }

    public int partition(Comparable[] a, final int lo, final int hi){
        Comparable c = a[lo];

        int i = lo, j = hi + 1;
        while(true){
            while(less(a[++i], a[lo])) if(i == hi) break;
            while(less(a[lo], a[--j])) if(j == lo) break;

            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) throws Exception{
        A5_Quick quick = new A5_Quick();
        quick.executeSort();
    }
}
