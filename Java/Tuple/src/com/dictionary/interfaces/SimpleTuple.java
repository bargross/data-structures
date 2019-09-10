package com.dictionary.interfaces;

public interface SimpleTuple<L, R> {
    L getElemOne();
    R getElemTwo();

    void setElemOne(L elementOne);
    void setElemTwo(R elementTwo);

    boolean equals(SimpleTuple<L, R> tuple);
}
