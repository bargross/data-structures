package com.dictionary;

import com.dictionary.interfaces.*;
import java.util.Objects;

public class KeyValuePair<K, V> implements Pair<K, V> {
    private K key;
    private V value;
    private KeyValuePair<K, V> next;
    private int hash;

    public KeyValuePair() {}
    public KeyValuePair(K key, V value, KeyValuePair<K, V> next, int hash) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public KeyValuePair<K, V> getNext() {
        return this.next;
    }

    public void setValue(V value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public void setNext(KeyValuePair<K, V> next) {
        this.next = next;
    }

    public boolean hasKey(K key, int hash) {
        if(key == null) {
            return false;
        }
        return this.key.equals(key) || this.hash == hash;
    }

    public boolean isNextEmpty() {
        return next == null;
    }

    // @override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if (o instanceof KeyValuePair<?, ?>) {
            KeyValuePair<K, V> instance = ((KeyValuePair<K, V>)o);
            boolean isSameValue = value instanceof Object ? value.hashCode() == instance.getValue().hashCode() : value == instance.getValue();
            K key = instance.getKey();
            return hasKey( key, key.hashCode() ) &&  isSameValue;
        }

        return false;
    }

    public String toString() {
        return "[{ "+key+" } : { "+value+" }] \n";
    }
}
