package cn.jay.ch4_graph.a1_dfs;

import cn.jay.ch1_base.stack.ListStack;
import cn.jay.ch1_base.stack.Stack;
import cn.jay.ch4_graph.a0_graph.Graph;
import cn.jay.ch4_graph.a0_graph.GraphImpl;

/**
 * 检测图中是否有环，默认该图为连通图
 * 若已被标记访问的节点中存在非父节点，则表示可以成环，因为已被标记的节点都是连通的，而子节点可以和非负节点连通，子-父-其他-子，就形成一个环
 */
public class ExistRing {
    private boolean visitedArr[];

    public ExistRing(Graph g){
        this.visitedArr = new boolean[g.getV()];
    }

    public boolean hasRing(Graph g){
        Stack<Integer[]> stack = new ListStack<>();
        visitedArr[0] = true;
        for (Integer v : g.adj(0))
            stack.push(new Integer[]{v, 0});
        while(!stack.isEmpty()){
            // vAndFV : vertexAndFatherVertex
            Integer[] vAndFV = stack.pop();
            visitedArr[vAndFV[0]] = true;
            for (Integer childV : g.adj(vAndFV[0])) {
                if(!visitedArr[childV])
                    stack.push(new Integer[]{childV, vAndFV[0]});
                // 关键，若已被标记访问的节点中存在非父节点，则表示可以成环，因为已被标记的节点都是连通的，而子节点可以和非负节点连通，子-父-其他-子，就形成一个环
                else if(vAndFV[1] != childV)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = GraphImpl.initGraphFromFile();
        ExistRing existRing = new ExistRing(g);
        System.out.println(existRing.hasRing(g));
    }

}
