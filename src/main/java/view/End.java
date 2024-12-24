package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Game;
import model.User;

import java.io.IOException;
import java.util.Objects;

public class End extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;
    public Label kills = new Label();
    public Label winState = new Label();
    public Label accuracy = new Label();
    private final Game game = Game.currentGame;
    public AnchorPane rootPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ApplicationController.setEndStage(stage);
        Pane pane;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/End.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void initialize() {
        rootPane.getChildren().addAll(kills, winState, accuracy);

        kills.setLayoutX(180);
        winState.setLayoutX(180);
        accuracy.setLayoutX(180);
        kills.setLayoutY(150);
        winState.setLayoutY(200);
        accuracy.setLayoutY(250);

        kills.setTextFill(Color.WHITE);
        kills.setFont(new Font("Arial", 22));
        winState.setTextFill(Color.WHITE);
        winState.setFont(new Font("Arial", 22));
        accuracy.setTextFill(Color.WHITE);
        accuracy.setFont(new Font("Arial", 22));

        kills.setAlignment(Pos.CENTER);
        winState.setAlignment(Pos.CENTER);
        accuracy.setAlignment(Pos.CENTER);
        kills.setTextAlignment(TextAlignment.CENTER);
        winState.setTextAlignment(TextAlignment.CENTER);
        accuracy.setTextAlignment(TextAlignment.CENTER);

        kills.setText("Kills : " + game.getScore());
        if (game.getWinState()) winState.setText("You Won!");
        else winState.setText("You Lost");
        double accuracyNumber = ((double) game.getSuccessfulShoots()) / ((double) game.getShoots()) * 100;
        accuracy.setText(String.format("Accuracy : %.2f%%", accuracyNumber));
    }

    public void back(MouseEvent mouseEvent) {
        ApplicationController.getEndStage().close();
        ApplicationController.setEndStage(null);
        if (User.loggedInUser == null) {
            new RegisterMenu().start(ApplicationController.getStage());
        } else {
            new MainMenu().start(ApplicationController.getStage());
        }
    }
}
