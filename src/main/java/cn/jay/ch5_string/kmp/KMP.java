package cn.jay.ch5_string.kmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * KMP算法的实现，需要先了解<a href="https://oi-wiki.org/string/automaton/">自动机</a>
 * <p>
 * eg: 对于 pattern = "abcabc"<p>
 * 正确状态列表: state(0){}, state(1){a}, state(2){ab}, state(3){abc}, state(4){abca}, state(5){abcab}, state(6){abcabc}, 且 state(6)为最终状态<p>
 * 每个状态的最长相等前后缀长度: next[0] = 0, next[1] = 0, next[2] = 0, next[3] = 0, next[4] = 1, next[5] = 2, next[6] = 3 <p>
 * 如何计算每个状态对应的最长相等前后缀呢? next[i] = stateTransitionEquation(next[i-1], pattern.charAt(i-1));
 * @author jay
 */
public class KMP {

    private String pattern;

    /**
     * 当前传入的 c 与 "转换到下一个正确状态所需的字符" 不匹配时, 应该跳转到哪个状态继续比较
     */
    private int[] next;

    public KMP(String pattern) {
        this.pattern = pattern;
        next = new int[pattern.length() + 1];
        next[0] = 0;
        computeMaxPrePostFixLength();
    }

    /**
     * <a href="https://oi-wiki.org/string/automaton/#kmp-%E8%87%AA%E5%8A%A8%E6%9C%BA">状态转移方程</a>
     * </p>
     * @param state 当前状态
     * @param c 待对比的字符
     * @return
     */
    private int stateTransitionEquation(int state, char c) {
        // 当前状态转向下一正确状态所需的字符pattern.charAt(state) 是否等于字符 c
        if (pattern.charAt(state) == c) {
            return state + 1;
        } else if (state == 0) {
            return 0;
        } else {
            return stateTransitionEquation(next[state], c);
        }
    }

    private void computeMaxPrePostFixLength() {
        // 状态 0 和 1 的最长相等前后缀长度为 0, 因为其字符串长度小于 2
        next[0] = 0;
        next[1] = 0;
        for (int i = 2; i <= pattern.length(); i++) {
            next[i] = stateTransitionEquation(next[i-1], pattern.charAt(i-1));
        }
        System.out.println("sMPPFL: " + Arrays.toString(next));
    }

    public List<Integer> match(String text) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < text.length()) {
            // 状态转移
            j = stateTransitionEquation(j, text.charAt(i));
            // 若 j = 结束状态
            if (j == pattern.length()) {
                result.add(i - pattern.length() + 1);
                // 继续匹配
                j = next[pattern.length()];
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        String pattern = "ababcabcabab";
        String text = "ababcabcabababcabcabababcabcabababcabcabab";
        KMP kmp = new KMP(pattern);
        System.out.println(kmp.match(text));
    }
}
