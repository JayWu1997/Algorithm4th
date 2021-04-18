package cn.yongjie.ch2;

import cn.yongjie.ch2.base.SortTemplate;

public class A2_Insertion implements SortTemplate {
    @Override
    public void sort(Comparable[] a) {
        for(int i = 1; i < a.length; i++){
            for(int j = i; j > 0; j--){
                if(less(a[j],a[j-1])){
                    exch(a ,j ,j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        A2_Insertion insertion = new A2_Insertion();
        insertion.executeSort();
    }
}
