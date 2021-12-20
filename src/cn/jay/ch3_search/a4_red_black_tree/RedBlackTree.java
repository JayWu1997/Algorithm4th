package cn.jay.ch3_search.a4_red_black_tree;

import cn.jay.ch3_search.a0_base.OrderedSymbolTable;

/**
 * @ClassName: RedBlackTree
 * @Description:    红黑树
 *                  疑惑为啥必须要左右旋转，推了半天发现不用旋转也行。
 *                  只是需要判断一个节点的父链接、左链接、右链接三个链接中是否有两个是红链接。
 *                  如果这个节点的三个链接中有两个是红链接话，那么对应的三个节点就可以组成2-3树中的一个4-节点，以后再按照2-3树那样上升就行了。
 * @Author jay
 * @Date 2021/12/20 1:13
 */
public class RedBlackTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public K floor(K k) {
        return null;
    }

    @Override
    public K ceiling(K k) {
        return null;
    }

    @Override
    public int rank(K k) {
        return 0;
    }

    @Override
    public K select(int rank) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(K lo, K hi) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return null;
    }

    @Override
    public void put(K k, V v) {
        root = put(root, k, v);
    }

    private Node put(Node node, K k, V v){
        if(node == null)
            return new Node(k, v, 1, RED);

        // 比较k和node.k的大小，并插入
        int cmp = k.compareTo(node.k);
        if(cmp == 0){   // 相等则更换node.v的值，不用重新计算size和左右旋及反转颜色的操作
            node.v = v;
            return node;
        }
        else if(cmp < 0)
            node.left = put(node.left, k, v);
        else
            node.right = put(node.right, k, v);

        // 判断是否左右旋和翻转
        if(!isRed(node.left) && isRed(node.right))
            node = rotateLeft(node);
        if(isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if(isRed(node.left) && isRed(node.right))
            flipColor(node);

        // 计算size
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public V get(K k) {
        Node targetNode = get(root, k);
        if(targetNode == null) return null;
        return targetNode.v;
    }

    private Node get(Node node, K k){
        // 判断node是否为空
        if(node == null) return null;

        // 比较k和node.k
        int cmp = k.compareTo(node.k);
        if(cmp < 0) return get(node.left, k);
        else if (cmp > 0) return get(node.right, k);
        return node;
    }

    @Override
    public void delete(K k) {

    }

    @Override
    public int size() {
        return 0;
    }

    private int size(Node node){
        if(node == null) return 0;
        return node.size;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    private Node rotateLeft(Node node){
        // 旋转
        Node rightChildNode = node.right;
        node.right = rightChildNode.left;
        rightChildNode.left = node;

        // 变色
        rightChildNode.color = node.color;  // 注意这里
        node.color = RED;

        // 更节点的size
        rightChildNode.size = node.size;
        node.size = 1 + size(node.left) + size(node.right);

        return rightChildNode;
    }

    private Node rotateRight(Node node){
        // 旋转
        Node leftChildNode = node.left;
        node.left = leftChildNode.right;
        leftChildNode.right = node;

        // 变色
        leftChildNode.color = node.color;
        node.color = RED;

        // 更改节点的size
        leftChildNode.size = node.size;
        node.size = 1 + size(node.left) + size(node.right);

        return leftChildNode;
    }

    private void flipColor(Node node){
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private boolean isRed(Node node){
        if(node == null) return false;
        return node.color == RED;
    }

    private class Node{
        K k;
        V v;
        int size;
        Node left;
        Node right;
        boolean color;

        public Node(K k, V v, int size, boolean color) {
            this.k = k;
            this.v = v;
            this.size = size;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();

        System.out.println("**** 测试put()，插入1-10 ****");
        for(int i = 1; i < 11; i++)
            redBlackTree.put(i,i);
        System.out.println();

        System.out.println("**** 测试get() ****");
        for(int i = -5; i < 15; i++)
            System.out.println("get(" + i + ") : " + redBlackTree.get(i));
        System.out.println();
    }
}
