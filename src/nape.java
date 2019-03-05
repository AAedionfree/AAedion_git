package oo;

public class Nape {
    //符号 系数 指数
    private int coefficient;
    private int index;

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
            coefficient = change * 1;
            index = 1;
        }
        if (!na.contains("x"))
        {
            coefficient = change
                    * Integer.parseInt(na.substring(1,na.length()));
            index = 0;
        }
        if (na.contains("x") && na.contains("*") && !na.contains("^"))
        {
            temp = na.indexOf("x");
            coefficient = change * Integer.parseInt(na.substring(1,temp - 1));
            index = 1;
        }
        if (na.contains("x") && na.contains("*") && na.contains("^"))
        {
            temp = na.indexOf("x");
            coefficient = change * Integer.parseInt(na.substring(1,temp - 1));
            index = Integer.parseInt(na.substring(temp + 2,na.length()));
        }
        if (na.contains("x") && !na.contains("*") && na.contains("^"))
        {
            temp = na.indexOf("x");
            coefficient = change * 1;
            index = Integer.parseInt(na.substring(temp + 2,na.length()));
        }
    }

    Nape(int coeff,int index)
    {
        this.coefficient = coeff;
        this.index = index;
    }

    public int get_coeff()
    {
        return coefficient;
    }

    public int get_index()
    {
        return index;
    }

    public Nape dervation()
    {
        return new Nape(this.coefficient * this.index,this.index - 1);
    }
}
