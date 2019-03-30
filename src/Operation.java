package oothree;

public class Operation extends Term {
    Operation(String a) {
        isleaf = 0;
        str = a;
    }

    public Term der() {
        return new Operation("error");
    }
}
