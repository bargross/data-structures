package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Either<L, R> {
    private ImmutableContainer<L, R> CONTAINER;
    private boolean isContainer;

    Either() { isContainer = false; }

    public void setLeft(L left) {
        CONTAINER = new ImmutableContainer<>();
        CONTAINER.setLeft(left);
        CONTAINER.setRight(null);
    }

    public void setRight(R right) {
        CONTAINER = new ImmutableContainer<>();
        CONTAINER.setLeft(null);
        CONTAINER.setRight(right);
    }

    public void setContainerFlag(boolean isContainer) {
        this.isContainer = isContainer;
    }

    public <T> T getValue() {
        if(CONTAINER.isLeftNull() && !CONTAINER.isRightNull()) {
            return (T)((Object)CONTAINER.getRight());
        }
        if(!CONTAINER.isLeftNull() && CONTAINER.isRightNull()) {
            return (T)((Object)CONTAINER.getLeft());
        }

        return null;
    }


    public <T> List<T> map(Function<T, T> func) {
        List<T> elements = (List<T>)this.<T>getValue();
        if(elements == null || !isContainer) {
            return new ArrayList<T>();
        }

        for(int index = 0; index < elements.size(); ++index) {
            elements.set(index, func.apply(elements.get(index)));
        }

        return elements;
    }

}
