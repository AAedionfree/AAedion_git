package ootwo;

import java.math.BigInteger;

public class Trig {
    private  BigInteger nape_coeff;
    private BigInteger nape_index;
    private BigInteger index;

    Trig(BigInteger nape_coeff, BigInteger nape_index, BigInteger index){
        this.nape_coeff = nape_coeff;
        this.nape_index = nape_index;
        this.index = index;
    }

    public BigInteger get_index(){
        return index;
    }
}