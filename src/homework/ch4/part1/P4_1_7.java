package homework.ch4.part1;

import cn.jay.ch4_graph.a0_graph.GraphImpl;

/**
 * P4.1.7: 为Graph编写测试用例，用命令行参数指定的输入流中接受一幅图，然后用toString()方法将其打印出来。
 */
public class P4_1_7 {
    public static void main(String[] args) {
        System.out.println(GraphImpl.initFromKeyboard());
    }
}
