package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import model.Game;
import model.Level;
import view.MainMenu;

public class GameSettingsController {
    public RadioButton arrow;
    public RadioButton asdw;
    public RadioButton easy;
    public RadioButton normal;
    public RadioButton hard;

    public void changeControlKeys(ActionEvent actionEvent) {
        Node clickedNode = (Node) actionEvent.getSource();
        if (clickedNode == arrow) {
            Game.setArrowKeys(true);
        } else if (clickedNode == asdw) {
            Game.setArrowKeys(false);
        }
    }

    public void changeGameLevel(ActionEvent actionEvent) {
        Node clickedNode = (Node) actionEvent.getSource();
        if (clickedNode == easy) {
            Game.setLevel(Level.EASY);
        } else if (clickedNode == normal) {
            Game.setLevel(Level.NORMAL);
        } else if (clickedNode == hard) {
            Game.setLevel(Level.HARD);
        }
    }

    public void back(MouseEvent mouseEvent) {
        new MainMenu().start(ApplicationController.getStage());
    }

    public void mute(MouseEvent mouseEvent) {
        MusicPlayer.pauseMusic();
    }
}
