package cn.jay.ch4_graph.a1_search;

import cn.jay.ch4_graph.a0_graph.Graph;
import cn.jay.ch4_graph.a0_graph.GraphImpl;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 找到图中与指定顶点 sv 连通的其他顶点，使用DFS算法，没有保存路径
 */
public class DFS_v1 {
    private int cvn; // connected vertex' num 连通的顶点数
    private boolean[] markArr; // 是否连通和是否已经访问标记

    public DFS_v1(Graph g, int sv) {
        this.markArr = new boolean[g.getV()];
        dfs(g, sv);
    }

    /**
     * 判断指定顶点与 sv 是否连通
     */
    public boolean marked(int v) {
        return this.markArr[v];
    }

    private void dfs(Graph g, int v) {
        this.markArr[v] = true;
        this.cvn++;
        for (Integer vertex : g.adj(v))
            if (!markArr[vertex])
                dfs(g, vertex);
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        // 获得工程目录
        StringBuilder builder = new StringBuilder(new File("").getAbsolutePath());
        // 填充文件地址
        builder.append(File.separator).append("src")
                .append(File.separator).append("cn")
                .append(File.separator).append("jay")
                .append(File.separator).append("ch4_graph")
                .append(File.separator).append("a0_graph")
                .append(File.separator).append("graph.txt");
        GraphImpl graph = new GraphImpl();
        try {
            graph.initByFile(builder.toString());
            System.out.println(graph);

            // TODO
            DFS_v1 dfsV1 = new DFS_v1(graph, 9);
            System.out.println("连通的点：");
            for (int i = 0; i < dfsV1.markArr.length; i++) {
                if (dfsV1.marked(i))
                    System.out.print(i + " ");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("文件不存在");
        }
    }
}
