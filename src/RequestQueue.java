package oofive;

import com.oocourse.elevator1.PersonRequest;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue {
    private static Queue<PersonRequest> queue = new LinkedList<PersonRequest>();

    public static synchronized PersonRequest poll() {
        return queue.poll();
    }

    public static synchronized PersonRequest peek() {
        return queue.peek();
    }

    public static synchronized void offer(PersonRequest e) {
        queue.offer(e);
    }
}
