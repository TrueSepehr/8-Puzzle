import java.util.*;

public class Info {

    public Queue<BoardNode> queue;
    public PriorityQueue<BoardNode> pQueue;
    public int time;
    private int maxQueueSize;
    public HashMap<Integer, BoardNode> visited;

    public Info() {
        queue = new LinkedList<BoardNode>();
        pQueue = new PriorityQueue<BoardNode>();
        time = 0;
        maxQueueSize = 0;
        visited = new HashMap<Integer, BoardNode>();
    }

    public void makePQueue(Comparator<BoardNode> c) {
        pQueue = new PriorityQueue<BoardNode>(c);
    }

    public void incTime() {  //timer method that begins timer
        time += 1;
    }

    public void queueSize() {
        if (queue.size() > maxQueueSize){
            maxQueueSize = queue.size();
        }
    }

    public void pQueueSize() {
        if (pQueue.size() > maxQueueSize){
            maxQueueSize = pQueue.size();
        }
    }

    public int getTime() { //time is returned
        return time;
    }

    public int getSpace() {  //space is returned
        return maxQueueSize;
    }
}
