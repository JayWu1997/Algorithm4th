package cn.yongjie.ch2;

import cn.yongjie.ch2.base.SortTemplate;

public class A2_Insertion implements SortTemplate {
    @Override
    public void sort(Comparable[] a) {
        for(int i = 1; i < a.length; i++){
            // 注意这里的循环条件，并不一定会从j一直比到0
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
                exch(a ,j ,j-1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        A2_Insertion insertion = new A2_Insertion();
        insertion.executeSort();
    }
}
