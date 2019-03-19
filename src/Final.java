package oothree;

import java.math.BigInteger;

public class Final extends Leaf {
    Final(String a) {
        super(a);
        str = a;
    }

    public String der(){
        return "0";
    }
}
