package cn.jay.ch4_graph.a0_base;

public interface Graph {
    public int getV();

    public int getE();

    public void addEdge(int v1, int v2);

    Iterable<Integer> adj(int v);

    String toString();
}
