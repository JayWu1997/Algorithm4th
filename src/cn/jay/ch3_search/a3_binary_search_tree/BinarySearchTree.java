package cn.jay.ch3_search.a3_binary_search_tree;

import cn.jay.ch1_base.queue.QueueByListImpl;
import cn.jay.ch3_search.a0_base.OrderedSymbolTable;

/**
 * @ClassName: BinarySearchTree, delete(K k)函数非常重要，一定要看懂
 * @Description: 二叉查找树
 * @Author jay
 * @Date 2021/12/18 4:39
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    private Node root;

    @Override
    public K min() {
        if(root == null)
            return null;
        return min(root).key;
    }

    /**
    * @Description: 返回st中最小节点，递归获取左子节点直至某个节点的左子节点为空，并返回这个节点，根据二叉树的特性可知该节点为最小节点
    * @Param: [node]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 15:35
    */
    private Node min(Node node){
        if(node == null) return null;
        if(node.left == null)
            return node;
        else
            return min(node.left);
    }

    @Override
    public K max() {
        if(root == null)
            return null;
        else
            return max(root).key;
    }

    /**
    * @Description: 返回st中最大节点，递归获取右子节点直至某个节点的右子节点为空，并返回这个节点，根据二叉树的特性可知该节点为最大节点
    * @Param: [node]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 15:36
    */
    private Node max(Node node){
        if(node == null) return null;
        if(node.right == null)
            return node;
        else
            return max(node.right);
    }

    @Override
    public K floor(K k) {
        Node targetNode = floor(root, k);
        if (targetNode == null) return null;
        return targetNode.key;
    }

    /**
    * @Description: 返回Key对象小于等于k的最大node。
    *               若k小于根节点node，则结果一定在以左子节点为根节点的树中；
    *               若k等于根节点node，则直接返回根节点node；
    *               若k大于根节点node，当右子树中存在结果，则返回右子树中的结果，否则返回根节点
    * @Param: [node, k]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 15:43
    */
    private Node floor(Node node, K k){
        if (node == null) return null;

        int imp = k.compareTo(node.key);
        if (imp == 0) return node;
        if (imp < 0) return floor(node.left, k);
        Node n = floor(node.right, k);  //  以右子树为根节点，n为右子树中的结果
        if(n != null) return n;
        else return node;
    }

    @Override
    public K ceiling(K k) {
        Node targetNode = ceiling(root, k);
        if(targetNode == null) return null;
        else return targetNode.key;
    }

    /**
    * @Description: 实现方法同floor
    * @Param: [node, k]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 15:50
    */
    private Node ceiling(Node node, K k){
        if (node == null) return null;

        int cmp = k.compareTo(node.key);
        if (cmp == 0) return node;
        else if(cmp > 0) return ceiling(node.right, k);
        Node n = ceiling(node.left, k);
        if(n != null) return n;
        else return node;
    }

    @Override
    public int rank(K k) {
        if (size() == 0) return -1;
        return rank(k, root);
    }

    /**
    * @Description: 返回k在以node为头结点的树中的排名，每有一个node的key小于k则排名加一
    * @Param: [k, node]
    * @return: int
    * @Author: Jay
    * @Date: 2021/12/19 1:42
    */
    private int rank(K k, Node node){
        if(node == null) return 0;
        int cmp = k.compareTo(node.key);
        if(cmp <= 0)
            return rank(k, node.left);
        else
            return 1+rank(k, node.left)+rank(k, node.right);
    }

    /**
    * @Description: 返回指定rank的key值，rank从0开始
    * @Param: [rank]
    * @return: K
    * @Author: Jay
    * @Date: 2021/12/18 15:08
    */
    @Override
    public K select(int rank) {
        if(rank >= size(root) || rank<0) return null;   // rank值大于当前元素个数，或者rank值小于0，返回null
        return select(root, rank).key;
    }

    /**
    * @Description: 返回第rank(从零开始)小的节点。
    *               根据二叉树的特性，我们从根节点开始遍历，若左子树的节点数大于等于rank+1，则目的节点一定在左子树中，继续遍历以左子节点为根节点的树
    *               若以左子节点为根节点的树，其节点数小于rank+1，说明目的节点在以右子节点为根节点的树中，更改传入的rank值继续遍历以右子节点为根节点的树
    *               当rank==0时，说明该根节点即为目的节点
    * @Param: [node, rank]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 15:52
    */
    private Node select(Node node, int rank){
        if(rank == 0)
            return node;
        if(size(node.left) >= rank+1)
            return select(node.left, rank);
        else
            return select(node.right, rank-size(node.left)-1);
    }

    @Override
    public void deleteMin() {
        if (root == null) return;
        root = deleteMin(root);
    }

    /**
    * @Description: 返回完成删除后的根节点，并更新所有节点的size
    * @Param: [node]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 16:09
    */
    private Node deleteMin(Node node){
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void deleteMax() {
        if(root == null) return;
        root = deleteMax(root);
    }

    /**
     * @Description: 返回完成删除后的根节点，并更新所有节点的size
     * @Param: [node]
     * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
     * @Author: Jay
     * @Date: 2021/12/19 16:09
     */
    private Node deleteMax(Node node){
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right);
        return node;
    }

    @Override
    public int size(K lo, K hi) {
        return size(root);
    }

    private int size(Node node){
        if(node == null)
            return 0;
        else
            return node.size;
    }

    @Override
    public void put(K k, V v) {
        root = put(k, v, root);
    }

    private Node put(K k, V v, Node node){
        if(node == null){
            node = new Node(k, v, 1);
            return node;
        }

        int cmp = k.compareTo(node.key);
        if(cmp < 0)
            node.left = put(k, v, node.left);
        else if(cmp > 0)
            node.right = put(k, v, node.right);
        else
            node.val = v;
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public V get(K k) {
        Node targetNode = get(k, root);
        if(targetNode == null) return null; // 判断是否存在该k
        return targetNode.val;
    }

    private Node get(K k, Node node){
        if(node == null) return null;
        int imp = k.compareTo(node.key);
        if (imp == 0) return node;
        else if(imp < 0) return get(k, node.left);
        else return get(k, node.right);
    }


    @Override
    public void delete(K k) {
        Node targetNode = delete(root, k);
        if(targetNode != null)
            root = delete(root, k);
    }

    /**
    * @Description: 真尼玛吊这个递归，头给我看炸了
    * @Param: [node, k]
    * @return: cn.jay.ch3_search.a3_binary_search_tree.BinarySearchTree<K,V>.Node
    * @Author: Jay
    * @Date: 2021/12/19 0:22
    */
    private Node delete(Node node, K k){
        if(node == null) return null;   // 递归出口

        int cmp = k.compareTo(node.key);

        if(cmp < 0) node.left = delete(node.left, k);
        else if(cmp > 0) node.right = delete(node.right, k);
        else {
            if (node.left == null) return delete(node.right, k);
            if (node.right == null) return delete(node.left, k);

            Node temp = node;
            node = min(temp.right);
            node.left = temp.left;
            node.right = deleteMin(temp.right);
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        QueueByListImpl<K> queue = new QueueByListImpl<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
    * @Description: 返回在区间 [lo, hi] 内的所有key值，使用中序遍历
    * @Param: [node, queue, lo, hi]
    * @return: void
    * @Author: Jay
    * @Date: 2021/12/19 16:17
    */
    private void keys(Node node,QueueByListImpl<K> queue, K lo, K hi){
        if(node == null) return;

        int cmpLo = lo.compareTo(node.key);
        int cmpHi = hi.compareTo(node.key);

        if(cmpLo < 0) keys(node.left, queue, lo, hi);
        if(cmpLo <= 0 && cmpHi >= 0) queue.enqueue(node.key);
        if(cmpHi > 0) keys(node.right, queue, lo, hi);
    }

    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }

        @Override
        public String toString(){
            return ("key:" + key.toString() + " value:" + val.toString() + " size:" + size);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

        System.out.println("测试" + "min(): " + bst.min());
        System.out.println();

        System.out.println("测试" + "max(): " + bst.max());
        System.out.println();

        System.out.println("测试" + "rank(0): " + bst.rank(0));
        System.out.println();

        System.out.println("测试put和get");
        for(int i = 0; i < 5; i++){
            bst.put(i*2, i*2+100);
            System.out.println("put(" + i*2 + ", " + (i*2+100) + ")");
        }
        bst.put(8, 100);
        System.out.println("put(" + 8 + ", " + 100 + ")");
        for(int i = 0; i < 5; i++){
            System.out.println("get(" + i + ")" + bst.get(i));
        }
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试" + "min(): " + bst.min());
        System.out.println();

        System.out.println("测试" + "max(): " + bst.max());
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试floor()");
        for(int i = -5; i < 15; i++){
            System.out.println("floor(" + i + "): " + bst.floor(i));
        }
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试ceiling()");
        for(int i = -5; i < 15; i++){
            System.out.println("ceiling(" + i + "): " + bst.ceiling(i));
        }
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试rank()");
        for(int i = -3; i < 12; i++){
            System.out.println("rank(" + i + "): " + bst.rank(i));
        }
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试select()");
        for(int i = -5; i < 10; i++){
            System.out.println("select(" + i + "): " + bst.select(i));
        }
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试minNode(root):" + bst.min(bst.root).key);
        System.out.println("测试minNode(null):" + bst.min(null));
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试maxNode(root):" + bst.max(bst.root).key);
        System.out.println("测试maxNode(null):" + bst.max(null));
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试deleteMin()");
        bst.deleteMin();
        System.out.println("测试minNode(root):" + bst.min(bst.root).key);
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试deleteMax()");
        bst.deleteMax();
        System.out.println("测试maxNode(root):" + bst.max(bst.root).key);
        System.out.println();

        System.out.print("当前元素: ");
        for (Integer i: bst.keys())
            System.out.print(i + ", ");
        System.out.println();
        System.out.println();

        System.out.println("测试delete(2)");
        bst.delete(2);
        System.out.println("测试minNode(root):" + bst.min(bst.root).key);
        System.out.println();

        System.out.println("测试keys()");
        for (Integer i: bst.keys()
             ) {
            System.out.println(i);
        }
        System.out.println();
    }
}
