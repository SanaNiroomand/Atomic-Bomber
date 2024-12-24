package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;
import view.AvatarMenu;
import view.MainMenu;
import view.RegisterMenu;

import java.util.Objects;

public class ProfileMenuController {

    public ImageView avatar;
    private final User loggedInUser = User.loggedInUser;
    public Label label;
    public TextField newUsername;
    public TextField newPassword;

    @FXML
    public void initialize() {
        avatar.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar" + loggedInUser.getAvatarNumber() + ".png")).toExternalForm()));
        label.setText(loggedInUser.getUsername());
    }

    public void changeUsername(MouseEvent mouseEvent) {
        if (User.getUserByUsername(newUsername.getText()) != null && !loggedInUser.getUsername().equals(newUsername.getText())) {
            ApplicationController.showErrorAlert("This username already exists.");
        } else {
            loggedInUser.setUsername(newUsername.getText());
            ApplicationController.showInformationAlert("Username changed successfully.");
        }
        newUsername.setText("");
    }

    public void changePassword(MouseEvent mouseEvent) {
        loggedInUser.setPassword(newPassword.getText());
        ApplicationController.showInformationAlert("Password changed successfully.");
        newPassword.setText("");
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        User.deleteUser(loggedInUser);
        User.loggedInUser = null;
        ApplicationController.showInformationAlert("Your account deleted successfully.");
        new RegisterMenu().start(ApplicationController.getStage());
    }

    public void logout(MouseEvent mouseEvent) {
        ApplicationController.showInformationAlert("You logged out successfully.");
        User.loggedInUser = null;
        new RegisterMenu().start(ApplicationController.getStage());
    }

    public void launchAvatarMenu(MouseEvent mouseEvent) {
        new AvatarMenu().start(ApplicationController.getStage());
    }

    public void back(MouseEvent mouseEvent) {
        new MainMenu().start(ApplicationController.getStage());
    }
}
