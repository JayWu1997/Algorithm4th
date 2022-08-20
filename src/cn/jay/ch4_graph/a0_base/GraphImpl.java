package cn.jay.ch4_graph.a0_base;

import cn.jay.ch1_base.bag.ListBagImpl;

import java.util.Scanner;

/**
 * @ClassName: GraphImpl
 * @Description: 接口Graph的实现类，无向无权图
 * @Author jay
 * @Date 2021/12/25 12:06
 */
public class GraphImpl {
    private int v;  // 顶点数

    private int e;  //  边数

    private ListBagImpl<Integer>[] adj; // 领接表

    public void initGraphByKeyboardInput(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入顶点数");
        this.v = in.nextInt();
        System.out.println("请输入边数");
        this.e = in.nextInt();
        adj = (ListBagImpl<Integer>[]) new ListBagImpl[v];
        for (int i = 0; i < v; i++)
            adj[v] = new ListBagImpl<Integer>();
        for (int i = 0; i < e; i++) {
            System.out.println("请输入第" + (i + 1) + "条边的两个顶点");
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            addEdge(v1, v2);
        }
    }

    /**
     * @Description: 返回点v的连通节点
     * @Param: [v]
     * @return: java.lang.Iterable<java.lang.Integer>
     * @Author: Jay
     * @Date: 2021/12/25 13:15
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public void addEdge(int v1, int v2) {
        adj[v1].add(v1);
        adj[v1].add(v2);
    }

}
