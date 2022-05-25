import java.util.ArrayList;
import java.util.List;

public class Successor {

    public List<BoardNode> successor(BoardNode node) {
        //successor function that takes a state and returns a list of possible states that can be reached
        List<BoardNode> possibleStates = new ArrayList<BoardNode>();
        int row = node.getRowBlank();
        int col = node.getColBlank();

        //up
        if ((col != 0 || col != 1 || col != 2) && (row != 0)){
            BoardNode upNode = node.createChild(row - 1, col);
            upNode.setDir(Directions.UP);
            possibleStates.add(upNode);
        }

        //down
        if ((col != 0 || col != 1 || col != 2) && (row != 2)){
            BoardNode downNode = node.createChild(row + 1, col);
            downNode.setDir(Directions.DOWN);
            possibleStates.add(downNode);
        }

        //right
        if ((row != 0 || row != 1 || row != 2) && (col != 2)){
            BoardNode rightNode = node.createChild(row, col + 1);
            rightNode.setDir(Directions.RIGHT);
            possibleStates.add(rightNode);
        }

        //left
        if ((row != 0 || row != 1 || row != 2) && (col != 0)){
            BoardNode leftNode = node.createChild(row, col - 1);
            leftNode.setDir(Directions.LEFT);
            possibleStates.add(leftNode);
        }

        return possibleStates;  //a list of children is returned

    }
}
