package goatadven;

import java.util.Random;

public class ObstacleManager {
    private static final String[] OBSTACLE_TYPES = {
        "resources/assets/karakter/goat_jump.png",
        "resources/assets/karakter/goat_nunduk.png"
    };

    public static Obstacle spawn(double screenWidth, double screenHeight) {
        Random rand = new Random();
        int type = rand.nextInt(2); // 0 = batu, 1 = burung

        String path = OBSTACLE_TYPES[type];

        double x = screenWidth + 100;
        double y;
        double width = 80;
        double height = 80;

        if (type == 0) {
            // obstacle tanah
            y = screenHeight - 120;
        } else {
            // obstacle udara (burung)
            y = screenHeight - 250;
        }

        return new Obstacle(path, x, y, width, height);
    }
}
