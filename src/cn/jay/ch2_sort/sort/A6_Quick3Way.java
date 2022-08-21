package cn.jay.ch2_sort.sort;

import cn.jay.ch2_sort.sort.base.Sort;

/**
 * @ClassName:      A6_Quick3Way
 * @Description:    三项切分的快速排序算法，该算法主要用于重复值较多的数组
 * @Author:         jay
 * @Date:           2021/4/22 23:40
 */
public class A6_Quick3Way implements Sort {

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length-1, 5);
    }

    /**
     * @Method      sort
     * @Description 快速排序的三向切分算法，先找基准值并切割为小于基准值部分、等于基准值部分、大于基准值部分，再将大于和小于的两部分递归排序
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

        // 三取样切分
        middleOfFirst3Nums(a, lo);

        // 三向切分
        int lt = lo, gt = hi, i = lo + 1;

        while(i <= gt){
            if(less(a[i], a[lt])){
                exch(a, lt, i);
                i++;
                lt++;
            }
            else if(less(a[lt], a[i])){
                exch(a, gt, i);
                gt--;
            }
            else {
                i++;
            }
        }

        sort(a, lo, lt - 1, 5);
        sort(a, gt + 1, hi, 5);
    }

    /**
     * @Method      middleOfFirst3Nums
     * @Description 三取样切分函数，使用数组起始三个数的中值作为基准值，并将其移至数组头部
     * @param a     待排序数组
     * @param lo    待排序部分起始坐标
     */
    public void middleOfFirst3Nums(Comparable[] a, final int lo){
        new A2_Insertion().sort(a, lo, lo+2);
        exch(a, lo, lo+1);
    }

    public static void main(String[] args) throws Exception{
        new A6_Quick3Way().executeSort();
    }
}
