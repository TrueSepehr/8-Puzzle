import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathActions {
    // this class provides an object that is used to trace back the path from the goal
    // it then prints the path
    static List<BoardNode> path;
    static Info info; //info object is used in order to print details about space and time

    public PathActions(BoardNode initialNode, BoardNode goalNode, Info info) {  //the arguments are goalNode, info and initialNode so a path can be found.
        path = this.getPath(initialNode, goalNode);
        this.info = info;
    }

    public List<BoardNode> getPath(BoardNode initialNode, BoardNode goalNode) {  //given a goalNode and initialNode this method uses node's parents to trace it's way back up
        BoardNode tempNode = goalNode;
        List<BoardNode> list = new ArrayList<BoardNode>();

        while(!(tempNode.equals(initialNode))) {
            list.add(tempNode);
            tempNode = tempNode.getParent();
        }
        list.add(initialNode);
        return list;  // a list of the path is returned in reverse order
    }

    public void printPath() {
        //this method enables us to print the path in correct order from start node to goal node with sufficient details.
        for (int i = path.size() - 1; i >= 0; i--){
            System.out.println("Direction Moved: " + path.get(i).getDir());
            System.out.println("Depth: " + path.get(i).getDepth());
            System.out.println("Cost: " + path.get(i).getCost());
            System.out.println("MaxCost: " + path.get(i).getMaxCost());
            System.out.println();
            System.out.println("Current Node: \n");
            System.out.println(Arrays.deepToString(path.get(i).getMatrix()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            System.out.println("---------------------------");
        }
        System.out.println();
        System.out.println("results: ");
        System.out.println("Time: " + info.getTime());
        System.out.println("Space: " + info.getSpace());
    }
}
