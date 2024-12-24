package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;
import view.GameLauncher;
import view.MainMenu;

public class RegisterMenuController {
    public TextField username;
    public PasswordField password;

    public void register(MouseEvent mouseEvent) {
        if (User.getUserByUsername(username.getText()) != null) {
            ApplicationController.showErrorAlert("This username already exists.");
        } else {
            User user = new User(username.getText(), password.getText());
            launchMainMenu(user);
        }
    }

    public void login(MouseEvent mouseEvent) {
        User user = User.getUserByUsername(username.getText());
        if (user == null) {
            ApplicationController.showErrorAlert("This username doesn't exist.");
        } else if (!user.getPassword().equals(password.getText())) {
            ApplicationController.showErrorAlert("Password is wrong.");
        } else {
            launchMainMenu(user);
        }
    }

    public void guest(MouseEvent mouseEvent) {
        new GameLauncher().start(ApplicationController.getStage());
    }

    public void launchMainMenu(User user) {
        User.loggedInUser = user;
        new MainMenu().start(ApplicationController.getStage());
    }
}
