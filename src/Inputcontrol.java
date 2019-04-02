package oofive;

import com.oocourse.elevator1.ElevatorInput;
import com.oocourse.elevator1.PersonRequest;

public class Inputcontrol extends Thread{
    public void run(){
        ElevatorInput elevatorInput = new ElevatorInput(System.in);
        while(true) {
            PersonRequest request = elevatorInput.nextPersonRequest();
            synchronized (Elevator.class){
                if (request == null) {
                    Elevator.class.notify();
                    Elevator.end = 1;
                    return;
                }
                RequestQueue.offer(request);
                Elevator.class.notify();
            }
            //System.out.println(request);
        }

    }
}
