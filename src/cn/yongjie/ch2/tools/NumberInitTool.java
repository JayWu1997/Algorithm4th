package cn.yongjie.ch2.tools;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

// 该类用于生成随机数至文件 numbers.txt
public class NumberInitTool {
    // 这里修改生成个数
    private static int NUMBER_OF_PARAMS = 100000;
    public static void initNums() throws Exception{
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

    public static void main(String[] args) throws Exception{
        initNums();
    }
}
