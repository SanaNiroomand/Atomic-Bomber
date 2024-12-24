package view;

import controller.ApplicationController;
import controller.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.Objects;

public class RegisterMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/icon.png")));
        stage.getIcons().add(icon);
        ApplicationController.setStage(stage);
        stage.setTitle("Atomic Bomber");
        stage.setResizable(false);
        stage.centerOnScreen();
        User.loadUsers();
        Pane pane;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(RegisterMenu.class.getResource("/FXML/RegisterMenu.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(pane));
        stage.show();
        playMusic();
    }

    public void playMusic() {
        int musicNumber = ApplicationController.random.nextInt(1, 4);
        MusicPlayer.playMusic(musicNumber);
    }
}
