package cn.jay.ch4_graph.a0_graph;

public interface Graph {
    int getV();

    int getE();

    void addEdge(int v1, int v2);

    Iterable<Integer> adj(int v);
}
