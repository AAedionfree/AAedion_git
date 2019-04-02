package oofive;

import com.oocourse.TimableOutput;

public class Main {
    public static void main(String[] args) {
        TimableOutput.initStartTimestamp();
        new Elevator().start();
        new Inputcontrol().start();
    }
}
