package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;
import view.ProfileMenu;

import java.util.Objects;

public class AvatarMenuController {
    public ImageView avatar1;
    public ImageView avatar2;
    public ImageView avatar3;
    public ImageView avatar4;
    public ImageView currentAvatar;
    public Label username;

    @FXML
    public void initialize() {
        username.setText(User.loggedInUser.getUsername());
        avatar1.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar1.png")).toExternalForm()));
        avatar2.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar2.png")).toExternalForm()));
        avatar3.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar3.png")).toExternalForm()));
        avatar4.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar4.png")).toExternalForm()));
        currentAvatar.setImage(new Image(Objects.requireNonNull(ProfileMenuController.class.getResource(
                "/Images/avatar" + User.loggedInUser.getAvatarNumber() + ".png")).toExternalForm()));
    }

    public void changeAvatar(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        if (clickedNode == avatar1) {
            User.loggedInUser.setAvatarNumber(1);
        } else if (clickedNode == avatar2) {
            User.loggedInUser.setAvatarNumber(2);
        } else if (clickedNode == avatar3) {
            User.loggedInUser.setAvatarNumber(3);
        } else if (clickedNode == avatar4) {
            User.loggedInUser.setAvatarNumber(4);
        }
        initialize();
        ApplicationController.showInformationAlert("Avatar changed successfully.");
    }

    public void back(MouseEvent mouseEvent) {
        new ProfileMenu().start(ApplicationController.getStage());
    }
}
