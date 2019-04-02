package oofive;

import com.oocourse.elevator1.PersonRequest;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue {
    private static Queue<PersonRequest> queue = new LinkedList<PersonRequest>();
    public synchronized static PersonRequest poll(){
        return queue.poll();
    }
    public synchronized static PersonRequest peek(){
        return queue.peek();
    }
    public synchronized static void offer(PersonRequest e){
        queue.offer(e);
    }
}
