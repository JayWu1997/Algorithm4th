package cn.jay.ch4_graph.a1_dfs;

import cn.jay.ch1_base.stack.LinkedStack;
import cn.jay.ch1_base.stack.Stack;
import cn.jay.ch4_graph.a0_graph.Graph;
import main.java.cn.jay.ch4_graph.a0_graph.GraphImpl;

/**
 * 使用深度优先算法求解双色问题, 用了stack而不用递归的原因是，当发现图中有环后可以直接退出函数，而使用递归则必须等待递归结束
 */
public class TwoColor {
    private int[] color;

    public TwoColor(Graph g){
        color = new int[g.getV()];
    }

    private boolean dfs(Graph g){
        Stack<Integer[]> stack = new LinkedStack<>();
        Integer[] vertexAndColor;
        color[0] = 1;
        stack.push(new Integer[]{0, 1});
        while(!stack.isEmpty()){
            vertexAndColor = stack.pop();
            color[vertexAndColor[0]] = vertexAndColor[1];
            for (Integer childV : g.adj(vertexAndColor[0])) {
                if(color[childV] == 0)
                    stack.push(new Integer[]{childV, vertexAndColor[1]==1? 2:1});
                else{
                    for (Integer vertex : g.adj(vertexAndColor[0])) {
                        if(color[vertexAndColor[0]] == color[vertex])
                            return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Graph g = GraphImpl.initGraphFromFile();
        TwoColor twoColor = new TwoColor(g);
        System.out.println(twoColor.dfs(g));
    }

}
