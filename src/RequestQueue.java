package oofiveone;

import com.oocourse.elevator2.PersonRequest;

import java.util.ArrayList;

public class RequestQueue {
    private ArrayList<PersonRequest> queue = new ArrayList<PersonRequest>();

    public synchronized void add(PersonRequest e) {
        queue.add(e);
    }

    public synchronized PersonRequest get(int i) {
        try {
            return queue.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void remove(int i) {
        queue.remove(i);
    }

    public synchronized void remove(Object i) {
        queue.remove(i);
    }
}
