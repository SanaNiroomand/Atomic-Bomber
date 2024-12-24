package controller;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.util.Random;

public class ApplicationController {
    private static Stage stage;
    private static Stage pauseStage;
    private static Stage endStage;
    public static final Random random = new Random();
    public static FileWriter fileWriter = null;
    public static Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public static Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    public static void setStage(Stage stage) {
        ApplicationController.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setPauseStage(Stage pauseStage) {
        ApplicationController.pauseStage = pauseStage;
    }

    public static Stage getPauseStage() {
        return pauseStage;
    }

    public static void setEndStage(Stage endStage) {
        ApplicationController.endStage = endStage;
    }

    public static Stage getEndStage() {
        return endStage;
    }

    public static void showErrorAlert(String message) {
        errorAlert.setContentText(message);
        errorAlert.show();
    }

    public static void showInformationAlert(String message) {
        informationAlert.setContentText(message);
        informationAlert.show();
    }

    public static void setPaneSize(Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }
}
