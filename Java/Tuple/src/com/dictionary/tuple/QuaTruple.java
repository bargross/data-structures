package com.dictionary.tuple;

public class QuaTruple<L, ML, MR, R> extends Triple<L, ML, MR> {
    private R elementFour;

    public QuaTruple(L elementOne, ML elementTwo, MR elementThree, R elementFour) {
        super(elementOne, elementTwo, elementThree);
        this.setElemFour(elementFour);
    }

    public QuaTruple() {}

    public void setElemFour(R elementThree) { this.elementFour = elementThree; }

    public R getElemFour() { return elementFour; }

    public boolean equals(QuaTruple<L, ML, MR, R> quaTruple) {
        return this.equals((Triple<L, ML, MR>)quaTruple) && quaTruple.getElemFour().hashCode() == this.elementFour.hashCode();
    }

}
