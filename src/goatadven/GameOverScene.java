package goatadven;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameOverScene {

    public static void show(Stage stage, int finalScore) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        StackPane root = new StackPane();

        // Background
        Image bgImage = new Image("file:resources/assets/bg/gameplay.png", screenWidth, screenHeight, false, true);
        BackgroundImage bg = new BackgroundImage(
            bgImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );
        root.setBackground(new Background(bg));

        Text gameOverText = new Text("Game Over!");
        gameOverText.setFont(Font.font("Arial", 60));
        gameOverText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        Text scoreText = new Text("Skor Akhir: " + finalScore);
        scoreText.setFont(Font.font("Arial", 32));
        scoreText.setStyle("-fx-fill: yellow; -fx-stroke: black; -fx-stroke-width: 1;");

        Button retryBtn = new Button("🔁 Coba Lagi");
        retryBtn.setFont(Font.font("Arial", 22));
         retryBtn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        );
        retryBtn.setOnMouseEntered(e -> retryBtn.setStyle(
            "-fx-background-color: rgba(255,255,255,0.2); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25; " +
            "-fx-cursor: hand;"
        ));
        retryBtn.setOnMouseExited(e -> retryBtn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        ));
        retryBtn.setOnAction(e -> new GameScene().show(stage));

        Button menuBtn = new Button("🏠 Kembali ke Menu");
        menuBtn.setFont(Font.font("Arial", 22));
         menuBtn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        );
        menuBtn.setOnMouseEntered(e -> menuBtn.setStyle(
            "-fx-background-color: rgba(255,255,255,0.2); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25; " +
            "-fx-cursor: hand;"
        ));
        menuBtn.setOnMouseExited(e -> menuBtn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        ));
        menuBtn.setOnAction(e -> MainMenuScene.show(stage));

        VBox content = new VBox(30, gameOverText, scoreText, retryBtn, menuBtn);
        content.setAlignment(Pos.CENTER);

        root.getChildren().add(content);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}
