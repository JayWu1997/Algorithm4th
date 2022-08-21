package cn.jay.ch2_sort.sort.base;


import cn.jay.ch2_sort.tools.NumberInitTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public interface Sort {

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


    default void exchange(Comparable[] a, int i, int j){
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

    /**
     * @Method      getParams()
     * @Description 从 numbers.txt 文件获取Integer数组
     */
    default Comparable[] getIntegerParamsFromFile() throws Exception{

        NumberInitTool.initRandomNums();

        // 获得工程目录
        StringBuilder builder = new StringBuilder();
        // 填充文件地址
        builder.append("src")
                .append(File.separator).append("cn")
                .append(File.separator).append("jay")
                .append(File.separator).append("ch2_sort")
                .append(File.separator).append("sort")
                .append(File.separator).append("numbers.txt");

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(builder.toString())));
        String line;
        if((line = in.readLine()) != null) {
            String[] numbers = line.split(" ");
            Integer[] a = new Integer[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                a[i] = Integer.valueOf(numbers[i]);
            }
            return a;
        }
        return null;
    }

    default void executeSort() throws Exception{
        Comparable[] a = getIntegerParamsFromFile();
        long beginTime = System.currentTimeMillis();
        sort(a);
        long endTime = System.currentTimeMillis();
        if(isSorted(a))
            System.out.println("排序成功!");
        else
            System.out.println("排序失败!");
        System.out.println("耗时：" + (endTime-beginTime)+"ms");
    }
}
