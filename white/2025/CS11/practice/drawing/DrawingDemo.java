/**
 * A simple introduction to StdDraw using pixel coordinates.
 * Draws a scene with no loops or animation - just basic shapes.
 * Using setScale makes coordinates easier to think about!
 */
public class DrawingDemo {
    public static void main(String[] args) {
        // Set up the canvas - 600x600 pixels
        StdDraw.setCanvasSize(600, 600);

        // Use pixel coordinates: (0,0) to (600,600) instead of (0,0) to (1,1)
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        // Draw the sky (background)
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(300, 300, 300);

        // Draw the ground
        StdDraw.setPenColor(34, 139, 34); // Green (RGB values)
        StdDraw.filledRectangle(300, 90, 300, 90);

        // Draw a house
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(300, 240, 90, 90); // House body

        // Draw the roof (triangle)
        StdDraw.setPenColor(139, 69, 19); // Brown
        double[] roofX = { 210, 300, 390 };
        double[] roofY = { 330, 420, 330 };
        StdDraw.filledPolygon(roofX, roofY);

        // Draw a door
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(300, 210, 24, 48);

        // Draw windows
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.filledSquare(252, 270, 18);
        StdDraw.filledSquare(348, 270, 18);

        // Draw the sun
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(480, 480, 48);

        // Draw a tree
        StdDraw.setPenColor(139, 69, 19); // Brown trunk
        StdDraw.filledRectangle(120, 210, 18, 60);

        StdDraw.setPenColor(34, 139, 34); // Green leaves
        StdDraw.filledCircle(120, 300, 48);

        // Add some text
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(StdDraw.getFont().deriveFont(24f));
        StdDraw.text(300, 30, "Welcome to Graphics!");

        // That's it - the image stays on screen until you close it
    }
}