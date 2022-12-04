package cn.jay.ch4_graph.a2_bfs;

import cn.jay.ch1_base.bag.Bag;
import cn.jay.ch1_base.bag.LinkedBag;
import cn.jay.ch1_base.queue.LinkedQueue;
import cn.jay.ch1_base.queue.Queue;
import cn.jay.ch4_graph.a0_graph.Graph;
import main.java.cn.jay.ch4_graph.a0_graph.GraphImpl;

import java.util.Arrays;

/**
 * 广度优先搜索算法，能够找出两个点之间的最短路径
 */
public class BFS {
    private final boolean[] visitedArr;
    private final int sv; // source vertex
    private final int[] lv; // last vertex 从 sv 到数组下标对应顶点的最短路径上的倒数第二个顶点 eg: 0-1-2-3  则lv[3]=2
    private final Queue<Integer> queue = new LinkedQueue<>();

    public BFS(Graph g, int sv) {
        this.visitedArr = new boolean[g.getV()];
        this.lv = new int[g.getV()];
        Arrays.fill(this.lv, -1);
        this.sv = sv;
        bfs(g, sv);
    }

    /**
     * 广度优先搜索算法，能够记录路径
     */
    private void bfs(Graph g, int v) {
        visitedArr[v] = true;
        for (Integer i : g.adj(v)) {
            if (!visitedArr[i]) {
                queue.enqueue(i);
                lv[i] = v;
            }
        }
        if (!queue.isEmpty())
            bfs(g, queue.dequeue());
    }

    /**
     * 获取从 sv 到 v 的路径
     */
    private Iterable<Integer> getPath(int v) {
        Bag<Integer> bag = new LinkedBag<>();
        while (v != this.sv && v != -1) {
            bag.add(v);
            v = lv[v];
        }
        if (v == -1)
            return null;
        bag.add(sv);
        return bag;
    }

    public static void main(String[] args) {
        Graph graph = GraphImpl.initGraphFromFile();

        // source vertex
        final int sv = 2;
        // target vertex
        final int tv = 5;
        if (graph != null) {
            BFS bfs = new BFS(graph, sv);
            Iterable<Integer> integers = bfs.getPath(tv);
            if(integers == null){
                System.out.println("点 " + sv + " 到点 " + tv + " 没有路径");
            }
            else {
                System.out.println("点 " + sv + " 到点 " + tv + " 的最短路径为: ");
                for (Integer i : integers)
                    System.out.print(i + " ");
            }

        }
    }
}
