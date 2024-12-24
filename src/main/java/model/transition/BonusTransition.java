package model.transition;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game;
import model.Plane;
import model.target.Building;
import model.target.Bunker;
import model.target.Target;

import java.util.Objects;

public class BonusTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Pane pane = game.pane;
    private final Plane plane = game.plane;
    private final Target target;
    private final Rectangle bonus;

    public BonusTransition(Target target) {
        this.target = target;
        int duration = 5;
        this.setCycleDuration(Duration.seconds(duration));
        this.setCycleCount(-1);

        bonus = new Rectangle(50, 50);
        if (target instanceof Building) {
            bonus.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/Images/bonusnuke.png")).toExternalForm())));
        } else {
            bonus.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/Images/bonuscluster.png")).toExternalForm())));
        }

        bonus.setX(target.getX());
        bonus.setY(target.getY());
        pane.getChildren().add(bonus);
    }

    @Override
    protected void interpolate(double v) {
        double speed = 0.3;
        bonus.setY(bonus.getY() - speed);

        if (plane.getBoundsInParent().intersects(bonus.getBoundsInParent())) {
            if (target instanceof Building) {
                game.increaseRadioactiveBombs();
            } else {
                game.increaseClusterBombs();
            }
            pane.getChildren().remove(bonus);
            this.stop();
        }

        if (bonus.getY() <= 0) {
            pane.getChildren().remove(bonus);
            this.stop();
        }
    }
}