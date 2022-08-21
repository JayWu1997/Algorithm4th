package homework;

import cn.jay.ch1_base.stack.stackImplByList.ListStack;
import cn.jay.ch1_base.stack.stackImplByList.ListStackImpl;

// 该段代码的行为是：正十进制数转化为二进制数
public class Prb1_3_5 {
    public static void main(String[] args) {
        ListStack<Integer> stack = new ListStackImpl<>();
        int N = 50;
        while(N > 0){
            stack.push(N%2);
            N /= 2;
        }
        for(Integer i : stack)
            System.out.print(""+i);
    }
}
