package model.target.attacking;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class Mig extends AttackingTarget {

    public Mig() {
        super(78, 26);
        WIDTH = 78;
        HEIGHT = 26;
        score = 25;
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Mig.class.getResource("/Images/mig1.png")).toExternalForm())));
    }
}
