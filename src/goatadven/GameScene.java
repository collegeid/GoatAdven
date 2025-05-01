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
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class GameScene {
    private int score = 0;
    private Text scoreText;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private long lastSpawnTime = 0;

    public void show(Stage stage) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        Pane root = new Pane();
        root.setPrefSize(screenWidth, screenHeight);

        // Background
        BackgroundImage bg = new BackgroundImage(
            new Image("file:resources/assets/bg/gameplay.png", screenWidth, screenHeight, false, true),
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
            if (e.getCode() == KeyCode.SPACE) {
                goat.jump();
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                goat.duck();
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                goat.stand();
            }
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                goat.update();

                // Spawn obstacle
                if (now - lastSpawnTime > 1_500_000_000L) {
                    Obstacle obs = ObstacleManager.spawn(screenWidth, screenHeight, score);
                    obstacles.add(obs);
                    root.getChildren().add(obs.getImageView());
                    lastSpawnTime = now;
                }

                // Gerak obstacle + tabrakan
                for (int i = 0; i < obstacles.size(); i++) {
                    Obstacle obs = obstacles.get(i);
                    obs.move();

                    if (obs.isOutOfScreen()) {
                        root.getChildren().remove(obs.getImageView());
                        obstacles.remove(i--);
                        score += 10;
                        scoreText.setText("Score: " + score);
                        continue;
                    }

                    // Tabrakan lebih cerdas
                    if (goat.getBounds().intersects(obs.getBounds())) {
                        boolean kenaGround = obs.getType().equals("ground") && !goat.isInAir();
                        boolean kenaAir = obs.getType().equals("air") && !goat.isDucking();

                        if (kenaGround || kenaAir) {
                            this.stop();
                            GameOverScene.show(stage, score);
                        }
                    }
                }
            }
        };

        timer.start();
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}
