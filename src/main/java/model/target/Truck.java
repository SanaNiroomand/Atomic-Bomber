package model.target;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Plane;

import java.util.Objects;

public class Truck extends Target {
    public Truck() {
        super(64, 34);
        WIDTH = 64;
        HEIGHT = 34;
        score = 10;
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/truck.png")).toExternalForm())));
    }
}
