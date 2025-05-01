package goatadven;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

public class Obstacle {
    private ImageView imageView;
    private double speed = 5;

    public Obstacle(String imagePath, double x, double y, double width, double height) {
        Image image = new Image("file:" + imagePath);
        imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setX(x);
        imageView.setY(y);
    }

    public void move() {
        imageView.setX(imageView.getX() - speed);
    }

    public boolean isOutOfScreen() {
        return imageView.getX() + imageView.getFitWidth() < 0;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Bounds getBounds() {
        return imageView.getBoundsInParent();
    }
}
