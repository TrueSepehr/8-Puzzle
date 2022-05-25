import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/View.fxml")));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("8-PUZZLE");
        primaryStage.getIcons().add(new Image("https://img.icons8.com/material/48/000000/puzzle--v1.png"));
        primaryStage.show();
    }
}
