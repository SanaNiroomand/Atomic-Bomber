package view;

import controller.ApplicationController;
import controller.GameController;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Game;
import model.Plane;
import model.bomb.ClusterBomb;
import model.bomb.RadioactiveBomb;
import model.target.*;
import model.bomb.IronBomb;
import model.target.attacking.AttackingTank;
import model.target.attacking.Mig;
import model.transition.PlaneTransition;
import model.transition.TargetTransition;

import java.util.Objects;

public class GameLauncher extends Application {
    public Stage stage;
    public Pane gamePane;
    public Game game;
    public Plane plane;
    private GameController gameController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        gamePane = new Pane();
        ApplicationController.setPaneSize(gamePane);
        setBackground();
        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        initialize();
    }

    public void initialize() {
        game = new Game(this);
        createPlane();
        gamePane.getChildren().add(game.targets);
        game.waveController.checkWaveCompletion();
        gameController = new GameController();
        plane.requestFocus();
    }

    private void setBackground() {
        Image background = new Image(Objects.requireNonNull(Game.class.getResource("/Images/background.png")).toExternalForm(),
                ApplicationController.WIDTH, ApplicationController.HEIGHT, false, false);
        gamePane.setBackground(new Background(new BackgroundImage(background,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }

    private void createPlane() {
        game.plane = new Plane();
        this.plane = game.plane;
        plane.setTransition(new PlaneTransition());
        plane.getTransition().play();
        gamePane.getChildren().add(plane);
        plane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (Game.isArrowKeys()) {
                        if (plane.getY() >= 15)
                            plane.setY(plane.getY() - 10);
                    }
                    break;
                case W:
                    if (!Game.isArrowKeys()) {
                        if (plane.getY() >= 15)
                            plane.setY(plane.getY() - 10);
                    }
                    break;
                case DOWN:
                    if (Game.isArrowKeys()) {
                        if (plane.getY() <= ApplicationController.HEIGHT - plane.HEIGHT - 115)
                            plane.setY(plane.getY() + 10);
                    }
                    break;
                case S:
                    if (!Game.isArrowKeys()) {
                        if (plane.getY() <= ApplicationController.HEIGHT - plane.HEIGHT - 115)
                            plane.setY(plane.getY() + 10);
                    }
                    break;
                case LEFT:
                    if (Game.isArrowKeys()) {
                        plane.setScaleX(-1);
                        if (plane.getX() >= -1 * plane.WIDTH)
                            plane.setX(plane.getX() - 10);
                        else
                            plane.setX(ApplicationController.WIDTH - plane.WIDTH);
                    }
                    break;
                case A:
                    if (!Game.isArrowKeys()) {
                        plane.setScaleX(-1);
                        if (plane.getX() >= -1 * plane.WIDTH)
                            plane.setX(plane.getX() - 10);
                        else
                            plane.setX(ApplicationController.WIDTH - plane.WIDTH);
                    }
                    break;
                case RIGHT:
                    if (Game.isArrowKeys()) {
                        plane.setScaleX(1);
                        if (plane.getX() <= ApplicationController.WIDTH)
                            plane.setX(plane.getX() + 10);
                        else
                            plane.setX(0);
                    }
                    break;
                case D:
                    if (!Game.isArrowKeys()) {
                        plane.setScaleX(1);
                        if (plane.getX() <= ApplicationController.WIDTH)
                            plane.setX(plane.getX() + 10);
                        else
                            plane.setX(0);
                    }
                    break;
                case SPACE:
                    gameController.shoot(new IronBomb());
                    game.increaseShoots();
                    break;
                case R:
                    if (game.getRadioactiveBombs() > 0) {
                        gameController.shoot(new RadioactiveBomb());
                        game.decreaseRadioactiveBombs();
                        game.increaseShoots();
                    }
                    break;
                case C:
                    if (game.getClusterBombs() > 0) {
                        gameController.shoot(new ClusterBomb());
                        game.decreaseClusterBombs();
                        game.increaseShoots();
                    }
                    break;
                case TAB:
                    if (game.isIced()) {
                        gameController.deIce();
                    } else {
                        if (game.getIce() == 1) gameController.ice();
                    }
                    break;
                case P:
                    game.waveController.startNextWave();
                    break;
                case G:
                    game.increaseRadioactiveBombs();
                    break;
                case CONTROL:
                    game.increaseClusterBombs();
                    break;
                case T:
                    createTank();
                    break;
                case H:
                    game.increaseHitpoint();
                    break;
            }
        });
    }

    public void createTank() {
        Tank tank = new Tank();
        tank.setTransition(new TargetTransition(tank, Game.getTankSpeed()));
        tank.getTransition().play();
        game.targets.getChildren().add(tank);
    }

    public void createAttackingTank() {
        AttackingTank attackingTank = new AttackingTank();
        Transition transition = new TargetTransition(attackingTank, 0.1);
        game.targets.getChildren().add(attackingTank);
        attackingTank.setTransition(transition);
        attackingTank.getTransition().play();
    }

    public void createMig() {
        Mig mig = new Mig();
        Transition transition = new TargetTransition(mig, Game.getMigSpeed());
        game.targets.getChildren().add(mig);
        mig.setTransition(transition);
        mig.getTransition().play();
    }

    public void createTruck() {
        Truck truck = new Truck();
        truck.setTransition(new TargetTransition(truck, 0.2));
        truck.getTransition().play();
        game.targets.getChildren().add(truck);
    }

    public void createTree() {
        Tree tree = new Tree();
        game.targets.getChildren().add(tree);
    }

    public void createBunker() {
        Bunker bunker = new Bunker();
        game.targets.getChildren().add(bunker);
    }

    public void createBuilding() {
        Building building = new Building();
        game.targets.getChildren().add(building);
    }
}
