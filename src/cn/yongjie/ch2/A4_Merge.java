package cn.yongjie.ch2;

import cn.yongjie.ch2.base.SortTemplate;

public class A4_Merge implements SortTemplate {

    @Override
    public void sort(Comparable[] a) {

    }

    public void sort(Comparable[] a, int lo, int hi){
        if(lo >= hi) return;
        int leftEnd = lo + (hi-lo)/2;
        sort(a, lo, leftEnd);
        // 右半部分的函数参数lo要加1，思考一下为什么。
        sort(a, leftEnd + 1, hi);
        merge(a, lo, leftEnd, hi);
    }

    public void merge(Comparable[] a, final int lo, final int leftEndOfA, final int hi){
        Comparable[] aTemp = new Comparable[hi-lo+1];
        // 这两个坐标变量的对象为aTemp数组
        int leftStart = 0;
        // 为什么是+1？
        int rightStart = leftEndOfA + 1 - lo;
        // 复制待归并元素
        for(int i = lo; i <= hi; i++)
            aTemp[i-lo] = a[i];
        // 开始归并元素
        for(int i = lo; i <= hi; i++){
            // 先判断左边是否排完，再判断右边是否排完，接着比较左边未排序的第一个数和右边未排序的第一个数大小并排序
            if(leftStart + lo > leftEndOfA)
                a[i] = aTemp[rightStart++];
            else if(rightStart + lo > hi)
                a[i] = aTemp[leftStart++];
            else if(less(aTemp[leftStart], aTemp[rightStart]))
                a[i] = aTemp[leftStart++];
            else
                a[i] = aTemp[rightStart++];
        }
    }

    @Override
    public void executeSort() throws Exception {
        Comparable[] a = getParams();
        System.out.println("Before sort:");
        show(a);
        System.out.println("After sort:");
        long beginTime = System.currentTimeMillis();
        sort(a, 0, a.length-1);
        long endTime = System.currentTimeMillis();
        show(a);
        System.out.println("耗时：" + (endTime-beginTime)+"ms");
    }

    public static void main(String[] args) throws Exception{
        A4_Merge merge = new A4_Merge();
        merge.executeSort();
    }
}
