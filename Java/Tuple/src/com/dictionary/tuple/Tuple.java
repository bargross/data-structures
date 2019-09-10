package com.dictionary.tuple;

import com.dictionary.interfaces.SimpleTuple;

public class Tuple<L, R> implements com.dictionary.interfaces.SimpleTuple.SimpleTuple<L, R> {
    private L elementOne;
    private R elementTwo;

    public Tuple(L elementOne, R elementTwo) {
        this.elementOne = elementOne;
        this.elementTwo = elementTwo;
    }

    public Tuple() {}

    public L getElemOne() { return elementOne; }
    public void setElemOne(L elementOne) { this.elementOne = elementOne; }

    public R getElemTwo() { return elementTwo; }
    public void setElemTwo(R elementTwo) { this.elementTwo = elementTwo; }

    public boolean equals(SimpleTuple.SimpleTuple<L, R> tuple) {
        return (tuple.getElemOne().hashCode() == elementOne.hashCode()) && (tuple.getElemTwo().hashCode() == elementTwo.hashCode());
    }
}
