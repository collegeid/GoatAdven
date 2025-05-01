package goatadven;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainMenuScene extends Application {

    @Override
    public void start(Stage stage) {
        // Ambil ukuran layar
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

        // VBox tombol
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        // Tombol
        Button startBtn = createStyledButton("Start Game");
        Button helpBtn = createStyledButton("Petunjuk");
        Button exitBtn = createStyledButton("Exit");

        // Aksi tombol
        startBtn.setOnAction(e -> System.out.println("Start Game diklik."));
        helpBtn.setOnAction(e -> PetunjukScene.show(stage));
        exitBtn.setOnAction(e -> stage.close());

        buttonBox.getChildren().addAll(startBtn, helpBtn, exitBtn);
        root.getChildren().add(buttonBox);

        // Tampilkan scene
        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Goat Adventure");
        stage.show();
    }

    private Button createStyledButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Arial", 24));
        btn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: rgba(255,255,255,0.2); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25; " +
            "-fx-cursor: hand;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 25;"
        ));
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Tambahan agar bisa dipanggil dari PetunjukScene
    public static void show(Stage stage) {
        MainMenuScene menu = new MainMenuScene();
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
