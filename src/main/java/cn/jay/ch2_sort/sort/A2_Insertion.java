package cn.jay.ch2_sort.sort;


import cn.jay.ch2_sort.sort.base.Sort;

public class A2_Insertion implements Sort {

    public void sort(Comparable[] a, final int lo, final int hi){
        for(int i = lo + 1; i < hi + 1; i++){
            // 注意这里的循环条件，并不一定会从j一直比到0
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }

    public void sort(Comparable[] a) {
        for(int i = 1; i < a.length; i++){
            // 注意这里的循环条件，并不一定会从j一直比到0
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        A2_Insertion insertion = new A2_Insertion();
        insertion.executeSort(2, 100000, 1000);
    }
}
