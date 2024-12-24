package model;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Plane extends Rectangle {
    public final double WIDTH = 54;
    public final double HEIGHT = 18;
    private Transition transition;
    private boolean isHit = false;

    public Plane() {
        super(54, 18);
        setX(100);
        setY((double) ApplicationController.HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/Images/plane.png")).toExternalForm())));
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
