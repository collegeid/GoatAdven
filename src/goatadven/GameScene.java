// ============================
// File: GameScene.java
// ============================
package goatadven;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class GameScene {
    private static int score = 0;
    private static Text scoreText;
    private static ArrayList<Obstacle> obstacles = new ArrayList<>();
    private static long lastSpawnTime = 0;

    public static void show(Stage stage) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        Pane root = new Pane();
        root.setPrefSize(screenWidth, screenHeight);

        // Background
        BackgroundImage bg = new BackgroundImage(
            new Image("file:resources/assets/bg/bg1.png", screenWidth, screenHeight, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );
        root.setBackground(new Background(bg));

        // Karakter Goat
        Goat goat = new Goat(100, screenHeight - 250);
        root.getChildren().add(goat.getImageView());

        // Skor
        scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("Arial", 28));
        scoreText.setFill(Color.WHITE);
        scoreText.setX(20);
        scoreText.setY(40);
        root.getChildren().add(scoreText);

        Scene scene = new Scene(root);

        // Input
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE -> goat.jump();
                case DOWN, S -> goat.duck();
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == javafx.scene.input.KeyCode.DOWN) {
                goat.stand();
            }
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate = 0;
            @Override
            public void handle(long now) {
                // Update Goat
                goat.update();

                // Update obstacle
                if (now - lastSpawnTime > 1_500_000_000L) { // spawn setiap 1.5 detik
                    Obstacle obs = ObstacleManager.spawn(screenWidth, screenHeight);
                    obstacles.add(obs);
                    root.getChildren().add(obs.getImageView());
                    lastSpawnTime = now;
                }

                for (int i = 0; i < obstacles.size(); i++) {
                    Obstacle obs = obstacles.get(i);
                    obs.move();
                    if (obs.isOutOfScreen()) {
                        root.getChildren().remove(obs.getImageView());
                        obstacles.remove(obs);
                        i--;
                        score += 10;
                        scoreText.setText("Score: " + score);
                    } else if (goat.getBounds().intersects(obs.getBounds())) {
                        this.stop();
                        GameOverScene.show(stage, score);
                    }
                }
            }
        };

        timer.start();
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}
