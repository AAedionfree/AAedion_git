package oo;

import java.util.Scanner;

public class nape {
    //符号 系数 指数
    private int coefficient;
    private int index;
    nape(String na){
        int i,temp,head,tail;
        char sign;
        // sign x
        if(na.contains("x")&&!na.contains("*")&&!na.contains("^")){
            sign = na.charAt(0);
            coefficient =((sign=='+')?1:-1)*1;
            index =1;
        }
        // final  such as 3
        if(!na.contains("x")){
            sign = na.charAt(0);
            coefficient =((sign=='+')?1:-1)*Integer.parseInt(na.substring(1,na.length()));
            index = 0;
        }
        //+3*x
        if(na.contains("x")&&na.contains("*")&&!na.contains("^")){
            temp = na.indexOf("x");
            sign = na.charAt(0);
            coefficient = ((sign=='+')?1:-1)*Integer.parseInt(na.substring(1,temp-1));
            index =1;
        }
        //+3*x^2
        if(na.contains("x")&&na.contains("*")&&na.contains("^")){
            temp = na.indexOf("x");
            sign = na.charAt(0);
            coefficient = ((sign=='+')?1:-1)*Integer.parseInt(na.substring(1,temp-1));
            index =Integer.parseInt(na.substring(temp+2,na.length()));
        }
        //+x^2
        if(na.contains("x")&&!na.contains("*")&&na.contains("^")){
            temp = na.indexOf("x");
            sign = na.charAt(0);
            coefficient = ((sign=='+')?1:-1)*1;
            index =Integer.parseInt(na.substring(temp+2,na.length()));
        }
    }
    nape(int coeff,int index){
        this.coefficient = coeff;
        this.index = index;
    }
    public int get_coeff(){
        return coefficient;
    }
    public int get_index(){
        return index;
    }
    public nape dervation(){
        return new nape(this.coefficient*this.index,this.index -1);
    }
}
