package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;
import view.GameLauncher;
import view.GameSettings;
import view.ProfileMenu;

import java.util.Objects;

public class MainMenuController {
    public ImageView avatar;
    public Label label;
    private final User loggedInUser = User.loggedInUser;

    @FXML
    public void initialize() {
        avatar.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar" + loggedInUser.getAvatarNumber() + ".png")).toExternalForm()));
        label.setText(loggedInUser.getUsername());
    }

    public void launchGame(MouseEvent mouseEvent) {
        new GameLauncher().start(ApplicationController.getStage());
    }

    public void launchProfileMenu(MouseEvent mouseEvent) {
        new ProfileMenu().start(ApplicationController.getStage());
    }

    public void launchGameSettings(MouseEvent mouseEvent) {
        new GameSettings().start(ApplicationController.getStage());
    }
}
