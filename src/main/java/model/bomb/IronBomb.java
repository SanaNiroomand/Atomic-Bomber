package model.bomb;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.Plane;

import java.util.Objects;

public class IronBomb extends Bomb {

    public IronBomb() {
        super(22, 10);
        WIDTH = 22;
        HEIGHT = 10;
        Plane plane = Game.currentGame.plane;
        setX(plane.getX() + (plane.WIDTH - this.WIDTH) / 2);
        setY(plane.getY() + (plane.HEIGHT - this.HEIGHT) + 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/ironbomb.png")).toExternalForm())));
    }
}
