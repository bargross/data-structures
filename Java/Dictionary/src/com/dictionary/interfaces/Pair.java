package com.dictionary.interfaces;
import com.dictionary.KeyValuePair;

public interface Pair<K, V> {
    K getKey();
    V getValue();

    void setValue(V value);
    void setNext(KeyValuePair<K, V> pair);

    boolean hasKey(K key, int hash);
    boolean isNextEmpty();

    String toString();
}
