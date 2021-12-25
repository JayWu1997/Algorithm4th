package cn.jay.ch4_graph.a0_base;

import cn.jay.ch1_base.bag.BagByListImpl;
import java.util.Scanner;

/**
 * @ClassName: GraphImpl
 * @Description: 接口Graph的实现类
 * @Author jay
 * @Date 2021/12/25 12:06
 */
public class GraphImpl {
    private int v;  // 顶点数

    private int e;  //  边数

    private BagByListImpl<Integer>[] adj; // 领接表

    public GraphImpl(int v){
        this.v = v;
        adj = (BagByListImpl<Integer>[]) new BagByListImpl[v];
        for(int i = 0; i < v; i++)
            adj[v] = new BagByListImpl<Integer>();
    }

    public GraphImpl(Scanner in){
        v = in.nextInt();
        e = in.nextInt();
        for(int i = 0; i < e; i++){
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
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public void addEdge(int v1, int v2){
        adj[v1].add(v1);
        adj[v1].add(v2);
    }

}
