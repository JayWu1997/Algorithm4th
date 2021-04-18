package cn.yongjie.ch2.base;


public interface SortTemplate {
    void sort(Comparable[] a);

    default boolean less(Comparable v, Comparable w){
        if(v.compareTo(w) < 0) return true;
        else return false;
    }

    default boolean lessOrEqual(Comparable v, Comparable w){
        return v.compareTo(w) < 0 || v.compareTo(w) == 0;
    }

    default boolean greaterOrEqual(Comparable v, Comparable w){
        return !less(v, w);
    }


    default void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    default void show(Comparable[] a){
        for(Comparable c : a)
            System.out.print(c + " ");
        System.out.println();
    }

    default boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    default void executeSort(){
        Integer[] a = new Integer[10];
        a[0] = 5;
        a[1] = 2;
        a[2] = 7;
        a[3] = 8;
        a[4] = 0;
        a[5] = 1;
        a[6] = 3;
        a[7] = 9;
        a[8] = 4;
        a[9] = 2;
        System.out.println("Before sort:");
        show(a);
        System.out.println("After sort:");
        sort(a);
        show(a);
    }
}
