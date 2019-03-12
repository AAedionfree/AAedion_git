package ootwo;

import java.math.BigInteger;

public class Trig {
    private BigInteger napecoeff;
    private BigInteger napeindex;
    private BigInteger index;

    Trig(BigInteger napecoeff, BigInteger napeindex, BigInteger index) {
        this.napecoeff = napecoeff;
        this.napeindex = napeindex;
        this.index = index;
    }

    public BigInteger get_index() {
        return index;
    }
}