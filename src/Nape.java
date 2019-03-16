package ooone;

import java.math.BigInteger;

public class Nape {
    //符号 系数 指数
    private BigInteger coefficient;
    private BigInteger index;

    Nape(String na)
    {
        int i = 0;
        int temp = 0;
        int head = 0;
        int tail = 0;
        char sign = na.charAt(0);
        int change = 0;
        if (sign == '+')
        {
            change = 1;
        }
        else {
            change = -1;
        }
        if (na.contains("x") && !na.contains("*") && !na.contains("^"))
        {
            coefficient = new BigInteger(change * 1 + "");
            index = new BigInteger("1");
        }
        if (!na.contains("x"))
        {
            coefficient = new BigInteger(na.substring(0,na.length()));
            index = new BigInteger("0");
        }
        if (na.contains("x") && na.contains("*") && !na.contains("^"))
        {
            temp = na.indexOf("x");
            coefficient = new BigInteger(na.substring(0,temp - 1));
            index = new BigInteger("1");
        }
        if (na.contains("x") && na.contains("*") && na.contains("^"))
        {
            temp = na.indexOf("x");
            coefficient = new BigInteger(na.substring(0,temp - 1));
            index = new BigInteger(na.substring(temp + 2,na.length()));
        }
        if (na.contains("x") && !na.contains("*") && na.contains("^"))
        {
            temp = na.indexOf("x");
            coefficient = new BigInteger(change * 1 + "");
            index = new BigInteger(na.substring(temp + 2,na.length()));
        }
    }

    Nape(BigInteger coeff,BigInteger index)
    {
        this.coefficient = new BigInteger(coeff + "");
        this.index = new BigInteger(index + "");
    }

    public BigInteger get_coeff()
    {
        return coefficient;
    }

    public BigInteger get_index()
    {
        return index;
    }

    public Nape dervation()
    {
        return new Nape(this.coefficient.multiply(this.index),
                this.index.subtract(new BigInteger("1")));
    }
}
