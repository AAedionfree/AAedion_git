package oofive;

import com.oocourse.TimableOutput;
import com.oocourse.elevator1.PersonRequest;

import java.util.HashMap;

import java.util.Scanner;

public class Elevator extends Thread {
    private int nowfloor = 1;
    private static int end = 0;
    private static HashMap person = new HashMap();

    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            synchronized (Elevator.class) {
                try {
                    //System.out.println("Eleavaot wait");
                    Elevator.class.wait();
                    //System.out.println("Eleavaot awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (RequestQueue.peek() != null) {
                PersonRequest temp = RequestQueue.poll();
                elevatorbegin(temp);
                //System.out.println(temp);
            }
            if (Elevator.end == 1) {
                //System.out.println("Eleavaot END");
                return;
            }
        }
    }

    public static void setend()
    {
        Elevator.end = 1;
    }

    private int getNowfloor() {
        return nowfloor;
    }

    private void elevatorbegin(PersonRequest now) {
        int from = now.getFromFloor();
        int id = now.getPersonId();
        int movefloor = this.nowfloor - from;
        movefloor(this.nowfloor, from);
        open(this.nowfloor);
        enter(id, this.nowfloor);
        close(this.nowfloor);

        int to = now.getToFloor();
        movefloor(this.nowfloor, to);
        open(this.nowfloor);
        out(id, this.nowfloor);
        close(this.nowfloor);
    }

    private synchronized void movefloor(int from, int to) {
        int movefloor = from - to;
        if (movefloor < 0) {
            movefloor = -movefloor;
        }
        try {
            //TimableOutput.println(String.format("sleep %d",movefloor*500));
            Thread.sleep(movefloor * 500);
            this.nowfloor = to;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void open(int floor) {
        TimableOutput.println(String.format("OPEN-%d", floor));
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void close(int floor) {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TimableOutput.println(String.format("CLOSE-%d", floor));
    }

    private void enter(int id, int floor) {
        TimableOutput.println(String.format("IN-%d-%d", id, floor));
    }

    private void out(int id, int floor) {

        TimableOutput.println(String.format("OUT-%d-%d", id, floor));
    }
}
