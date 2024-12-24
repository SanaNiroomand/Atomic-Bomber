package model.transition;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Plane;
import model.bomb.Bomb;
import model.bomb.IronBomb;
import model.target.attacking.AttackingTarget;
import model.target.Target;

public class TargetTransition extends Transition {
    private final Game game = Game.currentGame;
    private final Pane pane = game.pane;
    private final Plane plane = game.plane;
    private final Target target;
    private final double speed;

    public TargetTransition(Target target, double speed) {
        this.target = target;
        this.speed = speed;

        this.setCycleCount(-1);
        int duration = 100;
        this.setCycleDuration(Duration.seconds(duration));
    }

    @Override
    protected void interpolate(double v) {
        if (target.getX() <= ApplicationController.WIDTH) {
            target.setX(target.getX() + speed);
            if (target instanceof AttackingTarget attackingTarget) {
                attackingTarget.joyStick.setCenterX(attackingTarget.getX() + 30);
                checkAndShootAtPlane(attackingTarget);
            }
        } else {
            target.setX(0);
        }
    }

    public void checkAndShootAtPlane(AttackingTarget attackingTarget) {
        double distance = Math.sqrt(Math.pow(plane.getX() - attackingTarget.getX(), 2)
                + Math.pow(plane.getY() - attackingTarget.getY(), 2));
        if (distance <= Game.getJoyStickRadius() && !plane.isHit()) {
            shootPlane(attackingTarget);
        }
    }

    public void shootPlane(AttackingTarget attackingTarget) {
        plane.setHit(true);
        Bomb bomb = new IronBomb();
        bomb.setX(attackingTarget.joyStick.getCenterX());
        bomb.setY(attackingTarget.joyStick.getCenterY());
        AttackingTargetShootingTransition attackingTargetShootingTransition = new
                AttackingTargetShootingTransition(bomb, plane.getX(), plane.getY(),
                attackingTarget.getX(), attackingTarget.getY(), plane.getScaleX());
        pane.getChildren().add(bomb);
        attackingTargetShootingTransition.play();
    }
}
