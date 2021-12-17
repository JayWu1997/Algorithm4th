package cn.jay.ch3_search.a2_bsoast;

import cn.jay.ch3_search.a0_base.OrderedSymbolTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName: BinarySearchOrderedSymbolTable
 * @Description: 有序符号表和数组实现的二分查找表，优点是查找快，缺点是插入慢
 * @Author: jay
 * @Date: 2021/5/29 19:59
 */
public class BinarySearchOrderedArraySymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private int size;

    private K[] keys;

    private V[] values;

    private int capacity = 8;

    BinarySearchOrderedArraySymbolTable(){}

    /**
     * @Method BinarySearchOrderedSymbolTable
     * @Description 初始化二叉搜索符号表
     * @param capacity 初始容量
     */
    BinarySearchOrderedArraySymbolTable(int capacity){
        if (capacity > 8) {
            this.capacity = capacity;
        }
    }

    /**
    * @Description: 根据当前元素个数重置keys、values数组的大小
    * @Param: []
    * @return: void
    * @Author: Jay
    * @Date: 2021/12/17
    */
    private void resize(){
        if(size > capacity/2){
            keys = Arrays.copyOf(keys, 2*capacity);
            values = Arrays.copyOf(values, 2*capacity);
        }
        else if(size < capacity/4 && size > 8){
            keys = Arrays.copyOf(keys, capacity/2);
            values = Arrays.copyOf(values, capacity/2);
        }
    }

    /**
     * @Description: 将键值对放入符号表
     * @Param: [k, v]
     * @return: void
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public void put(K k, V v) {

        // 延迟初始化
        if(size == 0){
            keys = (K[]) new Comparable[this.capacity];
            values = (V[]) new Object[this.capacity];
        }

        // 开始插入
        if(size == 0) {
            keys[0] = k;
            values[0] = v;
        }
        else {
            int rank = rank(k);
            for (int i = size; i > rank; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[rank] = k;
            values[rank] = v;
        }

        size++;
        resize();
    }

    /**
     * @Description: 根据key获取指定value，key不存在则返回null
     * @Param: [k]
     * @return: V
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public V get(K k) {
        int index = getKeyRank(k, 0, size-1);
        if(index == -1)
            return null;
        else
            return values[index];
    }

    /**
     * @Description: 删除指定key及其value
     * @Param: [k]
     * @return: void
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public void delete(K k) {
        int index = getKeyRank(k, 0, size-1);
        if(index != -1){
            for(int i = index; i < size-2; i++){
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
            keys[size-1] = null;
            values[size-1] = null;
            size--;
        }
    }

    /**
     * @Description: 判断符号表是否包含k
     * @Param: [k]
     * @return: boolean
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public boolean contains(K k) {
        return OrderedSymbolTable.super.contains(k);
    }

    /**
     * @Description: 判断当前符号表是否为空
     * @Param: []
     * @return: boolean
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public boolean isEmpty() {
        return OrderedSymbolTable.super.isEmpty();
    }

    /**
     * @Description: 返回当前符号表内的元素个数
     * @Param: []
     * @return: int
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public int size() {
        return size;
    }

    /**
    * @Description: 返回最小key
    * @Param: []
    * @return: K
    * @Author: Jay
    * @Date: 2021/12/17
    */
    @Override
    public K min() {
        return keys[0];
    }

    /**
    * @Description: 返回最大key
    * @Param: []
    * @return: K
    * @Author: Jay
    * @Date: 2021/12/17
    */
    @Override
    public K max() {
        return keys[size-1];
    }

    /**
     * @Description: 返回小于等于k的最大键，若不存在则返回null
     * @Param: [k]
     * @return: K
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public K floor(K k) {
        int index = getKeyRank(k, 0, size-1);
        if(index == -1){
            index = getKeyRankWhetherExit(k, 0, size-1);
            if(index == 0)
                return null;
            else if(index >= size)
                return keys[size-1];
            else
                return keys[index-1];
        }
        else
            return keys[index];
    }

    /**
     * @Description: 返回大于等于k的最小键, 若不存在则返回null
     * @Param: [k]
     * @return: K
     * @Author: Jay
     * @Date: 2021/12/17
     */

    @Override
    public K ceiling(K k) {
        int index = getKeyRankWhetherExit(k, 0, size-1);
        if(index > size-1)
            return null;
        else
            return keys[index];
    }

    /**
     * @Description: 返回k在keys中的排名，k不一定要在keys中
     * @Param: [k]
     * @return: int
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public int rank(K k) {
        return getKeyRankWhetherExit(k, 0, size-1);
    }

    /**
    * @Description: 通过rank获取key
    * @Param: [rank]
    * @return: K
    * @Author: Jay
    * @Date: 2021/12/17
    */
    @Override
    public K select(int rank) {
        return keys[rank];
    }

    /**
    * @Description: 删除最小key及其value
    * @Param: []
    * @return: void
    * @Author: Jay
    * @Date: 2021/12/17
    */
    @Override
    public void deleteMin() {
        if (size == 0) return;
        for (int i = 0; i < size-1; i++) {
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }
        keys[size-1] = null;
        values[size-1] = null;
        size--;
    }

    /**
    * @Description: 删除最大key及其value
    * @Param: []
    * @return: void
    * @Author: Jay
    * @Date: 2021/12/17
    */
    @Override
    public void deleteMax() {
        if (size == 0) return;
        keys[size-1] = null;
        values[size-1] = null;
        size--;
    }

    @Override
    public int size(K lo, K hi) {
        int loIndex = rank(lo);
        int hiIndex = rank(hi);
        int result = hiIndex - loIndex;
        if (contains(hi)) result++;
        return result;
    }

    /**
     * @Description: 获取键值在[lo,hi]区间的所有键
     * @Param: [lo, hi]
     * @return: java.lang.Iterable<K>
     * @Author: Jay
     * @Date: 2021/12/17
     */
    @Override
    public Iterable<K> keys(K lo, K hi) {
        ArrayList<K> result = new ArrayList<>(Arrays.asList(keys).subList(getKeyRankWhetherExit(lo, 0, size - 1), getKeyRankWhetherExit(hi, 0, size - 1) + 1));
        if(!contains(hi)) result.remove(result.size()-1);
        return result;
    }


    // 2 4 6 8 10
    /**
    * @Description: 返回所有keys
    * @Param: []
    * @return: java.lang.Iterable<K>
    * @Author: Jay
    * @Date: 2021/12/17
    */
    @Override
    public Iterable<K> keys() {
        ArrayList<K> result = new ArrayList<>();
        for(int i = 0; i < size; i++)
            result.add(keys[i]);
        return result;
    }


    /**
    * @Description: 使用二分查找返回指定key的rank，若key不存在于该符号表中，则返回-1
    * @Param: [k, left, right]
    * @return: int
    * @Author: Jay
    * @Date: 2021/12/17
    */
    public int getKeyRank(K k, int left, int right){
        // 判断是否只剩最后一个元素没有比较
        if(left == right) {
            if(k.compareTo(keys[left]) != 0)
                return -1;
            else
                return left;
        }

        int mid = (left + right)/2;
        if(k.compareTo(keys[mid]) == 0)
            return mid;
        else if(k.compareTo(keys[mid]) < 0)
            return getKeyRank(k, left, mid);
        else
            return getKeyRank(k, mid+1, right);
    }

    /**
    * @Description: 使用二分查找返回指定key的rank，无论是否存在
    * @Param: [k, left, right]
    * @return: int
    * @Author: Jay
    * @Date: 2021/12/17
    */
    public int getKeyRankWhetherExit(K k, int left, int right){
        if(size == 0) return 0;

        // 判断是否只剩最后一个元素没有比较
        if(left == right) {
            if (k.compareTo(keys[left]) <= 0)
                return left;
            else
                return right+1;
        }

        int mid = (left + right)/2;
        if(k.compareTo(keys[mid]) == 0)
            return mid;
        else if(k.compareTo(keys[mid]) < 0)
            return getKeyRankWhetherExit(k, left, mid);
        else
            return getKeyRankWhetherExit(k, mid+1, right);
    }

    public static void main(String[] args) {
        BinarySearchOrderedArraySymbolTable<Integer, Integer> symbolTable = new BinarySearchOrderedArraySymbolTable<>();

        // 测试put()
        System.out.println("测试put()");
        for (int i = 0; i < 10; i++) {
            symbolTable.put(i, i + 100);
            System.out.println("插入<"+ i + "," + (i+100) + ">");
        }
        System.out.println();

        // 测试get()
        System.out.println("测试get()");
        for(int i = 0; i < 15; i++){
            System.out.println("get(" + i + ") : " + symbolTable.get(i));
        }
        System.out.println();

        // 测试rank
        System.out.println("测试rank()");
        for (int i = 0; i < 15; i++) {
            System.out.println("rank(" + i + ") : " + symbolTable.rank(i));
        }
        System.out.println();

        // 测试floor()
        System.out.println("测试floor()");
        for (int i = -5; i < 10; i++) {
            System.out.println("floor(" + i + ") : " + symbolTable.floor(i));
        }
        System.out.println();

        System.out.println("测试ceiling()");
        for (int i = 0; i < 15; i++) {
            System.out.println("ceiling(" + i + ") : " + symbolTable.ceiling(i));
        }
        System.out.println();

        System.out.println("测试contains()");
        for (int i = 0; i < 15; i++) {
            System.out.println("contains(" + i + ") : " + symbolTable.contains(i));
        }
        System.out.println();

        System.out.println("测试getKeyRankWhetherExit()");
        for (int i = -2; i < 13; i++) {
            System.out.println("getKeyRankWhetherExit(" + i + ") : " + symbolTable.getKeyRankWhetherExit(i, 0, symbolTable.size-1));
        }
        System.out.println();

        System.out.println("测试getKeyRank()");
        for (int i = -2; i < 13; i++) {
            System.out.println("getKeyRank(" + i + ") : " + symbolTable.getKeyRank(i, 0, symbolTable.size-1));
        }
        System.out.println();

        System.out.println("测试rank()");
        for (int i = -2; i < 13; i++) {
            System.out.println("rank(" + i + ") : " + symbolTable.rank(i));
        }
        System.out.println();

        System.out.println("测试deleteMin()，执行一次deleteMin()后");
        symbolTable.deleteMin();
        Iterable<Integer> keys = symbolTable.keys();
        for(Integer i : keys)
            System.out.println(i);
        System.out.println();

        System.out.println("测试deleteMax()，执行一次deleteMax()后");
        symbolTable.deleteMax();
        keys = symbolTable.keys();
        for(Integer i : keys)
            System.out.println(i);
        System.out.println();

        System.out.println("");




    }
}
