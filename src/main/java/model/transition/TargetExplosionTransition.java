package model.transition;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.bomb.Bomb;
import model.bomb.ClusterBomb;
import model.bomb.IronBomb;
import model.bomb.RadioactiveBomb;
import model.target.Tank;
import model.target.Target;

import java.util.Objects;

public class TargetExplosionTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Target target;
    private final Bomb bomb;


    public TargetExplosionTransition(Target target, Bomb bomb) {
        this.target = target;
        this.bomb = bomb;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        int number;
        if (0 <= v && v <= 0.33) number = 1;
        else if (0.33 < v && v <= 0.66) number = 2;
        else if (0.66 < v && v <= 1) number = 3;
        else number = 1;

        if (bomb instanceof IronBomb) {
            if (target instanceof Tank) {
                target.setFill(new ImagePattern(new Image(
                        Objects.requireNonNull(TargetTransition.class.getResource("/Images/deadtank" + number + ".png")).toExternalForm()
                )));
            } else {
                target.setFill(new ImagePattern(new Image(
                        Objects.requireNonNull(TargetTransition.class.getResource("/Images/fire" + number + ".png")).toExternalForm()
                )));
            }
        } else if (bomb instanceof ClusterBomb) {
            target.setFill(new ImagePattern(new Image(
                    Objects.requireNonNull(TargetTransition.class.getResource("/Images/blast" + number + ".png")).toExternalForm()
            )));
        } else if (bomb instanceof RadioactiveBomb) {
            target.setFill(new ImagePattern(new Image(
                    Objects.requireNonNull(TargetTransition.class.getResource("/Images/nuketop" + number + ".png")).toExternalForm()
            )));
        }

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.targets.getChildren().remove(target);
                game.waveController.checkWaveCompletion();
            }
        });
    }
}
