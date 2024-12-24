package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Game;
import model.target.Target;

import java.io.IOException;
import java.util.Objects;

public class PauseMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ApplicationController.setPauseStage(stage);
        stage.setTitle("Pause Menu");
        stage.setResizable(false);
        stage.centerOnScreen();
        Pane pane;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(RegisterMenu.class.getResource("/FXML/PauseMenu.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(pane));
        stage.show();
        stage.setOnCloseRequest(this::handleClose);
    }

    private void handleClose(WindowEvent windowEvent) {
        Game.currentGame.plane.getTransition().play();
        for (Node child : Game.currentGame.targets.getChildren()) {
            Target target = (Target) child;
            if (target.getTransition() != null)
                target.getTransition().play();
        }
    }
}
