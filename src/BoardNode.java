import java.util.ArrayList;
import java.util.List;

public class BoardNode {

    private int[][] state;
    private final int[][] goal = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private List<BoardNode> children;
    private BoardNode parent;
    private int depth;
    private int blankRow;
    private int blankCol;
    private Directions direction;
    private String stringState;
    private int cost;
    private int maxCost;

    public BoardNode(int[][] state) {
        this.state = state; // the state
        this.depth = 1; // the depth
        this.children = new ArrayList<BoardNode>(); //the children of the node
        this.parent = null;
        this.cost = 0;
        this.maxCost = 0;
        this.stringState = stringBoard();
        this.direction = null;
        //Here we find zero tile
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    this.blankRow = i;
                    this.blankCol = j;
                    break;
                }
            }
        }
    }

    public String stringBoard() { //method that returns a String version of the board
        StringBuilder sb = new StringBuilder();
        for (int[] ints : state) {
            for (int anInt : ints) {
                sb.append(anInt);
            }
        }
        return sb.toString();
    }

    public void setParent(BoardNode parent) {
        this.parent = parent;
    }

    public BoardNode getParent() {  //getting the Parent of the node
        return parent;
    }

    public void setDepth(int depth) {  //setting the Depth of the node
        this.depth = depth;
    }

    public int getDepth() {  //getting the Depth of the node
        return depth;
    }

    public int getRowBlank() {  //getting the Row of the zero tile
        return blankRow;
    }

    public int getColBlank() { //getting the Column of the zero tile
        return blankCol;
    }

    public int[][] getMatrix(){ //getting the state in array form
        return state;
    }

    public void setCost(int i) { this.cost = i; }

    public int getCost() { //getting the cost of last move
        return this.cost;
    }

    public void setDir(Directions d) {				//setting the Direction moved
        this.direction = d;
    }

    public Directions getDir() {				//getting the direction moved
        return direction;
    }

    //creating the child or possible states from current node
    public BoardNode createChild(int row, int col) {
        int[][] temp = new int[state.length][state.length];
        for (int i = 0; i < state.length; i++) //copy state in temp
            System.arraycopy(state[i], 0, temp[i], 0, state[i].length);
        temp[blankRow][blankCol] = temp[row][col];
        int cost = state[row][col];
        temp[row][col] = 0;
        BoardNode child = new BoardNode(temp);
        child.setCost(cost); //adding to Child to parent
        addChild(child);
        return child;
    }

    public void addChild(BoardNode child) { //adding a Child to the node
        child.setParent(this);
        child.setDepth(this.getDepth() + 1);
        child.setMaxCost(child.getCost());
        this.children.add(child);
    }

    public boolean isGaol() { //checking if node is goal node
        boolean result;
        BoardNode goalNode = new BoardNode(goal);
        result = this.equals(goalNode);
        return result;
    }

    @Override
    public boolean equals(Object object) { //equals for HashMap
        if (!(object instanceof BoardNode)) {
            return false;
        }
        BoardNode check = (BoardNode) object;
        return check.getString().equals(this.getString());
    }

    @Override
    public int hashCode() {	//Hashcode generated from String version of board
        int result = 19;
        result = 29 * result + this.getString().hashCode();
        return result;
    }

    public String getString() {			//getting String version of Board
        return stringState;
    }

    public void setMaxCost(int i) {
        this.maxCost = this.getParent().getMaxCost() + i;
    }

    public int getMaxCost() { //getting the current MaxCode to get to current Node
        return maxCost;
    }

    public int getRow(int value) { //getting the Row of a value in goalState
        int row = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goal[i][j] == value) {
                    row = i;
                }
            }
        }
        return row;
    }

    public int getCol(int value) { //getting the Column of value in goal state used for Manhattan computation
        int col = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goal[i][j] == value) {
                    col = j;
                }
            }
        }
        return col;
    }

}
