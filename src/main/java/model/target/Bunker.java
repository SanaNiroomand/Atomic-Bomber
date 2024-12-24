package model.target;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Plane;

import java.util.Objects;

public class Bunker extends Target {
    public Bunker() {
        super(96, 80);
        WIDTH = 96;
        HEIGHT = 80;
        score = 20;
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/bunker1.png")).toExternalForm())));
    }
}
