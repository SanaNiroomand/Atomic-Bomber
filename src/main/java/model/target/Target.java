package model.target;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.target.attacking.Mig;

abstract public class Target extends Rectangle {

    public double WIDTH;
    public double HEIGHT;
    protected int score;
    private Transition transition = null;
    private boolean isHit = false;

    public Target(int i, int i1) {
        super(i, i1);
        if (this instanceof Mig) {
            setY(120);
        } else {
            setY(ApplicationController.HEIGHT - 100);
        }
        setX();
    }

    public void setX() {
        boolean collision;
        do {
            collision = false;
            setX(ApplicationController.random.nextDouble(100, ApplicationController.WIDTH - WIDTH - 100));
            for (Node child : Game.currentGame.targets.getChildren()) {
                if (collides(child, this)) collision = true;
            }
        } while (collision);
    }

    private boolean collides(Node first, Node second) {
        if ((second instanceof Tree | second instanceof Bunker | second instanceof Building)
                && (first instanceof Tank | first instanceof Bunker)) {
            return false;
        } else if ((second instanceof Tank | second instanceof Bunker)
                && (first instanceof Tree | first instanceof Bunker | first instanceof Building)) {
            return false;
        }
        return first.getBoundsInParent().intersects(second.getBoundsInParent());
    }

    public int getScore() {
        return score;
    }

    public void hit() {
        isHit = true;
    }

    public boolean isHit() {
        return isHit;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }
}
