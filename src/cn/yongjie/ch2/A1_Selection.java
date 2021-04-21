package cn.yongjie.ch2;

import cn.yongjie.ch2.base.SortTemplate;

public class A1_Selection implements SortTemplate {

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
