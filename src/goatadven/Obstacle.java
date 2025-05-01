package goatadven;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;
import javafx.geometry.BoundingBox;

public class Obstacle {
    private ImageView imageView;
    private double speed;
    private String type;

    public Obstacle(String imagePath, double x, double y, double width, double height, double speed) {
        Image image = new Image("file:" + imagePath);
        imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setX(x);
        imageView.setY(y);
        this.speed = speed;
        this.type = imagePath.endsWith(".gif") ? "air" : "ground";
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
        Bounds original = imageView.getBoundsInParent();
        return new BoundingBox(
            original.getMinX() + 10,
            original.getMinY() + 10,
            original.getWidth() - 20,
            original.getHeight() - 20
        );
    }

    public String getType() {
        return type;
    }
}
