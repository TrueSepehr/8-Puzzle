import java.util.Comparator;
import java.util.List;

public class UniformCost implements Search {

    private BoardNode initialNode;

    public UniformCost(BoardNode node) {
        this.initialNode = node;
    }

    private static class CostComparator implements Comparator<BoardNode> {
        public int compare(BoardNode a, BoardNode b) {
            return a.getMaxCost() - b.getMaxCost();
        }
    }

    public boolean search() {
        //Uniform Cost search which creates a priority queue
        Info info = new Info();
        info.makePQueue(new CostComparator()); //making a priority queue with gComparator
        BoardNode node = initialNode;
        info.pQueue.add(node); //Insert the start state into the queue

        while (!(info.pQueue.isEmpty())) {
            node = info.pQueue.poll();
            info.incTime();
            info.visited.put(node.hashCode(), node);
            if (node.isGaol()) {
                PathActions p = new PathActions(initialNode, node, info); // class that creates a path from goal to start Node if goal is reached
//                p.printPath();
                return true;
            }

            Successor s = new Successor(); // Successor class created to provide next possible moves from current node
            List<BoardNode> fringe = s.successor(node); // list of potential children

            for (BoardNode temp: fringe) {
                boolean ans = info.visited.containsKey(temp.hashCode()); //Uses temporary node's hashCode to check if it has been expanded or not
                if (!ans) { //if it hasn't been expanded then we can now check if there is a node in the Priority Queue with a higher Cost
                    if (!(info.pQueue.contains(temp))){
                        info.pQueue.add(temp);
                        info.pQueueSize();
                    }
                }
            }
        }
        return false;
    }
}
