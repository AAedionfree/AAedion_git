package oofiveone;

public class ElevatorOS {
    private int id = 1;
    private Elevator elevator;
    private Dispatching dispatching;
    private Object lock = new Object();
    private RequestQueue queue;

    public ElevatorOS(int id) {
        this.id = id;
        this.queue = new RequestQueue();
        this.elevator = new Elevator(lock);
        this.dispatching = new Dispatching(this.elevator, lock, queue);
    }

    public Object getlock() {
        return lock;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Dispatching getDispatching() {
        return dispatching;
    }

    public void begin() {
        this.dispatching.start();
    }
}
