package com.dictionary;

import java.util.*;
import java.util.Collection;

public class Dictionary<K, V> {
    private KeyValuePair<K, V>[] bucket;
    private static int BUCKET_CAPACITY = 16;
    private int size;

    public Dictionary() {
       this(BUCKET_CAPACITY);
    }

    public Dictionary(int initialCapacity) {
        bucket = new KeyValuePair[initialCapacity];
        this.BUCKET_CAPACITY = initialCapacity;
    }

    /*

    */
    private int getBucketIndex(K key) {
        return key.hashCode() % BUCKET_CAPACITY;
    }

    /*
       re-sizes the bucket to a specified capacity
    */
    private void resize(int capacity) {
        if(capacity < BUCKET_CAPACITY) {
            capacity = BUCKET_CAPACITY;
        }

        KeyValuePair<K, V>[] resizedBucket = new KeyValuePair[capacity];
        for(int index = 0; index < this.BUCKET_CAPACITY; ++index) {
            resizedBucket[index] = bucket[index];
        }
        bucket = resizedBucket;
    }

    private Collection<K> getKeys(KeyValuePair<K, V> pair) {
        if(size == 0 || pair == null) {
            return null;
        }

        List<K> keys = new ArrayList<>(BUCKET_CAPACITY/2);
        while(pair != null) {
            keys.add(pair.getKey());
            pair = pair.getNext();
        }

        return keys;
    }

    /*
        adds a new KeyValuePair<> entry to the bucket
    */
    public void add(K key, V value) {
        // mask our key and add it to the Collection of keys

        if(key == null || value == null) {
            throw new NullPointerException("Either the Key or Value is null");
        }

        // get the index within the bucket
        int index = getBucketIndex(key);
        KeyValuePair<K, V> existing = bucket[index];

        if(existing == null) {
            bucket[index] = new KeyValuePair<K, V>(key, value, null, key.hashCode());
            ++size;
            return;
        }

        while(existing != null) {
            if(existing.hasKey(key, key.hashCode())) {
                existing.setValue(value);
                return;
            }
            existing = existing.getNext();
        }

        existing.setNext(new KeyValuePair<K, V>(key, value, null, key.hashCode()));
        ++size;
    }

    /*
        gets a specific value from the bucket via key
        as values can contain the same hash but not the same key
    */
    public V get(K key) {
        // specify the key must not be null
        Objects.requireNonNull(key);

        if(!this.containsKey(key)) {
            throw new IllegalArgumentException("Invalid argument key");
        }

        KeyValuePair<K, V> existing = bucket[getBucketIndex(key)];
        while(existing != null) {
            if(existing.hasKey(key, key.hashCode())) {
                return existing.getValue();
            }
            existing = existing.getNext();
        }

        return null;
    }

    /*
        checks whether the key is within the Collection of keys
     */
    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        KeyValuePair<K, V> existing  = bucket[index];

        if(existing == null) {
            return false;
        }

        if(existing.hasKey(key, key.hashCode())) {
            return true;
        }

        while(existing != null) {
            if(existing.hasKey(key, key.hashCode())) {
                return true;
            }
            existing = existing.getNext();
        }

        return false;
    }

    /*
        returns a Collection of keys
     */
    public List<K> getKeys() {

        if(size == 0) {
            return null;
        }

        List<K> keys = new ArrayList<>(BUCKET_CAPACITY);
        for(int index = 0; index < BUCKET_CAPACITY; ++index) {
            KeyValuePair<K, V> linkedNodes = bucket[index];
            Collection<K> keysFraction = getKeys(linkedNodes);

            if(keysFraction != null) {
                keys.addAll(keysFraction);
            }
        }
        return keys;
    }

    /*
        returns an array of keys
     */
    public K[] keysToArray() {
        Collection<K> keys = getKeys();
        if(keys != null) {
            return (K[])keys.toArray();
        }
        return null;
    }

    /*
        returns an array of values
     */
    public V[] valuesToArray() {

        if(size == 0) {
            return null;
        }

        List<K> keys = getKeys();
        V[] values = (V[])new Object[size];

        for(int index = 0; index < keys.size(); ++index) {
            K key = keys.get(index);
            values[index] = get(key);
        }
        return values;
    }

    public KeyValuePair<K, V> getPair(K key) {
        // specify the key must not be null
        Objects.requireNonNull(key);

        if(!this.containsKey(key)) {
            throw new IllegalArgumentException("Invalid argument key");
        }

        KeyValuePair<K, V> existing = bucket[getBucketIndex(key)];
        while(existing != null) {
            if(existing.hasKey(key, key.hashCode())) {
                return existing;
            }
            existing = existing.getNext();
        }

        return null;
    }

    /*
        returns the amount of elements in the bucket
     */
    public int size() { return size;  }

    public boolean equals(Object o) {

        if( !(o instanceof Dictionary) || o == null) {
            return false;
        }

        Dictionary<K, V> map = (Dictionary<K, V>)o;
        if(map.size() != this.size() || this.hashCode() != map.hashCode())   {
            return false;
        }

        K[] map2Keys = map.keysToArray();
        K[] keys = keysToArray();
        for(int index =0; index < size; ++index) {
            if(map2Keys[index] != keys[index]) {
                return false;
            }
        }

        return true;
    }

    public String toString() {

        if(size == 0) {
            return "";
        }

        StringBuffer builder = new StringBuffer();
        K[] keys = keysToArray();

        for(int index=0; index < size; ++index) {
            KeyValuePair<K, V> pair = getPair(keys[index]);
            builder.append(pair.toString());
        }
        return builder.toString();
    }
}
