package model.target;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Plane;

import java.util.Objects;

public class Tree extends Target {
    public Tree() {
        super(48, 70);
        WIDTH = 48;
        HEIGHT = 70;
        score = 0;
        int number = ApplicationController.random.nextInt(1, 4);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/tree" + number + ".png")).toExternalForm())));
    }
}
