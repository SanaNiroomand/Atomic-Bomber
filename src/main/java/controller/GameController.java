package controller;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.bomb.Bomb;
import model.Game;
import model.Plane;
import model.target.Target;
import model.transition.AirplaneBombingTransition;

public class GameController {
    private final Game game = Game.currentGame;
    private final Pane pane = game.pane;
    private final Plane plane = game.plane;

    public void shoot(Bomb bomb) {
        int planeIndex = pane.getChildren().indexOf(plane);
        pane.getChildren().add(planeIndex, bomb);
        AirplaneBombingTransition airplaneBombingTransition = new AirplaneBombingTransition(bomb);
        airplaneBombingTransition.play();
    }

    public void ice() {
        game.setIced(true);
        for (Node child : game.targets.getChildren()) {
            Target target = (Target) child;
            if (target.getTransition() != null)
                target.getTransition().stop();
        }
        game.resetIce();
    }

    public void deIce() {
        game.setIced(false);
        for (Node child : game.targets.getChildren()) {
            Target target = (Target) child;
            if (target.getTransition() != null)
                target.getTransition().play();
        }
    }
}
