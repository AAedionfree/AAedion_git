package oofiveone;

import com.oocourse.elevator2.ElevatorInput;
import com.oocourse.elevator2.PersonRequest;

import java.io.IOException;


public class Generalcontrol extends Thread {
    private int maxnumber = 1;
    private ElevatorOS[] allelevator = new ElevatorOS[maxnumber];

    public Generalcontrol(int num) {
        int i;
        for (i = 0; i < num; i++) {
            allelevator[i] = new ElevatorOS(i + 1);
            allelevator[i].begin();
        }
    }

    public void run() {
        try{
            input();
        }catch (IOException e){}
    }

    private void input() throws IOException{
        ElevatorInput elevatorInput = new ElevatorInput(System.in);
        ElevatorOS temp = allelevator[0];
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
            }
            else{
                if (request == null) {
                    temp.getDispatching().setEnd();
                    elevatorInput.close();
                    return;
                }
                allelevator[0].getQueue().add(request);
            }
            //System.out.println(request);
        }
    }
}
