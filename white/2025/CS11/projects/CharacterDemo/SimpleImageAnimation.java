import java.awt.event.KeyEvent;

/**
 * SimpleImageAnimation.java
 * A straightforward example showing image loading and animation
 * with keyboard and mouse input using Princeton's stdlib.jar
 * <p>
 * This version emphasizes loading and displaying images
 */
public class SimpleImageAnimation {

    static void main(String[] args) {
        // Set up canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 600);
        StdDraw.enableDoubleBuffering();

        // Character position
        double x = 400;
        double y = 300;
        double speed = 5;

        // Target position (for mouse clicks)
        double targetX = 400;
        double targetY = 300;
        boolean drawTarget = false;

        // Animation frame counter
        int frame = 0;

        // NOTE: You need to have these image files in your project folder
        // You can use any PNG images and rename them accordingly
        String backgroundImage = "background.png";  // Any background image
        String characterImage = "character.png";    // Character sprite
        String itemImage = "star.png";              // Collectible item

        // Item position
        double itemX = 600;
        double itemY = 400;
        boolean itemVisible = true;

        // Game loop
        while (true) {

            // === KEYBOARD INPUT ===
            // Arrow keys for direct control
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                x -= speed;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                x += speed;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                y += speed;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                y -= speed;
            }

            // R key to reset item
            if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                itemVisible = true;
                itemX = Math.random() * 700 + 50;
                itemY = Math.random() * 500 + 50;
            }

            // T key for turbo!
            if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
                speed = 10;
            } else {
                speed = 5;
            }

            // S key for spinDude
            boolean spinDude = StdDraw.isKeyPressed(KeyEvent.VK_S);

            // === MOUSE INPUT ===
            // Click to set a target position
            if (StdDraw.isMousePressed()) {
                targetX = StdDraw.mouseX();
                targetY = StdDraw.mouseY();
                drawTarget = true;
            }

            // Move toward target if one is set
            if (drawTarget) {
                double dx = targetX - x;
                double dy = targetY - y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                dx = dx / distance; // get the x direction to move
                dy = dy / distance; // get the y direction to move

                if (distance > 5) {
                    // Move towards the target if we're not already there
                    x += dx * speed;
                    y += dy * speed;
                } else {
                    // erase target if the hero is close
                    // and stop moving
                    drawTarget = false;
                }
            }

            // === COLLISION CHECK ===
            // Check if character touches item
            // By computing distance between center points
            // We'll say 50 pixels is a "hit"
            if (itemVisible) {
                double dist = Math.sqrt(Math.pow(x - itemX, 2) + Math.pow(y - itemY, 2));
                if (dist < 50) {
                    itemVisible = false;
                }
            }

            // === DRAWING ===
            StdDraw.clear();

            // Draw background
            StdDraw.picture(400, 300, backgroundImage, 800, 600);


            // Draw star (with simple animation)
            if (itemVisible) {
                double bobAmount = Math.sin(frame * 0.1) * 5;
                StdDraw.picture(itemX, itemY + bobAmount, itemImage, 50, 50, (5 * frame) % 360);
            }

            // Draw character
            if (spinDude) {
                StdDraw.picture(x, y, characterImage, 60, 60, (speed * speed * frame) % 360);

            } else {
                StdDraw.picture(x, y, characterImage, 60, 60);
            }

            // Draw target marker when mouse is clicked
            if (drawTarget) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.circle(targetX, targetY, 10);
                StdDraw.line(targetX - 15, targetY, targetX + 15, targetY);
                StdDraw.line(targetX, targetY - 15, targetX, targetY + 15);
            }

            // Draw UI text
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(10, 580, "Controls:");
            StdDraw.textLeft(10, 560, "Arrow Keys = Move");
            StdDraw.textLeft(10, 540, "Mouse Click = Set Target and Move");
            StdDraw.textLeft(10, 520, "R = Reset Item");
            StdDraw.textLeft(10, 500, "T = Turbo");
            StdDraw.textLeft(10, 480, "S = SpinDude");


            // Status message
            if (!itemVisible) {
                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                StdDraw.text(400, 550, "Item Collected! Press R for new item");
            }

            // Update animation frame
            frame++;

            // Display frame and pause
            StdDraw.show();
            StdDraw.pause(30); // ~33 FPS
        }
    }
}
