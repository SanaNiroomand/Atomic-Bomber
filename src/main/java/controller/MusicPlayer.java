package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;

    public static void playMusic(int musicNumber) {
        if (mediaPlayer == null) {
            String musicFilePath = "/Media/music" + musicNumber + ".wav";
            URL resource = MusicPlayer.class.getResource(musicFilePath);
            assert resource != null;
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            mediaPlayer.play();
        }
    }

    public static void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    public static void removeMusic() {
        mediaPlayer = null;
    }
}
