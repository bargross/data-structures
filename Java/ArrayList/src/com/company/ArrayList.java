package com.company;

import com.company.interfaces.List;

public class ArrayList<T> implements List<T> {
    T[] container;
    int size = 0;
    static int INITIAL_CAPACITY = 4;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        container = (T[]) new Object[initialCapacity];
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
            return container[index];
        }

        throw new IndexOutOfBoundsException("The List is empty");
    }

    public void add(T value) {
        if(size > 4 && size - container.length == 4) {
            resize();
        }
        container[size++] = value;
    }

    private void setInternalArray(T[] container) {
        this.container = container;
    }

    private T[] getInternalArray() {
        return container;
    }

    private void resize() {
        T[] temp = (T[]) new Object[container.length^2];
        for(int index=0; index < container.length; ++index) {
            temp[index] = get(index);
        }
        container = temp;
    }
}
