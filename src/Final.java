package oothree;

import java.math.BigInteger;

public class Final extends Leaf {
    Final(String a) {
        super(a);
    }

    public Term der(){
        return new Final("0");
    }
}
