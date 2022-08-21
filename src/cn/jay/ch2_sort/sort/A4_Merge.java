package cn.jay.ch2_sort.sort;

import cn.jay.ch2_sort.sort.base.Sort;

/**
 * @ClassName:      A4_Merge
 * @Description:    归并排序
 * @Author:         jay
 * @Date:           2021/4/22 21:48
 */
public class A4_Merge implements Sort {

    public static Comparable[] aTemp;

    @Override
    public void sort(Comparable[] a) {

    }

    // 自顶向下的归并排序
    public void sortFromTopToBottom(Comparable[] a, int lo, int hi){
        if(lo >= hi) return;
        int leftEnd = lo + (hi-lo)/2;
        sortFromTopToBottom(a, lo, leftEnd);
        // 右半部分的函数参数lo要加1，思考一下为什么。
        sortFromTopToBottom(a, leftEnd + 1, hi);
        merge(a, lo, leftEnd, hi);
    }

    // 自底向上的归并排序
    public void sortFromBottomToTop(Comparable[] a){
        for(int subArraySize = 1; subArraySize < a.length; subArraySize *= 2){
            for(int leftStart = 0; leftStart < a.length - subArraySize; leftStart += 2*subArraySize)
                merge(a, leftStart, leftStart + subArraySize - 1, Math.min(leftStart+2*subArraySize-1, a.length-1));
        }
    }

    public void merge(Comparable[] a, final int lo, final int leftEnd, final int hi){
        // 如果左边最大值都小于等于右边最小值，那就不用归并
        if(lessOrEqual(a[leftEnd], a[leftEnd+1]))
            return;
        // 这两个坐标变量的对象为aTemp数组
        int leftStart = lo, rightStart = leftEnd + 1;
        // 复制待归并元素
        for(int i = lo; i <= hi; i++)
            aTemp[i] = a[i];
        // 开始归并元素
        for(int i = lo; i <= hi; i++){
            // 先判断左边是否排完，再判断右边是否排完，接着比较左边未排序的第一个数和右边未排序的第一个数大小并排序
            if(leftStart > leftEnd)
                a[i] = aTemp[rightStart++];
            else if(rightStart > hi)
                a[i] = aTemp[leftStart++];
            else if(less(aTemp[leftStart], aTemp[rightStart]))
                a[i] = aTemp[leftStart++];
            else
                a[i] = aTemp[rightStart++];
        }
    }

    @Override
    public void executeSort() throws Exception {
        Comparable[] a = getIntegerParamsFromFile();
        aTemp = new Comparable[a.length];

        long beginTime = System.currentTimeMillis();
        sortFromTopToBottom(a, 0, a.length-1);
        long endTime = System.currentTimeMillis();
        if(isSorted(a))
            System.out.println("自顶向下的归并排序成功！");
        else
            System.out.println("自顶向下的归并排序失败!");
        System.out.println("耗时：" + (endTime-beginTime)+"ms");
        System.out.println();

        a = getIntegerParamsFromFile();
        aTemp = new Comparable[a.length];
        beginTime = System.currentTimeMillis();
        sortFromBottomToTop(a);
        endTime = System.currentTimeMillis();
        if(isSorted(a))
            System.out.println("自底向上的归并排序成功!");
        else
            System.out.println("自底向上的归并排序失败!");
        System.out.println("耗时：" + (endTime-beginTime)+"ms");
    }

    public static void main(String[] args) throws Exception{
        A4_Merge merge = new A4_Merge();
        merge.executeSort();
    }
}
