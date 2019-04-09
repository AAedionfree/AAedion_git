package oofiveone;

import com.oocourse.elevator2.ElevatorInput;
import com.oocourse.elevator2.PersonRequest;

import java.io.IOException;

public class Generalcontrol extends Thread
{

    private int maxnumber = 1;
    private ElevatorOs[] allelevator = new ElevatorOs[maxnumber];

    public Generalcontrol(int num) {
        int i;
        for (i = 0; i < num; i++) {
            allelevator[i] = new ElevatorOs(i + 1);
            allelevator[i].begin();
        }
    }

    public void run() {
        try {
            input();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void input() throws IOException {
        ElevatorInput elevatorInput = new ElevatorInput(System.in);
        ElevatorOs temp = allelevator[0];
        while (true) {
            PersonRequest request = elevatorInput.nextPersonRequest();
            if (temp.getDispatching().getWaiting() == 1) {
                Object lock = allelevator[0].getlock();
                synchronized (lock) {
                    if (request == null) {
                        lock.notify();
                        temp.getDispatching().setEnd();
                        elevatorInput.close();
                        return;
                    }
                    allelevator[0].getQueue().add(request);
                    lock.notify();
                }
            } else {
                if (request == null) {
                    temp.getDispatching().setEnd();
                    elevatorInput.close();
                    return;
                }
                allelevator[0].getQueue().add(request);
            }
            //System.out.println(request);
            //TimableOutput.println
            // (String.format("----%d", request.getPersonId()));
        }
    }
}
