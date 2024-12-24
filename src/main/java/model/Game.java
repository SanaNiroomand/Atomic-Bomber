package model;

import controller.ApplicationController;
import controller.WaveController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.target.Target;
import view.GameLauncher;
import view.PauseMenu;

import java.util.Objects;

public class Game {
    public static Game currentGame;
    public Pane pane;
    public GameLauncher gameLauncher;
    public WaveController waveController;
    public Plane plane;
    public final Group targets = new Group();
    private int score = 0;
    private int radioactiveBombs = 0;
    private int clusterBombs = 0;
    private double ice = 0;
    private boolean isIced = false;
    private int hitpoint = 3;
    private int shoots = 0;
    private int successfulShoots = 0;

    private boolean winState = true;
    private static boolean arrowKeys = true;
    private static Level level = Level.EASY;
    private static double tankSpeed = 0.1;
    private static double migSpeed = 0.1;
    private static double joyStickRadius = 200;

    private Label waveLabel;
    private Label accuracyLabel;
    private Label scoreLabel;
    private Label hitpointLabel;
    private Label radioactiveLabel;
    private Label clusterLabel;

    private ImageView pauseIconView;
    private ImageView scoreIconView;
    private ImageView nukeIconView;
    private ImageView clusterIconView;


    public Game(GameLauncher gameLauncher) {
        currentGame = this;
        this.gameLauncher = gameLauncher;
        this.pane = gameLauncher.gamePane;
        waveController = new WaveController();

        configurePauseIcon();
        configureLabels();
        configureImageViews();
        addLabelsAndImagesToPane();
    }

