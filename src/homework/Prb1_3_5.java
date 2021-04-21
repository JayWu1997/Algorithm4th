package homework;

import cn.yongjie.ch1.section3.stack.stackImplByList.StackByList;
import cn.yongjie.ch1.section3.stack.stackImplByList.StackByListImpl;

// 该段代码的行为是：正十进制数转化为二进制数
public class Prb1_3_5 {
    public static void main(String[] args) {
        StackByList<Integer> stack = new StackByListImpl<>();
        int N = 50;
        while(N > 0){
            stack.push(N%2);
            N /= 2;
        }
        for(Integer i : stack)
            System.out.print(""+i);
    }
}