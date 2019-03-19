package oothree;

public class Term {
    public String str;
    public int isleaf;
    public String der(){
        return "1";
    }
    public static void main(String[] args) {
        Term a = new Trig("sin");
        System.out.print(a.isleaf);
    }
}
