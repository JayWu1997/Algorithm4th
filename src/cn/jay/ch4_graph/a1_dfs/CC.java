package cn.jay.ch4_graph.a1_dfs;

import cn.jay.ch1_base.bag.Bag;
import cn.jay.ch1_base.bag.LinkedBag;
import cn.jay.ch4_graph.a0_graph.Graph;
import cn.jay.ch4_graph.a0_graph.GraphImpl;

/**
 * 使用深度优先搜索求解图中的连通子图
 */
public class CC {
    boolean[] visitedArr;
    int[] groupIdArr;  // 每个顶点所在的连通分量id
    int groupNum;   // 有几组连通分量

    public CC(Graph g){
        visitedArr = new boolean[g.getV()];
        groupIdArr = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if(!visitedArr[i]){
                dfs(g, i);
                groupNum++;
            }
        }
    }

    private void dfs(Graph g, int v){
        visitedArr[v] = true;
        groupIdArr[v] = groupNum;
        for (Integer vertex : g.adj(v)) {
            if(!visitedArr[vertex]){
                dfs(g, vertex);
            }
        }
    }

    public int getGroupNum(){
        return groupNum;
    }

    public boolean isConnected(int v1, int v2){
        return groupIdArr[v1] == groupIdArr[v2];
    }

    /**
     * 返回所有连通分量
     */
    public Bag<Bag<Integer>> getAllGroups(){
        if(groupNum == 0)
            return null;

        Bag<Bag<Integer>> bags = new LinkedBag<>();
        for (int i = 0; i < groupNum; i++)
            bags.add(getGroupById(i));

        return bags;
    }

    /**
     * 根据groupId返回连通分量
     */
    public Bag<Integer> getGroupById(int groupId){
        if(groupId > groupNum)
            return null;
        Bag<Integer> bag = new LinkedBag<>();
        for (int i = 0; i < groupIdArr.length; i++) {
            if(groupIdArr[i] == groupId)
                bag.add(i);
        }
        return bag;
    }

    public static void main(String[] args) {
        Graph g = GraphImpl.initGraphFromFile();
        CC cc = new CC(g);
        Bag<Bag<Integer>> bags = cc.getAllGroups();
        int groupId = 0;
        for (Bag<Integer> bag : bags) {
            System.out.print("group " + groupId++ + " : ");
            for (Integer i : bag) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
