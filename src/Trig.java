package oothree;

public class Trig extends Leaf {
    Trig(String a) {
        super(a);
        str = a;
    }
    public String der(){
        if(str.equals("si")){
            return "cos";
        }
        else if(str.equals("co")){
            return "-1*sin";
        }
        return "error";
    }
}
