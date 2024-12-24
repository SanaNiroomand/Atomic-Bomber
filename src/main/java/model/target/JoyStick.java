package model.target;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Game;
import model.Plane;

import java.util.Objects;

public class JoyStick extends Circle {
    public JoyStick(double x, double y) {
        super(Game.getJoyStickRadius());
        setCenterX(x);
        setCenterY(y);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/joystick.png")).toExternalForm())));
        setOpacity(0.5);
    }
}
