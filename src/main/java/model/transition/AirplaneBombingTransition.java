package model.transition;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Plane;
import model.bomb.Bomb;
import model.Game;
import model.target.*;
import model.target.attacking.AttackingTarget;

import java.util.Objects;

public class AirplaneBombingTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Pane pane = game.pane;
    private final Bomb bomb;
    private double vSpeed = 0;
    private double hSpeed = 0.3;

    public AirplaneBombingTransition(Bomb bomb) {
        this.bomb = bomb;
        Plane plane = game.plane;
        if (plane.getScaleX() == -1) {
            hSpeed = -1 * hSpeed;
            bomb.setScaleX(-1);
        }
        int duration = 200;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double x, y;

        y = bomb.getY() + vSpeed;
        x = bomb.getX() + hSpeed;
        double acceleration = 0.002;
        vSpeed += acceleration;
        for (Node child : game.targets.getChildren()) {
            Target target = (Target) child;
            if (bomb.getBoundsInParent().intersects(target.getBoundsInParent())) {
                if (target.isHit()) continue;
                game.increaseSuccessfulShoots();
                target.hit();
                Media media = new Media(Objects.requireNonNull(getClass().getResource("/Media/explosion.wav")).toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                game.increaseScore(target.getScore());
                if (target.getTransition() != null)
                    target.getTransition().stop();

                if (target instanceof Bunker || target instanceof Building) {
                    BonusTransition bonusTransition = new BonusTransition(target);
                    bonusTransition.play();
                }

                if (target instanceof AttackingTarget attackingTarget) {
                    pane.getChildren().remove(attackingTarget.joyStick);
                }

                TargetExplosionTransition targetExplosionTransition = new TargetExplosionTransition(target, bomb);
                targetExplosionTransition.play();


                if (target instanceof Tank || target instanceof Truck || target instanceof Building) {
                    game.increaseIce();
                }

                pane.getChildren().remove(bomb);
                this.stop();

                break;
            }
        }

        if (y >= ApplicationController.HEIGHT) {
            pane.getChildren().remove(bomb);
            this.stop();
        }

        bomb.setX(x);
        bomb.setY(y);
    }
}
