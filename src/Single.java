package ootwo;

import java.math.BigInteger;
import java.util.HashMap;

public class Single {
    private Nape nape;
    private Trig sin;
    private Trig cos;
    //标准化格式

    Single(HashMap in) {
        BigInteger one = new BigInteger("1");
        this.nape = new Nape(
                new BigInteger(in.get("final").toString()),
                new BigInteger(in.get("x").toString()));
        this.sin = new Trig(one, one,
                new BigInteger(in.get("sin").toString()));
        this.cos = new Trig(one, one,
                new BigInteger((in.get("cos").toString())));
    }

    Single(BigInteger k, BigInteger a, BigInteger b, BigInteger c) {
        BigInteger one = new BigInteger("1");
        this.nape = new Nape(k, a);
        this.sin = new Trig(one, one, b);
        this.cos = new Trig(one, one, c);
    }

    public BigInteger get_k() {
        BigInteger k = nape.get_coeff();
        return k;
    }

    public BigInteger get_a() {
        BigInteger a = nape.get_index();
        return a;
    }

    public BigInteger get_b() {
        BigInteger b = sin.get_index();
        return b;
    }

    public BigInteger get_c() {
        BigInteger c = cos.get_index();
        return c;
    }

    public Single[] der() {
        BigInteger one = new BigInteger("1");
        BigInteger k = nape.get_coeff();
        BigInteger a = nape.get_index();
        BigInteger b = sin.get_index();
        BigInteger c = cos.get_index();
        Single[] returnlist = new Single[3];
        //ak a-1 b c
        returnlist[0] = new Single(
                k.multiply(a), a.subtract(one), b, c);
        //bk a b-1 c+1
        returnlist[1] = new Single(
                k.multiply(b), a, b.subtract(one), c.add(one));
        //-ck a b+1 c-1
        returnlist[2] = new Single(
                k.multiply(c).multiply(new BigInteger("-1")),
                a, b.add(one), c.subtract(one));
        return returnlist;
    }

}