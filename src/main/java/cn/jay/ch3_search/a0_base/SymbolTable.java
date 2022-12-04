package cn.jay.ch3_search.a0_base;

/*
    符号表(symbol table)的基本接口
 */
public interface SymbolTable<Key extends Comparable<Key>,Value>{

    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    default boolean contains(Key key){ return get(key) != null; }

    default boolean isEmpty(){
        return size() == 0;
    };

    int size();

    // 返回所有键
    Iterable<Key> keys();
}
