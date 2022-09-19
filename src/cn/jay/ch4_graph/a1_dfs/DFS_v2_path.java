package cn.jay.ch4_graph.a1_dfs;

import cn.jay.ch1_base.bag.Bag;
import cn.jay.ch1_base.bag.LinkedBag;
import cn.jay.ch4_graph.a0_graph.Graph;
import cn.jay.ch4_graph.a0_graph.GraphImpl;

import java.util.Arrays;

/**
 * 改进的DFS算法，能够保存路径，但不能保证是最短路径
 */
public class DFS_v2_path {
    private boolean[] visitedArr;
    private int[] lv; // last vertex 从源点到数组下标对应顶点的路径上的倒数第二个顶点 eg: 0-1-2-3  则lv[3]=2
    private final int sv; // source sv 起点

    public DFS_v2_path(Graph g, int sv){ // source vertex
        visitedArr = new boolean[g.getV()];
        lv = new int[g.getV()];
        Arrays.fill(lv, -1);
        this.sv = sv;
        dfs(g, sv);
    }

    /**
     * 深度优先搜索算法，能够标记路径
     */
    private void dfs(Graph g, int v){
        visitedArr[v] = true;
        for (Integer vertex : g.adj(v)) {
            if(!visitedArr[vertex]){
                lv[vertex] = v;
                dfs(g, vertex);
            }
        }
    }

    /**
     * 获取从 v 到 sv 的路径
     */
    public Bag<Integer> getPath(int v){
        Bag<Integer> bag = new LinkedBag<>();
        while(v != -1 && v != sv){
            bag.add(v);
            v = lv[v];
        }
        if(v == -1)
            return null;
        else {
            bag.add(sv);
            return bag;
        }
    }

    public static void main(String[] args) {
        test();
    }

    private static void test(){
        Graph graph = GraphImpl.initGraphFromFile();
        if(graph != null){
            DFS_v2_path dfsV2Path = new DFS_v2_path(graph, 2);
            Bag<Integer> bag = dfsV2Path.getPath(5);

            if(bag == null){
                System.out.println("未连通");
                return;
            }

            System.out.println("路线为： ");
            for (Integer v : bag)
                System.out.print(v + " ");
        }
    }
}
