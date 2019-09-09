package com.company.interfaces;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Immutable<L, R> {
    default void setLeft(L left) { };
    default void setRight(R right) { };

    L getLeft();
    R getRight();

    <T> String toString(Function<T, String> func);
}
