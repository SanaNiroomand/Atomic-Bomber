package model.bomb;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.Plane;

import java.util.Objects;

public class RadioactiveBomb extends Bomb {

    public RadioactiveBomb() {
        super(28, 14);
        WIDTH = 28;
        HEIGHT = 14;
        Plane plane = Game.currentGame.plane;
        setX(plane.getX() + (plane.WIDTH - this.WIDTH) / 2);
        setY(plane.getY() + (plane.HEIGHT - this.HEIGHT) + 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/nukebomb.png")).toExternalForm())));
    }
}
