package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Either<L, R> {
    private ImmutableContainer<L, R> container;
    private boolean isContainer;

    Either() { isContainer = false; }

    public void setLeft(L left) {
        container = new ImmutableContainer<>();
        container.setLeft(left);
        container.setRight(null);
    }

    public void setRight(R right) {
        container = new ImmutableContainer<>();
        container.setLeft(null);
        container.setRight(right);
    }

    public void setContainerFlag(boolean isContainer) {
        this.isContainer = isContainer;
    }

    public <T> T getValue() {
        if(container.isLeftNull() && !container.isRightNull()) {
            return (T)((Object)container.getRight());
        }
        if(!container.isLeftNull() && container.isRightNull()) {
            return (T)((Object)container.getLeft());
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
