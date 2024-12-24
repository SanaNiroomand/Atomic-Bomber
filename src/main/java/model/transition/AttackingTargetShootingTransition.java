package model.transition;

import controller.ApplicationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Game;
import model.Plane;
import model.bomb.Bomb;

import java.util.Objects;

public class AttackingTargetShootingTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Pane pane = game.pane;
    private final Plane plane = game.plane;
    private final Bomb bomb;
    private final double hSpeed;
    private final double vSpeed;
    private final double attackingTargetX;
    private final double attackingTargetY;


    public AttackingTargetShootingTransition(Bomb bomb, double planeX, double planeY, double attackingTargetX, double attackingTargetY, double planeScaleX) {
        this.bomb = bomb;
        this.attackingTargetX = attackingTargetX;
        this.attackingTargetY = attackingTargetY;
        vSpeed = planeY - attackingTargetY;
        if (planeScaleX == -1) {
            hSpeed = planeX - 50 - attackingTargetX;
        } else {
            hSpeed = planeX + 50 - attackingTargetX;
        }
        if (planeX < attackingTargetX) {
            bomb.setScaleX(-1);
        }
        setCycleDuration(Duration.seconds(0.7));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        bomb.setX(attackingTargetX + (hSpeed * v));
        bomb.setY(attackingTargetY + (vSpeed * v));

        if (bomb.getBoundsInParent().intersects(plane.getBoundsInParent())) {
            plane.setHit(false);
            pane.getChildren().remove(bomb);

            game.decreaseHitpoint();
            Media media = new Media(
                    Objects.requireNonNull(getClass().getResource("/Media/explosion.wav")).toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            Timeline changePlaneOpacity = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                if (plane.getOpacity() == 1) {
                    plane.setOpacity(0.2);
                } else plane.setOpacity(1);
            }));
            changePlaneOpacity.setCycleCount(6);
            changePlaneOpacity.play();

            this.stop();

        }
        if (v == 1 || bomb.getY() <= 0 || bomb.getX() <= 0 || bomb.getX() >= ApplicationController.WIDTH) {
            plane.setHit(false);
            pane.getChildren().remove(bomb);
        }
    }
}
