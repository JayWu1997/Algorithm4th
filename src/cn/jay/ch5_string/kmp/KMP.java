package cn.jay.ch5_string.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jay
 * @date 2022/12/03 12:51:08
 * @desc KMP算法的实现，关键在 next 数组的计算，指定位置不匹配时应该回退到哪个位置再进行比较，这里只需找到和后缀相同的前缀
 * 最重要的是 next数组的计算，next数组表示前缀与后缀最长相同子串的长度，表示当 pattern第 index 位字符不能匹配指定String时，跳转到 next[index]处重新比较
 * 比如 pattern 为 abcabdef，其 next 数组为 [0, 0, 0, 1, 2, 0, 0, 0]
 */
public class KMP {

    /**
     * 计算 next 数组
     *
     * @param pat 即 pattern
     * @return 计算好的 next数组
     */
    private int[] calNext(String pat) {
        int[] next = new int[pat.length()];
        next[0] = 0;

        // 动态规划的思想，j表示当前要比较的字符的下标
        for (int i = 1, j = 0; i < pat.length(); i++){
            while(j > 0 && pat.charAt(j) != pat.charAt(i)){
                j = next[j-1];
            }

            if(pat.charAt(j) == pat.charAt(i)){
                j++;
            }

            next[i] = j;
        }
        return next;
    }

    private List<Integer> search(String pattern, String str){
        List<Integer> list = new ArrayList<Integer>();
        int[] next = calNext(pattern);
        for (int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && pattern.charAt(j) != str.charAt(i)){
                j = next[j];
            }
            if(pattern.charAt(j) == str.charAt(i)){
                j++;
            }

            if(j == pattern.length()){
                list.add(i-j+1);
                j = 0;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        System.out.println(kmp.search("ababc", "haabcababcabcdfjkal"));

    }

}
