package oothree;

public class Trig extends Leaf {
    Trig(String a) {
        super(a);
    }
    public Term der(){
        if(str.equals("si")){
            return new Trig("co");
        }
        else if(str.equals("co")){
            return new Trig("-1*si");
        }
        return new Trig("error");
    }
}
