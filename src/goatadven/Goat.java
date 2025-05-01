package goatadven;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

public class Goat {
    //yang pertama untuk lariii biasa
    private final Image runImage = new Image("file:resources/assets/karakter/goat_jump.png");
    private final Image jumpImage = new Image("file:resources/assets/karakter/goat_jump.png");
    private final Image duckImage = new Image("file:resources/assets/karakter/goat_nunduk.png");

    private ImageView imageView;
    private double velocityY = 0;
    private boolean inAir = false;
    private int jumpCount = 0;

    private final double GRAVITY = 0.8;
    private final double JUMP_POWER = -14;
    private final double groundY;

    public Goat(double x, double y) {
        imageView = new ImageView(runImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setX(x);
        imageView.setY(y);
        groundY = y;
    }

    public void jump() {
        if (jumpCount < 2) {
            velocityY = JUMP_POWER;
            jumpCount++;
            imageView.setImage(jumpImage);
        }
    }

    public void duck() {
        if (!inAir) {
            imageView.setImage(duckImage);
            imageView.setFitHeight(60); // lebih kecil saat nunduk
        }
    }

    public void stand() {
        if (!inAir) {
            imageView.setImage(runImage);
            imageView.setFitHeight(100);
        }
    }

    public void update() {
        double y = imageView.getY();
        y += velocityY;
        velocityY += GRAVITY;

        if (y >= groundY) {
            y = groundY;
            velocityY = 0;
            inAir = false;
            jumpCount = 0;
            stand(); // balik ke run
        } else {
            inAir = true;
        }

        imageView.setY(y);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Bounds getBounds() {
        return imageView.getBoundsInParent();
    }
}
