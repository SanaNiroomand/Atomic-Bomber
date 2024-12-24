package model.transition;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.util.Duration;
import model.Game;
import model.Plane;

public class PlaneTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Plane plane = game.plane;

    public PlaneTransition() {
        this.setCycleCount(-1);
        int duration = 100;
        this.setCycleDuration(Duration.seconds(duration));
    }

    @Override
    protected void interpolate(double v) {
        if (game.getHitpoint() == 0) {
            this.stop();
            PlaneExplosionTransition planeExplosionTransition = new PlaneExplosionTransition();
            planeExplosionTransition.play();
        }
        if (plane.getScaleX() == 1) {
            if (plane.getX() <= ApplicationController.WIDTH)
                plane.setX(plane.getX() + 0.2);
            else
                plane.setX(0);
        } else {
            if (plane.getX() >= 0)
                plane.setX(plane.getX() - 0.2);
            else
                plane.setX(ApplicationController.WIDTH);
        }
    }
}
