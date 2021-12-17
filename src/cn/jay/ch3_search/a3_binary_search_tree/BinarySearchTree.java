package cn.jay.ch3_search.a3_binary_search_tree;

import cn.jay.ch3_search.a0_base.OrderedSymbolTable;

/**
 * @ClassName: BinarySearchTree
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
        return min(root);
    }

    private K min(Node node){
        if(node.left == null)
            return node.key;
        else
            return min(node.left);
    }

    @Override
    public K max() {
        if(root == null)
            return null;
        else
            return max(root);
    }

    private K max(Node node){
        if(node.right == null)
            return node.key;
        else
            return max(node.right);
    }

    @Override
    public K floor(K k) {
        if(root == null)
            return null;
        K result = null;
        Node node = root;
        while(true){
            if(node == null) return result;
            int cmp = k.compareTo(node.key);
            if(cmp < 0){
                node = node.left;
                continue;
            }
            else if(cmp > 0){
                result = node.key;
                node = node.right;
                continue;
            }
            else
                return node.key;
        }
    }

    @Override
    public K ceiling(K k) {
        if(root == null)
            return null;
        K result = null;
        Node node = root;
        while(true){
            if(node == null) return result;
            int cmp = k.compareTo(node.key);
            if(cmp < 0){
                result = node.key;
                node = node.left;
                continue;
            }
            else if(cmp > 0){
                node = node.right;
                continue;
            }
            else
                return node.key;
        }
    }

    @Override
    public int rank(K k) {
        return rank(k, root);
    }

    private int rank(K k, Node node){
        if(node == null) return 0;
        int cmp = k.compareTo(node.key);
        if(cmp <= 0)
            return rank(k, node.left);
        else
            return 1+rank(k, node.left)+rank(k, node.right);
    }

    @Override
    public K select(int rank) {
        if(rank > size()) return null;
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
        return size(root);
    }

    private int size(Node node){
        if(node == null)
            return 0;
        else
            return node.size;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return null;
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
        return get(k, root);
    }

    private V get(K k, Node node){
        if(node == null) return null;
        int cmp = k.compareTo(node.key);
        if(cmp < 0)
            return get(k, node.left);
        else if(cmp > 0)
            return get(k, node.right);
        else
            return node.val;
    }

    @Override
    public void delete(K k) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        return null;
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

        System.out.println("测试" + "rank(): " + bst.rank(0));
        System.out.println();

        System.out.println("测试put和get");
        for(int i = 0; i < 5; i++){
            bst.put(i, i+100);
            System.out.println("put(" + i + ", " + (i+100) + ")");
        }
        bst.put(4, 100);
        System.out.println("put(" + 4 + ", " + 100 + ")");
        for(int i = 0; i < 5; i++){
            System.out.println("get(" + i + ")" + bst.get(i));
        }
        System.out.println();

        System.out.println("测试" + "min(): " + bst.min());
        System.out.println();

        System.out.println("测试" + "max(): " + bst.max());
        System.out.println();

        System.out.println("测试floor()");
        for(int i = -3; i < 8; i++){
            System.out.println("floor(" + i + "): " + bst.floor(i));
        }

        System.out.println("测试ceiling()");
        for(int i = -3; i < 8; i++){
            System.out.println("ceiling(" + i + "): " + bst.ceiling(i));
        }
        System.out.println();

        System.out.println("测试rank()");
        for(int i = -3; i < 8; i++){
            System.out.println("rank(" + i + "): " + bst.rank(i));
        }
    }
}
