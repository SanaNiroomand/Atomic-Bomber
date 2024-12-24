package model.target.attacking;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Plane;

import java.util.Objects;

public class AttackingTank extends AttackingTarget {
    public AttackingTank() {
        super(64, 34);
        WIDTH = 64;
        HEIGHT = 34;
        score = 15;
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/zsu57.png")).toExternalForm())));
    }
}
