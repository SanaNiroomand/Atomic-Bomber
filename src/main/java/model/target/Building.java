package model.target;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Plane;

import java.util.Objects;

public class Building extends Target {

    public Building() {
        super(96, 80);
        WIDTH = 96;
        HEIGHT = 80;
        score = 15;
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/building.png")).toExternalForm())));
    }
}
