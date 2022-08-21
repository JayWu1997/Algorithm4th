package cn.jay.ch4_graph.a0_graph;

import cn.jay.ch1_base.bag.ListBag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 接口Graph的实现类，无向无权图
 */
public class GraphImpl implements Graph {
    private int v;  // 顶点数

    private int e;  // 边数

    private ListBag<Integer>[] adj; // 领接表

    /**
     * 通过键盘初始化图
     */
    public void initByKeyboard()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入顶点数");
        this.v = in.nextInt();
        System.out.println("请输入边数");
        this.e = in.nextInt();
        adj = (ListBag<Integer>[]) new ListBag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new ListBag<Integer>();
        for (int i = 0; i < e; i++) {
            System.out.println("请输入第" + (i + 1) + "条边的两个顶点");
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            addEdge(v1, v2);
        }
    }

    /**
     * 通过文件初始化，需要绝对路径
     *
     * @param path
     * @throws FileNotFoundException
     */
    public void initByFile(String path) throws FileNotFoundException {
        File file = new File(path);
        InputStream fileInputStream = new FileInputStream(file);
        Scanner in = new Scanner(fileInputStream);
        this.v = in.nextInt();
        this.e = in.nextInt();
        adj = (ListBag<Integer>[]) new ListBag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new ListBag<Integer>();
        for (int i = 0; i < e; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            addEdge(v1, v2);
        }
    }


    /**
     * 返回点v的连通节点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("V: ").append(this.v).append("\n");
        builder.append("E: ").append(this.e).append("\n");
        for (int i = 0; i < adj.length; i++) {
            for (Integer integer : adj[i]) {
                builder.append(i).append(" - ").append(integer).append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * 获取顶点数
     */
    @Override
    public int getV() {
        return this.v;
    }

    /**
     * 获取边数
     */
    @Override
    public int getE() {
        return this.e;
    }

    /**
     * 添加边
     */
    public void addEdge(int v1, int v2) {
        adj[v1].add(v2);
        adj[v2].add(v1);
    }

    /**
     * 返回顶点v的度数
     */
    public int getDegree(int v) {
        return adj[v].size();
    }

    /**
     * 返回度数最大的顶点
     */
    public int maxDegreeV() {
        int max = adj[0].size();
        int index = 0;
        for (int i = 1; i < adj.length; i++) {
            if (max < adj[i].size()) {
                max = adj[i].size();
                index = i;
            }
        }
        return index;
    }

    /**
     * 返回顶点的平均度数
     *
     * @return
     */
    public double avgDegree() {
        int sum = 0;
        for (ListBag<Integer> bag : adj) {
            sum += bag.size();
        }
        return ((double) sum) / this.v;
    }

    /**
     * 返回自环个数
     */
    public int numOfSelfLoops() {
        int num = 0;
        for (int i = 0; i < adj.length; i++) {
            for (Integer integer : adj[i]) {
                if (i == integer)
                    num++;
            }
        }
        return num / 2;
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        // TODO
         test_initFromFile();
        // test_initByKeyboard();
    }

    private static void test_initFromFile() {
        System.out.println(initGraphFromFile());
    }

    public static Graph initGraphFromFile() {
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
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("文件不存在");
            return null;
        }
        return graph;
    }

    private static void test_initByKeyboard() {
        GraphImpl graph = new GraphImpl();
        graph.initByKeyboard();
        System.out.println(graph);
    }

}
