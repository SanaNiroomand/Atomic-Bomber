package model.bomb;

import javafx.scene.shape.Rectangle;

abstract public class Bomb extends Rectangle {
    public double WIDTH;
    public double HEIGHT;

    public Bomb(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
    }
}
