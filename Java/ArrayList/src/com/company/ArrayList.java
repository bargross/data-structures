package com.company;

import com.company.interfaces.List;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ArrayList<T extends Object> implements List<T> {
    private T[] container;
    int size = 0;
    static int INITIAL_CAPACITY = 4;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        container = (T[])new Object[initialCapacity];
        INITIAL_CAPACITY = initialCapacity;
    }

    public ArrayList(ArrayList<T> list) {
        size = list.size();
        setInternalArray(list.getInternalArray());
    }

    public ArrayList(T[] container) {
        size = container.length;
        setInternalArray(container);
    }

    public int size() { return size; }
    public T get(int index) {
        if(index > 0 && index < container.length) {
            return (T)container[index];
        }

        throw new IndexOutOfBoundsException("The List is empty");
    }

    public void add(T value) {
        if(size > 4 && size - container.length == 4) {
            resize();
        }
        container[size++] = value;
    }

    public void bucketSort(T[] container) {
        java.util.List<T>[] tempContainer = (java.util.ArrayList<T>[])new java.util.ArrayList[container.length];
        for(int index = 0; index < container.length; ++index) {
            int bucketIndex = container.length * container[index].hashCode();
            if(tempContainer[bucketIndex] == null) {
                tempContainer[bucketIndex] = new java.util.ArrayList<T>();
                tempContainer[bucketIndex].add((T)container[index]);
            }

        }

        for(int index = 0; index < container.length; ++index) {
            if(tempContainer[index] != null) {
                java.util.List<T> temp = (java.util.ArrayList<T>)tempContainer[index];
                Collections.sort(temp, new Comparator<T>() {
                    @Override
                    public int compare(T t, T t1) {
                        if(t.hashCode() > t1.hashCode())
                            return 1;
                        else if(t.hashCode() < t1.hashCode())
                            return 0;
                        else return -1;
                    }
                });
            }
        }

        int iOrderedElement = 0;
        for(int index=0; index < container.length; ++index) {
            List<T> temp = ((List<T>)container[index]);
            if(temp == null) continue;
            for(int iContainer = 0; iContainer < temp.size(); ++iContainer) {
                container[iOrderedElement++] = temp.get(iContainer);
            }
        }
    }

    public void bucketSort(Collection<T> collection) {
        T[] collectionArray = (T[])collection.toArray();
        bucketSort(collectionArray);
        collection = Arrays.asList(collectionArray);
    }

    public static void bucketSort(List<Object> collection) {
        bucketSort(collection);
    }

    public int binarySearch(T[] collection, Object value, int min, int max) {
        int mid = Math.abs((min + max) / 2);

        if(!isSorted(collection)) {
            if(collection.length > 50000) {
                bucketSort(collection);
            } else {
                quickSort(collection);
            }
        }

        if(min > max) return -1;
        else if(collection[mid].hashCode() == value.hashCode()) return mid;
        else if(collection[mid].hashCode() > value.hashCode()) return binarySearch(collection, value, min, mid - 1);
        else if(collection[mid].hashCode() < value.hashCode()) return binarySearch(collection, value, mid + 1, max);

        return -1;
    }

    public boolean contains(T value) {
        return binarySearch(container, value, 0, container.length) != -1;
    }

    public int indexOf(T value) {
        return binarySearch(container, value, 0, container.length);
    }

    // ====================================================

    private static void quickSort(Object[] collection) {
        Arrays.sort(collection);
    }

    private static boolean isSorted(Object[] collection) {
        return false;
    }

    private void setInternalArray(T[] container) {
        this.container = container;
    }

    private T[] getInternalArray() {
        return (T[])container;
    }

    private void resize() {
        T[] temp = (T[])new Object[container.length^2];
        for(int index=0; index < container.length; ++index) {
            temp[index] = get(index);
        }
        container = temp;
    }
}
