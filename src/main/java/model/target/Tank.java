package model.target;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Plane;

import java.util.Objects;

public class Tank extends Target {

    public Tank() {
        super(64, 32);
        WIDTH = 64;
        HEIGHT = 32;
        score = 5;
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/tank1.png")).toExternalForm())));
    }
}
