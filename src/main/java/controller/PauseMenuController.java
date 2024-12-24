package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.User;
import view.MainMenu;
import view.ProfileMenu;
import view.RegisterMenu;

import java.io.IOException;
import java.util.Objects;

public class PauseMenuController {
    public RadioButton mute;
    public RadioButton musicOne;
    public RadioButton musicTwo;
    public RadioButton musicThree;

    public void exitGame(MouseEvent mouseEvent) {
        if (User.loggedInUser == null) {
            new MainMenu().start(ApplicationController.getStage());
        } else {
            new ProfileMenu().start(ApplicationController.getStage());
        }
        ApplicationController.getPauseStage().close();
        ApplicationController.setPauseStage(null);
    }

    public void showHelp(MouseEvent mouseEvent) {
        Pane pane;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(RegisterMenu.class.getResource("/FXML/Help.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ApplicationController.getPauseStage().setScene(new Scene(pane));
    }

    public void changeMusic(ActionEvent actionEvent) {
        Node clickedNode = (Node) actionEvent.getSource();
        if (clickedNode == mute) {
            MusicPlayer.pauseMusic();
        } else if (clickedNode == musicOne) {
            MusicPlayer.pauseMusic();
            MusicPlayer.removeMusic();
            MusicPlayer.playMusic(1);
        } else if (clickedNode == musicTwo) {
            MusicPlayer.pauseMusic();
            MusicPlayer.removeMusic();
            MusicPlayer.playMusic(2);
        } else if (clickedNode == musicThree) {
            MusicPlayer.pauseMusic();
            MusicPlayer.removeMusic();
            MusicPlayer.playMusic(3);
        }
    }

    public void helpBack(MouseEvent mouseEvent) {
        Pane pane;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(RegisterMenu.class.getResource("/FXML/PauseMenu.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ApplicationController.getPauseStage().setScene(new Scene(pane));
    }
}
