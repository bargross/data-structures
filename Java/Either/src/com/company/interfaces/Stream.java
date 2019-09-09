package com.company.interfaces;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public interface Stream<T> {
    Iterator<T> iterator();
    default void forEach(Consumer<T> var1) {
        Objects.requireNonNull(var1);
        Iterator var2 = this.iterator();

        while(var2.hasNext()) {
            T var3 = (T)var2.next();
            var1.accept(var3);
        }

    }

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(this.iterator(), 0);
    }
}
