package model.transition;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.Plane;

import java.util.Objects;

public class PlaneExplosionTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Pane pane = game.pane;
    private final Plane plane = game.plane;

    public PlaneExplosionTransition() {
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
    }

    @Override
    protected void interpolate(double v) {
        int number;
        if (0 <= v && v <= 0.33) number = 1;
        else if (0.33 < v && v <= 0.66) number = 2;
        else if (0.66 < v && v <= 1) number = 3;
        else number = 1;

        plane.setFill(new ImagePattern(new Image(
                Objects.requireNonNull(TargetTransition.class.getResource("/Images/fire" + number + ".png")).toExternalForm()
        )));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(plane);
                plane.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {

                    }
                });
                game.setWinState(false);
                game.waveController.endGame();
            }
        });
    }
}
