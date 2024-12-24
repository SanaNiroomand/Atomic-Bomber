package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameSettings extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(RegisterMenu.class.getResource("/FXML/GameSettings.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