    private void configurePauseIcon() {
        Image pauseIcon = new Image(Objects.requireNonNull(GameLauncher.class.getResource("/Images/pauseicon.png")).toExternalForm());
        pauseIconView = new ImageView(pauseIcon);
        pauseIconView.setFitWidth(30);
        pauseIconView.setFitHeight(30);
        pauseIconView.setLayoutX(ApplicationController.WIDTH - 45);
        pauseIconView.setLayoutY(15);
        pauseIconView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                plane.getTransition().stop();
                for (Node child : targets.getChildren()) {
                    Target target = (Target) child;
                    if (target.getTransition() != null)
                        target.getTransition().stop();
                }
                new PauseMenu().start(new Stage());
            }
        });
    }

    private void addLabelsAndImagesToPane() {
        pane.getChildren().addAll(pauseIconView, waveLabel, accuracyLabel, scoreLabel, hitpointLabel, radioactiveLabel, clusterLabel,
                scoreIconView, nukeIconView, clusterIconView);
    }

    private void configureImageViews() {
        Image scoreIcon = new Image(Objects.requireNonNull(GameLauncher.class.getResource("/Images/scoreicon.png")).toExternalForm());
        scoreIconView = new ImageView(scoreIcon);
        scoreIconView.setFitWidth(50);
        scoreIconView.setFitHeight(50);
        Image nukeIcon = new Image(Objects.requireNonNull(GameLauncher.class.getResource("/Images/nukeicon.png")).toExternalForm());
        nukeIconView = new ImageView(nukeIcon);
        nukeIconView.setFitWidth(40);
        nukeIconView.setFitHeight(30);
        Image clusterIcon = new Image(Objects.requireNonNull(GameLauncher.class.getResource("/Images/clustericon.png")).toExternalForm());
        clusterIconView = new ImageView(clusterIcon);
        clusterIconView.setFitWidth(40);
        clusterIconView.setFitHeight(30);
        scoreIconView.setLayoutX(10);
        scoreIconView.setLayoutY(10);
        nukeIconView.setLayoutX(130);
        nukeIconView.setLayoutY(9);
        clusterIconView.setLayoutX(130);
        clusterIconView.setLayoutY(35);
    }

    private void configureLabels() {
        waveLabel = new Label("Wave " + waveController.getCurrentWave());
        waveLabel.setTextFill(Color.WHITE);
        waveLabel.setFont(new Font("Arial", 22));
        double accuracyNumber = ((double) successfulShoots) / ((double) shoots) * 100;
        accuracyLabel = new Label(String.format("Accuracy : %.2f%%", accuracyNumber));
        accuracyLabel.setTextFill(Color.WHITE);
        accuracyLabel.setFont(new Font("Arial", 19));
        scoreLabel = new Label("" + score);
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", 22));
        hitpointLabel = new Label("" + hitpoint);
        hitpointLabel.setTextFill(Color.WHITE);
        hitpointLabel.setFont(new Font("Arial", 22));
        radioactiveLabel = new Label("" + radioactiveBombs);
        radioactiveLabel.setTextFill(Color.WHITE);
        radioactiveLabel.setFont(new Font("Arial", 22));
        clusterLabel = new Label("" + clusterBombs);
        clusterLabel.setTextFill(Color.WHITE);
        clusterLabel.setFont(new Font("Arial", 22));

        waveLabel.setLayoutX((double) ApplicationController.WIDTH / 2 - 40);
        waveLabel.setLayoutY(12);
        accuracyLabel.setLayoutX((double) ApplicationController.WIDTH / 2 - 82);
        accuracyLabel.setLayoutY(35);
        scoreLabel.setLayoutX(60);
        scoreLabel.setLayoutY(8);
        hitpointLabel.setLayoutX(60);
        hitpointLabel.setLayoutY(32);
        radioactiveLabel.setLayoutX(174);
        radioactiveLabel.setLayoutY(9);
        clusterLabel.setLayoutX(174);
        clusterLabel.setLayoutY(34);
    }

    public int getRadioactiveBombs() {
        return radioactiveBombs;
    }

    public int getClusterBombs() {
        return clusterBombs;
    }

    public double getIce() {
        return ice;
    }

    public boolean isIced() {
        return isIced;
    }

    public void increaseScore(int score) {
        this.score += score;
        scoreLabel.setText("" + this.score);
    }

    public void increaseRadioactiveBombs() {
        this.radioactiveBombs++;
        radioactiveLabel.setText("" + radioactiveBombs);
    }

    public void decreaseRadioactiveBombs() {
        this.radioactiveBombs--;
        radioactiveLabel.setText("" + radioactiveBombs);
    }

    public void increaseClusterBombs() {
        this.clusterBombs++;
        clusterLabel.setText("" + clusterBombs);
    }

    public void decreaseClusterBombs() {
        this.clusterBombs--;
        clusterLabel.setText("" + clusterBombs);
    }

    public void increaseShoots() {
        this.shoots++;
        updateAccuracyLabel();
    }

    public void increaseSuccessfulShoots() {
        this.successfulShoots++;
        updateAccuracyLabel();
    }

    public int getShoots() {
        return shoots;
    }

    public int getSuccessfulShoots() {
        return successfulShoots;
    }

    public void updateWaveLabel() {
        waveLabel.setText("Wave " + waveController.getCurrentWave());
    }

    public void increaseIce() {
        if (this.ice < 1) this.ice += 0.2;
    }

    public void resetIce() {
        this.ice = 0;
    }

    public void setIced(boolean iced) {
        isIced = iced;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public void increaseHitpoint() {
        if (hitpoint < 2) hitpoint++;
        hitpointLabel.setText("" + hitpoint);
    }

    public void decreaseHitpoint() {
        hitpoint--;
        if (hitpoint >= 0) hitpointLabel.setText("" + hitpoint);
    }

    public static boolean isArrowKeys() {
        return arrowKeys;
    }

    public static void setArrowKeys(boolean arrowKeys) {
        Game.arrowKeys = arrowKeys;
    }

    public int getScore() {
        return score;
    }

    public boolean getWinState() {
        return winState;
    }

    public void setWinState(boolean winState) {
        this.winState = winState;
    }

    public static void setLevel(Level level) {
        Game.level = level;
        updateLevel();
    }

    private static void updateLevel() {
        if (level == Level.EASY) {
            tankSpeed = 0.1;
            migSpeed = 0.1;
            joyStickRadius = 100;
        } else if (level == Level.NORMAL) {
            tankSpeed = 0.2;
            migSpeed = 0.3;
            joyStickRadius = 200;
        } else if (level == Level.HARD) {
            tankSpeed = 0.3;
            migSpeed = 0.2;
            joyStickRadius = 300;
        }
    }

    public static double getTankSpeed() {
        return tankSpeed;
    }


    public static double getMigSpeed() {
        return migSpeed;
    }

    public static double getJoyStickRadius() {
        return joyStickRadius;
    }

    private void updateAccuracyLabel() {
        double accuracyNumber = (((double) successfulShoots) / ((double) shoots) * 100);
        accuracyLabel.setText(String.format("Accuracy : %.2f%%", accuracyNumber));
    }
}
