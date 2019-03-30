package oothree;

public class Nape extends Leaf {
    Nape(String a) {
        super(a);
        str = a;
    }

    public Term der() {
        return new Nape("1");
    }
}
