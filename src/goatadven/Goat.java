package goatadven;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

public class Goat {
    private final double DUCK_OFFSET_Y = 40;
    private final double NORMAL_HEIGHT = 100;
    private final double DUCK_HEIGHT = 60;

    private final Image runImage = new Image("file:resources/assets/karakter/goat_run.gif");
    private final Image jumpImage = new Image("file:resources/assets/karakter/goat_jump.png");
    private final Image duckImage = new Image("file:resources/assets/karakter/goat_nunduk.png");

    private ImageView imageView;
    private double velocityY = 0;
    private int jumpCount = 0;
    private boolean isDucking = false;
    private boolean wasDucking = false;

    private final double GRAVITY = 0.30;
    private final double MAX_FALL_SPEED = 6;
    private final double JUMP_POWER = -17;
    private final double groundY;

    private boolean requestJump = false;

    public Goat(double x, double y) {
        imageView = new ImageView(runImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(NORMAL_HEIGHT);
        imageView.setX(x);
        imageView.setY(y);
        groundY = y;
    }

    public void jump() {
        if (jumpCount == 0 || (jumpCount == 1 && velocityY > 0)) {
            requestJump = true;
        }
    }

    public void duck() {
        if (!isDucking) {
            isDucking = true;
            wasDucking = true;
            imageView.setImage(duckImage);
            imageView.setFitHeight(DUCK_HEIGHT);
            imageView.setY(imageView.getY() + DUCK_OFFSET_Y); // turun sekali
        }
    }

    public void stand() {
        if (isDucking) {
            imageView.setY(imageView.getY() - DUCK_OFFSET_Y); // naik sekali
            imageView.setFitHeight(NORMAL_HEIGHT);
            isDucking = false;
        }

        if (isInAir()) {
            imageView.setImage(jumpImage);
        } else {
            imageView.setImage(runImage);
        }
    }

public void update() {
    if (requestJump) {
        // Buat jump kedua lebih lemah
        velocityY = (jumpCount == 1) ? JUMP_POWER : JUMP_POWER + 3;
        jumpCount++;
        requestJump = false;

        // Reset duck jika sedang nunduk
        if (isDucking) {
            stand();
        }

        imageView.setImage(jumpImage);
        imageView.setFitHeight(NORMAL_HEIGHT);
    }

    double y = imageView.getY();
    velocityY += GRAVITY;
    if (velocityY > MAX_FALL_SPEED) velocityY = MAX_FALL_SPEED;

    y += velocityY;

    if (y >= groundY) {
        y = groundY;
        velocityY = 0;
        jumpCount = 0;

        if (isDucking) {
            imageView.setImage(duckImage);
            imageView.setFitHeight(DUCK_HEIGHT);
            imageView.setY(groundY + DUCK_OFFSET_Y);
        } else {
            imageView.setImage(runImage);
            imageView.setFitHeight(NORMAL_HEIGHT);
            imageView.setY(groundY);
        }
    }

    imageView.setY(y);
}

    public boolean isInAir() {
        return imageView.getY() < groundY;
    }

    public boolean isDucking() {
        return isDucking;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Bounds getBounds() {
        return imageView.getBoundsInParent();
    }
}
