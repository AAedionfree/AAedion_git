package oofiveone;

import com.oocourse.TimableOutput;

public class Main {
    private static final boolean debug = false;
    private static final int num = 1;

    public static void main(String[] args) {
        TimableOutput.initStartTimestamp();
        new Generalcontrol(num).start();
    }
}
