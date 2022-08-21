package cn.jay.ch2_sort.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

// 该类用于生成随机数至文件 numbers.txt
public class NumberInitTool {
    // 这里修改生成个数
    private static int NUMBER_OF_PARAMS = 1000000;

    // 取余参数
    private static int REMAINDER = 1000000;

    public static String getNumsFilePath(){
        // 获得工程目录
        StringBuilder builder = new StringBuilder();
        // 填充文件地址
        builder.append("src")
                .append(File.separator).append("cn")
                .append(File.separator).append("jay")
                .append(File.separator).append("ch2_sort")
                .append(File.separator).append("sort")
                .append(File.separator).append("numbers.txt");
        return builder.toString();
    }

    public static void initNums(int numType, int arrayLength, int modArg) throws Exception {
        NUMBER_OF_PARAMS = arrayLength;
        REMAINDER = modArg;

        switch (numType) {
            case 0 -> initRandomNums();
            case 1 -> initIncreasingNums();
            case 2 -> initDescendingNums();
            case 3 -> initHalf0Half1Nums();
            case 4 -> initHalf0Quarter1Quarter2Nums();
            case 5 -> initHalf0HalfRandomNums();
        }
    }

    private static void initRandomNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(getNumsFilePath()), true);
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < NUMBER_OF_PARAMS; i++){
            sb.append(""+random.nextInt()%REMAINDER+" ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    private static void initIncreasingNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(getNumsFilePath()), true);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < NUMBER_OF_PARAMS; i++){
            sb.append("" + i + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    private static void initDescendingNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(getNumsFilePath()), true);
        StringBuilder sb = new StringBuilder();
        for(int i = NUMBER_OF_PARAMS-1; i >= 0; i--){
            sb.append("" + i + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    private static void initHalf0Half1Nums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(getNumsFilePath()), true);
        StringBuilder sb = new StringBuilder();
        for(int i = NUMBER_OF_PARAMS-1; i >= 0; i--){
            sb.append("" + i%2 + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    private static void initHalf0Quarter1Quarter2Nums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(getNumsFilePath()), true);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < NUMBER_OF_PARAMS; i++){
            if(i%4 == 0 || i%4 == 1)
                sb.append("" + 0 + " ");
            else if(i%4 == 2)
                sb.append("" + 1 + " ");
            else if(i%4 == 3)
                sb.append("" + 2 + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    private static void initHalf0HalfRandomNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(getNumsFilePath()), true);
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < NUMBER_OF_PARAMS; i++){
            if(i%2 == 0)
                sb.append("" + 0 + " ");
            else
                sb.append(""+random.nextInt()%NUMBER_OF_PARAMS+" ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }



    public static void main(String[] args) throws Exception{
        initRandomNums();
//        initIncreasingNums();
//        initDescendingNums();
//        initHalf0Half1Nums();
//        initHalf0Quarter1Quarter2Nums();
//        initHalf0HalfRandomNums();
    }
}
