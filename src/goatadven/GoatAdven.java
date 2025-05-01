package goatadven;

import javafx.application.Application;
import javafx.stage.Stage;

public class GoatAdven extends Application {

    @Override
    public void start(Stage stage) {
        MainMenuScene.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
