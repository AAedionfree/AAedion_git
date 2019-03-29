package oothree;

public abstract class Leaf extends Term {
    public abstract Term der();
    Leaf(String a){
        isleaf = 1;
        str = a;
    }
}
