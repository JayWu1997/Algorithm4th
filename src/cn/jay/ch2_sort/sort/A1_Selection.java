package cn.jay.ch2_sort.sort;

import cn.jay.ch2_sort.sort.base.Sort;

public class A1_Selection implements Sort {

    public void sort(Comparable[] a) {
        for(int i = 0; i < a.length-1; i++){
            int min = i;
            for(int j = i+1; j < a.length; j++){
                if(less(a[j],a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) throws Exception{
        A1_Selection selection = new A1_Selection();
        selection.executeSort();
    }
}
