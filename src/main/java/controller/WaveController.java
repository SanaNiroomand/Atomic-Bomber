package controller;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Game;
import model.User;
import model.target.attacking.AttackingTarget;
import model.target.Target;
import view.End;

public class WaveController {
    private final Game game = Game.currentGame;
    private int currentWave = 0;

    public int getCurrentWave() {
        return currentWave;
    }

    public void startNextWave() {
        currentWave++;
        if (currentWave <= 3) game.updateWaveLabel();
        clearExistingTargets();
        switch (currentWave) {
            case 1:
                setupWave1();
                break;
            case 2:
                setupWave2();
                break;
            case 3:
                setupWave3();
                break;
            default:
                endGame();
                break;
        }
    }

    private void setupWave1() {
        game.gameLauncher.createTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTruck();
        game.gameLauncher.createTruck();
        game.gameLauncher.createBuilding();
        game.gameLauncher.createBunker();
        game.gameLauncher.createTree();
        game.gameLauncher.createTree();
        game.gameLauncher.createTree();
        game.gameLauncher.createTree();
    }

    private void setupWave2() {
        game.gameLauncher.createAttackingTank();
        game.gameLauncher.createAttackingTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTruck();
        game.gameLauncher.createTruck();
        game.gameLauncher.createBuilding();
        game.gameLauncher.createBunker();
    }

    private void setupWave3() {
        game.plane.getTransition().stop();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Mig Warning! Get Low!", ButtonType.OK);
        alert.showAndWait();
        game.plane.getTransition().play();

        game.gameLauncher.createMig();
        game.gameLauncher.createTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTank();
        game.gameLauncher.createTruck();
        game.gameLauncher.createTruck();
        game.gameLauncher.createBuilding();
        game.gameLauncher.createBunker();
    }

    private void clearExistingTargets() {
        for (Node child : game.targets.getChildren()) {
            Target target = (Target) child;
            if (target.getTransition() != null) {
                target.getTransition().stop();
            }
            if (target instanceof AttackingTarget attackingTarget) {
                game.pane.getChildren().remove(attackingTarget.joyStick);
            }
        }
        game.targets.getChildren().clear();
    }

    public void checkWaveCompletion() {
        if (game.targets.getChildren().isEmpty()) {
            startNextWave();
        }
    }

    public void endGame() {
        game.plane.getTransition().stop();
        for (Node child : game.targets.getChildren()) {
            Target target = (Target) child;
            if (target.getTransition() != null)
                target.getTransition().stop();
        }
        if (User.loggedInUser != null) {
            User.loggedInUser.checkAndSetTopScore(game.getScore());
        }
        new End().start(new Stage());
    }
}
