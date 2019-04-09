package oofiveone;

import com.oocourse.elevator2.PersonRequest;

public class Dispatching extends Thread {
    private int nowfloor = 1;
    private int goalfloor;
    private PersonRequest[] out = new PersonRequest[30];
    private PersonRequest[] in = new PersonRequest[30];
    private int nout;
    private int nin;
    private PersonRequest mainrequest = null;
    private Elevator elevator;
    private Object lock;
    private RequestQueue inputqueue;
    private RequestQueue dealqueue = new RequestQueue();
    private int waiting = 0;
    private int end = 0;
    private int exit = 0;

    public Dispatching(Elevator elevator, Object lock, RequestQueue queue) {
        this.inputqueue = queue;
        this.elevator = elevator;
        this.lock = lock;
        this.nin = 0;
    }

    public void run() {
        int i;
        while (true) {
            updatemain();
            if (exit == 1) {
                return;
            }
            for (i = 0; i < dealqueue.size(); i++) {
                PersonRequest temp = dealqueue.get(i);
                if (nowfloor == temp.getToFloor()) {
                    out[nout++] = temp;
                    dealqueue.remove(i);
                    i--;
                }
            }
            for (i = 0; i < inputqueue.size(); i++) {
                PersonRequest temp = inputqueue.get(i);
                if (nowfloor == temp.getFromFloor() &&
                        ((goalfloor > nowfloor && temp.getToFloor() > nowfloor)
                                || (goalfloor < nowfloor
                                && temp.getToFloor() < nowfloor))) {
                    in[nin++] = temp;
                    dealqueue.add(temp);
                    inputqueue.remove(i);
                    i--;
                }
            }
            if (nout != 0 || nin != 0) {
                elevator.open(nowfloor);
                for (i = 0; i < dealqueue.size(); i++) {
                    PersonRequest temp = dealqueue.get(i);
                    if (nowfloor == temp.getToFloor()) {
                        out[nout++] = temp;
                        dealqueue.remove(i);
                        i--;
                    }
                }
                for (i = 0; i < inputqueue.size(); i++) {
                    PersonRequest temp = inputqueue.get(i);
                    if (nowfloor == temp.getFromFloor() &&
                            ((goalfloor > nowfloor && temp.getToFloor()
                                    > nowfloor) || (goalfloor < nowfloor
                                    && temp.getToFloor() < nowfloor))) {
                        in[nin++] = temp;
                        dealqueue.add(temp);
                        inputqueue.remove(i);
                        i--;
                    }
                }
                for (i = 0; i < nout; i++) {
                    elevator.out(out[i].getPersonId(), nowfloor);
                }
                for (i = 0; i < nin; i++) {
                    elevator.enter(in[i].getPersonId(), nowfloor);
                }
                elevator.close(nowfloor);
            }
            nowfloor = elevator.moveonefloor(nowfloor, goalfloor);
        }
    }

    public void setEnd() {
        this.end = 1;
    }

    public int getWaiting() {
        return waiting;
    }

    private void updatemain() {
        int i;
        nout = 0;
        nin = 0;
        if (mainrequest == null && dealqueue.get(0) == null
                && inputqueue.get(0) == null && end == 1) {
            exit = 1;
            return;
        }
        if (mainrequest == null) {
            if (dealqueue.get(0) == null) {
                if (inputqueue.get(0) == null) {
                    synchronized (this.lock) {
                        try {
                            if (inputqueue.get(0) == null) {
                                waiting = 1;
                                lock.wait();
                                waiting = 0;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (mainrequest == null && dealqueue.get(0) == null
                            && inputqueue.get(0) == null && end == 1) {
                        exit = 1;
                        return;
                    }
                }
                mainrequest = mininput(inputqueue, nowfloor);
                for (i = nowfloor; i != mainrequest.getFromFloor(); i =
                        elevator.moveonefloor(i, mainrequest.getFromFloor())
                ) {
                }
                ;
                nowfloor = mainrequest.getFromFloor();
                nin = 1;
                in[0] = mainrequest;
                goalfloor = mainrequest.getToFloor();
            } else {
                mainrequest = mininput(dealqueue, nowfloor);
                goalfloor = mainrequest.getToFloor();
            }
        } else {
            if (mainrequest.getToFloor() == nowfloor) {
                nout = 1;
                out[0] = mainrequest;
                mainrequest = null;
            }
        }
    }

    private PersonRequest mininput(RequestQueue inputqueue, int now) {
        int i;
        int temp = 9999;
        int goal = 0;
        PersonRequest request = null;
        for (i = 0; i < inputqueue.size(); i++) {
            if (temp > Math.abs(now - inputqueue.get(i).getFromFloor())) {
                temp = Math.abs(now - inputqueue.get(i).getFromFloor());
                request = inputqueue.get(i);
                goal = i;
            }
        }
        inputqueue.remove(goal);
        return request;
    }
}
