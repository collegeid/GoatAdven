package goatadven;

import java.util.Random;

public class ObstacleManager {
    // Daftar path obstacle (.png = ground, .gif = air)
    private static final String[] OBSTACLE_PATHS = {
        "resources/assets/obstacle/rock.png",
        "resources/assets/obstacle/bird.gif"
    };

    public static Obstacle spawn(double screenWidth, double screenHeight, int score) {
        Random rand = new Random();
        String path = OBSTACLE_PATHS[rand.nextInt(OBSTACLE_PATHS.length)];

        double x = screenWidth + 100;
        double y;
        double width = 80;
        double height = 80;

        // Tentukan posisi berdasarkan jenis file
        if (path.endsWith(".gif")) {
            y = screenHeight - 350; // obstacle udara
        } else {
            y = screenHeight - 220; // obstacle darat
        }

        // Speed dinamis berdasarkan skor
        double baseSpeed = 2.5;
        double maxSpeed = 10.0;
        double speed = Math.min(baseSpeed + (score / 200.0), maxSpeed);

        return new Obstacle(path, x, y, width, height, speed);
    }
}
