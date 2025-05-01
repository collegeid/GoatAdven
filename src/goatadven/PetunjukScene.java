package goatadven;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PetunjukScene {

    public static void show(Stage stage) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        StackPane root = new StackPane();

        // Background
        Image bgImage = new Image("file:resources/assets/bg/bg1.png");
        BackgroundImage bg = new BackgroundImage(
            bgImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        root.setBackground(new Background(bg));

        // Teks petunjuk
        Text petunjukText = new Text(
            "🕹️ Cara Bermain:\n\n" +
            "- Tekan tombol SPASI untuk melompat\n" +
            "- Hindari rintangan seperti batu dan jebakan\n" +
            "- Semakin lama bertahan, skor akan bertambah\n" +
            "- Jika menabrak rintangan, permainan berakhir"
        );
        petunjukText.setFont(Font.font("Arial", 28));
        petunjukText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");

        // Tombol kembali
        Button backBtn = new Button("⏪ Kembali");
         backBtn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        );
        backBtn.setOnMouseEntered(e -> backBtn.setStyle(
            "-fx-background-color: rgba(255,255,255,0.2); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25; " +
            "-fx-cursor: hand;"
        ));
        backBtn.setOnMouseExited(e -> backBtn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        ));
        backBtn.setOnAction(e -> MainMenuScene.show(stage));

        VBox content = new VBox(40, petunjukText, backBtn);
        content.setAlignment(Pos.CENTER);

        root.getChildren().add(content);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}
