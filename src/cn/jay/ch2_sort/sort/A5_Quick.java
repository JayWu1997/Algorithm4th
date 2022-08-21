package cn.jay.ch2_sort.sort;

import cn.jay.ch2_sort.sort.base.Sort;

public class A5_Quick implements Sort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length-1, 5);
    }

    /**
     * @Method      sort
     * @Description 快速排序的排序算法，先找基准值并切割，再将左右两块递归排序
     * @param a     需要排序的数组
     * @param lo    数组排序的起点坐标（包含）
     * @param hi    数组排序的终点坐标（包含）
     * @param M     在规模小于等于M时使用插入排序优化，一般 5 <= M <= 15
     */
    public void sort(Comparable[] a, final int lo, final int hi, final int M){

        // if(lo >= hi) return;
        // 改进的快速排序（相对上一段注释的代码），对小规模部分使用插入排序(规模大小由函数参数M决定，一般在5~15)
        if(lo >= hi - M) {
            new A2_Insertion().sort(a, lo, hi);
            return;
        }

        // 切分元素坐标
        int index = partition(a, lo, hi);
        sort(a, lo, index - 1, 5);
        sort(a, index + 1, hi, 5);
    }


    /**
     * @Method partition
     * @Description 快速排序的分割函数，将第一个数字作为基准值（三取样切分则是将前三个数的中值作为基准值）插入对应的位置，
     *              将比基准值大的数字移到基准值右侧，将比基准值小的数字移到其左侧，并返回插入后基准值的坐标
     * @param a     待分割的数组
     * @param lo    待分割数组部分的起始坐标（包括）
     * @param hi    待分割数组部分的终点坐标（包括）
     * @Return int  基准值在数组 a 中的坐标
     */
    public int partition(Comparable[] a, final int lo, final int hi){

        // 三取样切分，下面一行注释掉剩下的就是普通的切分算法
        middleOfFirst3Nums(a, lo);

        int i = lo, j = hi + 1;
        while(true){
            while(less(a[++i], a[lo])) if(i == hi) break;
            while(less(a[lo], a[--j])) if(j == lo) break;

            if(i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    /**
     * @Method      middleOfFirst3Nums
     * @Description 三取样切分函数，使用数组起始三个数的中值作为基准值，并将其移至数组头部
     * @param a     待排序数组
     * @param lo    待排序部分起始坐标
     */
    public void middleOfFirst3Nums(Comparable[] a, final int lo){
        new A2_Insertion().sort(a, lo, lo+2);
        exchange(a, lo, lo+1);
    }

    public static void main(String[] args) throws Exception{
        A5_Quick quick = new A5_Quick();
        quick.executeSort();
    }
}
