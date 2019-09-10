package com.dictionary.tuple;

public class Triple<L, M, R> extends Tuple<L, M> {
    private R elementThree;

    public Triple(L elementOne, M elementTwo, R elementThree) {
        super(elementOne, elementTwo);
        this.setElemThree(elementThree);
    }

    public Triple() {}

    public void setElemThree(R elementThree) { this.elementThree = elementThree; }
    public R getElemThree() { return elementThree; }

    public boolean equals(Triple<L, M, R> triple) {
        return this.equals((Tuple<L, M>)triple) && triple.getElemThree().hashCode() == this.elementThree.hashCode();
    }

}
