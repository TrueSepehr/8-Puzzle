import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ViewController implements Initializable {

    private final int PUZZLE_SIZE = 3;
    private final String[] ALGORITHMS = {"Uniform Cost", "A*"};
    private final int[][] goalState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private final int[][] testCase1 = {{1, 2, 5}, {3, 4, 0}, {6, 7, 8}};
    private final int[][] testCase2 = {{3, 1, 2}, {0, 4, 5}, {6, 7, 8}};
    private final int[][] testCase3 = {{3, 1, 2}, {4, 5, 0}, {6, 7, 8}};
    private final int[][] testCase4 = {{1, 2, 5}, {0, 3, 4}, {6, 7, 8}};
    private BoardNode initialNode;
    int[][] randomCase = new int[PUZZLE_SIZE][PUZZLE_SIZE];

    @FXML Button startBTN;
    @FXML Button stepsBTN;
    @FXML Button backBTN;
    @FXML Button closeBTN;
    @FXML Label timeLBL;
    @FXML Label spaceLBL;
    @FXML Label depthLBL;
    @FXML BorderPane startPANE;
    @FXML BorderPane resultPANE;
    @FXML StackPane stackPANE;
    @FXML ChoiceBox<String> algorithmsBOX;
    @FXML ChoiceBox<String> casesBOX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //UI: set algorithms in choice box
        ObservableList<String> algorithms = FXCollections.observableArrayList();
        algorithms.add(ALGORITHMS[0]);
        algorithms.add(ALGORITHMS[1]);
        algorithmsBOX.setItems(algorithms);
        algorithmsBOX.setStyle("-fx-font-size : 12pt;");

        //UI: set test cases in choice box
        ObservableList<String> testCases = FXCollections.observableArrayList();
        testCases.add(Arrays.deepToString(testCase1));
        testCases.add(Arrays.deepToString(testCase2));
        testCases.add(Arrays.deepToString(testCase3));
        testCases.add(Arrays.deepToString(testCase4));
        testCases.add("Random Case");
        casesBOX.setItems(testCases);
        casesBOX.setStyle("-fx-font-size : 11pt;");

        startBTN.setOnAction(event -> {
            initialNode = new BoardNode(getCase(casesBOX.getSelectionModel().getSelectedItem()));
            Search search;
            if (algorithmsBOX.getSelectionModel().getSelectedItem().equals(ALGORITHMS[0])) {
                search = new UniformCost(initialNode);
            } else {
                search = new AStar(initialNode, 1);
            }
            search.search();
            timeLBL.setText(String.valueOf(PathActions.info.getTime()));
            spaceLBL.setText(String.valueOf(PathActions.info.getSpace()));
            depthLBL.setText(String.valueOf(PathActions.path.get(0).getDepth()));
            changeTop();
        });

        stepsBTN.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(ViewController.class.getResource("view/Steps.fxml")));
            try {
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("8-Puzzle: Steps");
                stage.getIcons().add(new Image("https://img.icons8.com/material/48/000000/puzzle--v1.png"));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backBTN.setOnAction(event -> changeTop());

        closeBTN.setOnAction(event -> Platform.exit());
    }

    private void changeTop() {
        ObservableList<Node> children = this.stackPANE.getChildren();
        if (children.size() > 1) {
            Node topNode = children.get(children.size()-1);
            //this node will be brought to the front
            Node newTopNode = children.get(children.size()-2);
            topNode.setVisible(false);
            topNode.toBack();
            newTopNode.setVisible(true);
        }
    }

    private int[][] getCase(String s) {
        if (s.equals(Arrays.deepToString(testCase1))) return testCase1;
        else if (s.equals(Arrays.deepToString(testCase2))) return testCase2;
        else if (s.equals(Arrays.deepToString(testCase3))) return testCase3;
        else return testCase4;
    }
}
