package com.company.interfaces;

import java.util.Collection;

public interface List<T> {
    void add(T value);
    T get(int index);
    int size();

    void sort();
    void bucketSort(Collection<T> collection);
    void bucketSort(List<T> collection);

    boolean contains(T value);
    int indexOf(T value);
}
