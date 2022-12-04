package main.java.cn.jay.ch4_graph.a0_graph;

import cn.jay.ch1_base.bag.Bag;
import cn.jay.ch1_base.bag.LinkedBag;
import cn.jay.ch4_graph.a0_graph.Graph;

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

    private Bag<Integer>[] adj; // 领接表

    public GraphImpl() {
    }

    /**
     * 作业 P4.1.3 的实现，深复制指定Graph对象
     */
    public GraphImpl(Graph g) {
        this.e = g.getE();
        this.v = g.getV();
        this.adj = (LinkedBag<Integer>[]) new LinkedBag[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            Bag<Integer> bag = new LinkedBag<>();
            for (Integer integer : g.adj(i)) {
                bag.add(integer);
            }
            this.adj[i] = bag;
        }
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
    @Override
    public void addEdge(int v1, int v2) {
        if (v1 >= v || v2 >= v)
            return;

        // 不添加重复边，不允许自环，P4.1.5
        if (v1 == v2) return;
        for (Integer child : adj[v1]) {
            if (child == v2)
                return;
        }

        adj[v1].add(v2);
        adj[v2].add(v1);
    }

    /**
     * 判断是否存在指定边，作业P4.1.4
     */
    public boolean hasEdge(int v1, int v2) {
        if (v1 >= this.v || v2 >= this.v)
            return false;

        for (Integer child : adj[v1]) {
            if (child == v2)
                return true;
        }

        return false;
    }

    /**
     * 返回点v的连通节点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
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
        for (Bag<Integer> bag : adj) {
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
     * 通过键盘初始化图
     */
    private void initByKeyboard() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入顶点数");
        this.v = in.nextInt();
        System.out.println("请输入边数");
        this.e = in.nextInt();
        adj = (LinkedBag<Integer>[]) new LinkedBag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedBag<Integer>();
        for (int i = 0; i < e; i++) {
            System.out.println("请输入第" + (i + 1) + "条边的两个顶点");
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            addEdge(v1, v2);
        }
    }

    /**
     * 通过文件初始化，需要绝对路径
     */
    private void initByFile() throws FileNotFoundException {
        // 获得工程目录
        StringBuilder builder = new StringBuilder(new File("").getAbsolutePath());
        // 填充文件地址
        builder.append(File.separator).append("src")
                .append(File.separator).append("cn")
                .append(File.separator).append("jay")
                .append(File.separator).append("ch4_graph")
                .append(File.separator).append("a0_graph")
                .append(File.separator).append("graph.txt");
        InputStream fileInputStream = new FileInputStream(builder.toString());
        Scanner in = new Scanner(fileInputStream);
        this.v = in.nextInt();
        this.e = in.nextInt();
        adj = (LinkedBag<Integer>[]) new LinkedBag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedBag<Integer>();
        for (int i = 0; i < e; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            addEdge(v1, v2);
        }
    }

    /**
     * 通过同目录的graph文件生成graph
     *
     * @return
     */
    public static Graph initGraphFromFile() {

        GraphImpl graph = new GraphImpl();
        try {
            graph.initByFile();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("文件不存在");
            return null;
        }
        return graph;
    }

    /**
     * 通过键盘录入生成Graph
     */
    public static Graph initFromKeyboard() {
        GraphImpl graph = new GraphImpl();
        graph.initByKeyboard();
        return graph;
    }

}
