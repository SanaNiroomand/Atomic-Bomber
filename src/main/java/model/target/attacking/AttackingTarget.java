package model.target.attacking;

import model.Game;
import model.target.JoyStick;
import model.target.Target;

abstract public class AttackingTarget extends Target {
    public JoyStick joyStick;

    public AttackingTarget(int i, int i1) {
        super(i, i1);
        joyStick = new JoyStick(getX() + WIDTH / 2 + 30, getY() + HEIGHT / 2 + 20);
        Game.currentGame.pane.getChildren().add(joyStick);
    }
}
