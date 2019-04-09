package oofiveone;

import com.oocourse.TimableOutput;

public class Elevator {
    private Object lock;
    private int nowfloor = 1;
    private RequestQueue queue;
    private int end = 0;
    private int waiting = 0;

    public Elevator(Object lock) {
        this.lock = lock;
    }

    public void setend() {
        end = 1;
    }

    public int getNowfloor() {
        return nowfloor;
    }

    public int moveonefloor(int now, int goal) {
        int change = 0;
        if (now > goal) {
            change = -1;
        } else if (now < goal) {
            change = 1;
        } else {
            return now;
        }
        nowfloor = now + change;
        if (nowfloor == 0) {
            nowfloor += change;
        }
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrive(nowfloor);

        return nowfloor;
    }

    public void open(int floor) {
        TimableOutput.println(String.format("OPEN-%d", floor));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close(int floor) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TimableOutput.println(String.format("CLOSE-%d", floor));
    }

    public void enter(int id, int floor) {
        TimableOutput.println(String.format("IN-%d-%d", id, floor));
    }

    public void out(int id, int floor) {
        TimableOutput.println(String.format("OUT-%d-%d", id, floor));
    }

    public void arrive(int floor) {
        TimableOutput.println(String.format("ARRIVE-%d", floor));
    }

    public int getWaiting() {
        return waiting;
    }

    public void setqueue(RequestQueue queue) {
        this.queue = queue;
    }

}
