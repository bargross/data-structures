package com.company;

import com.company.interfaces.Immutable;
import java.util.function.Function;

public class ImmutableContainer<L, R> implements Immutable<L, R> {
    private L left;
    private R right;

    ImmutableContainer() { }

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public L getLeft() { return left; }

    public R getRight() { return right;  }

    public boolean isLeftNull() { return left == null; }

    public boolean isRightNull() { return right == null; }

    public <T> String toString(Function<T, String> func) {
        if(left == null && right != null) {
            T curatedValue = (T)right;
            if(curatedValue == null) {
                return "";
            }
            return func.apply(curatedValue);
        }

        if(left != null && right == null) {
            T curatedValue = (T)right;
            if(curatedValue == null) {
                return "";
            }
            return func.apply(curatedValue);
        }

        return "";
    }
}
