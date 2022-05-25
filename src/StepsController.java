import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class StepsController implements Initializable {

    @FXML Button BTN0;
    @FXML Button BTN1;
    @FXML Button BTN2;
    @FXML Button BTN3;
    @FXML Button BTN4;
    @FXML Button BTN5;
    @FXML Button BTN6;
    @FXML Button BTN7;
    @FXML Button BTN8;
    @FXML Button backwardBTN;
    @FXML Button forwardBTN;

    private int[][] arrState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<BoardNode> path = PathActions.path;
        Collections.reverse(path);
        ListIterator<BoardNode> iterator = path.listIterator();
        BoardNode initialState = iterator.next();
        arrState = initialState.getMatrix();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button button = getBTN(x, y);
                button.setText(String.valueOf(arrState[x][y]));
                if (arrState[x][y] == 0) button.setText("");
            }
        }

        forwardBTN.setOnAction(event -> {
            if (iterator.hasNext()) {
                arrState = iterator.next().getMatrix();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        Button button = getBTN(x, y);
                        button.setText(String.valueOf(arrState[x][y]));
                        if (arrState[x][y] == 0) button.setText("");
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("That's it!");
                alert.show();
            }
        });

        backwardBTN.setOnAction(event -> {
            if (iterator.hasPrevious()) {
                arrState = iterator.previous().getMatrix();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        Button button = getBTN(x, y);
                        button.setText(String.valueOf(arrState[x][y]));
                        if (arrState[x][y] == 0) button.setText("");
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("We are in the initial state!");
                alert.show();
            }
        });
    }

    private Button getBTN(int x, int y) { //get zero tile
        if (x == 0) {
            if (y == 0) return BTN0;
            else if (y == 1) return BTN1;
            else return BTN2;
        } else if (x == 1) {
            if (y == 0) return BTN3;
            else if (y == 1) return BTN4;
            else return BTN5;
        } else {
            if (y == 0) return BTN6;
            else if (y == 1) return BTN7;
            else return BTN8;
        }
    }
}
