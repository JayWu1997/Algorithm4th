package cn.jay.ch2.tools;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

// 该类用于生成随机数至文件 numbers.txt
public class NumberInitTool {
    // 这里修改生成个数
    private static int NUMBER_OF_PARAMS = 10000000;

    public static void initRandomNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("numbers.txt"), true);
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < NUMBER_OF_PARAMS; i++){
            sb.append(""+random.nextInt()%NUMBER_OF_PARAMS+" ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    public static void initIncreasingNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("numbers.txt"), true);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < NUMBER_OF_PARAMS; i++){
            sb.append("" + i + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    public static void initDescendingNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("numbers.txt"), true);
        StringBuilder sb = new StringBuilder();
        for(int i = NUMBER_OF_PARAMS-1; i >= 0; i--){
            sb.append("" + i + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    public static void initHalf0Half1Nums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("numbers.txt"), true);
        StringBuilder sb = new StringBuilder();
        for(int i = NUMBER_OF_PARAMS-1; i >= 0; i--){
            sb.append("" + i%2 + " ");
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    public static void initHalf0Quarter1Quarter2Nums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("numbers.txt"), true);
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

    public static void initHalf0HalfRandomNums() throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("numbers.txt"), true);
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
