package cn.jay.ch4_graph.a2_bfs;

import cn.jay.ch1_base.bag.Bag;
import cn.jay.ch1_base.bag.ListBag;
import cn.jay.ch1_base.queue.Queue;
import cn.jay.ch1_base.queue.ListQueue;
import cn.jay.ch4_graph.a0_graph.Graph;
import cn.jay.ch4_graph.a0_graph.GraphImpl;

import java.util.Arrays;

/**
 * 广度优先搜索算法，能够找出两个点之间的最短路径
 */
public class BFS {
    private final boolean[] markArr;
    private final int sv; // source vertex
    private final int[] lv; // last vertex 从 sv 到数组下标对应顶点的最短路径上的倒数第二个顶点 eg: 0-1-2-3  则lv[3]=2
    private final Queue<Integer> queue = new ListQueue<>();

    public BFS(Graph g, int sv) {
        this.markArr = new boolean[g.getV()];
        this.lv = new int[g.getV()];
        Arrays.fill(this.lv, -1);
        this.sv = sv;
        bfs(g, sv);
    }

    /**
     * 广度优先搜索算法，能够记录路径
     */
    private void bfs(Graph g, int v) {
        markArr[v] = true;
        for (Integer i : g.adj(v)) {
            if (!markArr[i]) {
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
        Bag<Integer> bag = new ListBag<>();
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
        if (graph != null) {
            BFS bfs = new BFS(graph, 2);
            for (Integer i : bfs.getPath(5))
                System.out.print(i + " ");
        }
    }
}
