package cn.jay.homework.ch4.part1.p4_1_7;

import main.java.cn.jay.ch4_graph.a0_graph.GraphImpl;

/**
 * P4.1.7: 为Graph编写测试用例，用命令行参数指定的输入流中接受一幅图，然后用toString()方法将其打印出来。
 */
public class GraphTest {
    public static void main(String[] args) {
        System.out.println(GraphImpl.initFromKeyboard());
    }
}
