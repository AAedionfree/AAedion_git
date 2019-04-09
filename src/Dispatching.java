package oofiveone;

import com.oocourse.elevator2.PersonRequest;

public class Dispatching extends Thread {
    private int nowfloor = 1;
    private int goalfloor;
    private PersonRequest out[] = new PersonRequest[30];
    private PersonRequest in[] = new PersonRequest[30];
    private int nout;
    private int nin;
    private PersonRequest mainrequest = null;
    private Elevator elevator;
    private Object lock;
    private RequestQueue inputqueue;
    private RequestQueue dealqueue = new RequestQueue();
    private int waiting = 0;
    private int end = 0;

    public Dispatching(Elevator elevator, Object lock, RequestQueue queue) {
        this.inputqueue = queue;
        this.elevator = elevator;
        this.lock = lock;
    }

    public void run() {
        int i;
        while (true) {
            nout = 0;
            nin = 0;
            if(mainrequest == null && dealqueue.get(0) == null && inputqueue.get(0) == null && end == 1){
                return;
            }
            if (mainrequest == null) {
                if(dealqueue.get(0) == null){
                    if (inputqueue.get(0) == null) {
                        //System.out.println("waiting input NOW"+nowfloor);
                        synchronized (this.lock) {
                            try {
                                if (inputqueue.get(0) == null) {
                                    waiting = 1;
                                    lock.wait();
                                    waiting = 0;
                                    Thread.sleep(10);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if(mainrequest == null && dealqueue.get(0) == null && inputqueue.get(0) == null && end == 1){
                            return;
                        }
                    }
                    mainrequest = inputqueue.get(0);
                    inputqueue.remove(0);
                    for (i = nowfloor; i != mainrequest.getFromFloor(); i = elevator.moveonefloor(i, mainrequest.getFromFloor()));
                    nowfloor = mainrequest.getFromFloor();
                    nin = 1;
                    in[0] = mainrequest;
                    goalfloor = mainrequest.getToFloor();
                }
                else{
                    mainrequest = dealqueue.get(0);
                    dealqueue.remove(0);
                    goalfloor = mainrequest.getToFloor();
                }
            }
            else {
                if (mainrequest.getToFloor() == nowfloor) {
                    nout = 1;
                    out[0] = mainrequest;
                    mainrequest = null;
                    //System.out.println("remove");
                }
            }
            for (i = 0; i < dealqueue.size(); i++) {
                PersonRequest temp = dealqueue.get(i);
                if (nowfloor == temp.getToFloor()) {
                    out[nout] = temp;
                    nout++;
                    dealqueue.remove(i);
                }
            }
            for (i = 0; i < inputqueue.size(); i++) {
                PersonRequest temp = inputqueue.get(i);
                if (nowfloor == temp.getFromFloor()) {
                    in[nin] = temp;
                    nin++;
                    dealqueue.add(temp);
                    inputqueue.remove(i);
                }
            }
            if (nout != 0 || nin != 0) {
                elevator.open(nowfloor);
                for (i = 0; i < nout; i++) {
                    elevator.out(out[i].getPersonId(), nowfloor);
                }
                for (i = 0; i < nin; i++) {
                    elevator.enter(in[i].getPersonId(), nowfloor);
                }
                elevator.close(nowfloor);
            }
            nowfloor = elevator.moveonefloor(nowfloor, goalfloor);
            //System.out.println(nowfloor);
        }
    }

    public void setEnd(){
        this.end = 1;
    }
    public int getWaiting() {
        return waiting;
    }
}
