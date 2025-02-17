package cn.jay.homework;

import cn.jay.ch1_base.stack.LinkedStack;
import cn.jay.ch1_base.stack.Stack;

public class Prb1_3_4 {
    public static void main(String[] args) {
        String in = "}((({})[]))";
        System.out.println(test(in));
    }

    public static boolean test(String in){
        Stack<Character> stack = new LinkedStack<>();
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '(' || in.charAt(i) == '{' || in.charAt(i) == '[')
                stack.push(in.charAt(i));
            else{
                if(stack.isEmpty() || !pair(stack.pop(), in.charAt(i)))
                    return false;
            }
        }
        if(stack.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean pair(char char1, char char2){
        boolean result = false;
        if(char1 == '[' && char2 == ']')
            result = true;
        else if(char1 == '{' && char2 == '}')
            result = true;
        else if(char1 == '(' && char2 == ')')
            result = true;
        return result;
    }
}
