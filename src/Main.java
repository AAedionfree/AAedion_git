package oofiveone;

import com.oocourse.TimableOutput;

public class Main {
    private final static boolean debug = false;
    private final static int num = 1;
    public static void main(String[] args) {
        TimableOutput.initStartTimestamp();
        new Generalcontrol(num).start();
    }
}
