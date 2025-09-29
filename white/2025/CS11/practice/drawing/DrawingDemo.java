public class DrawingDemo {
    public static void main(String[] args) {
        // Set up the canvas
        StdDraw.setCanvasSize(600, 600);

        // Draw the sky (background)
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(0.5, 0.5, 0.5);

        // Draw the ground
        StdDraw.setPenColor(34, 139, 34);  // Green (RGB values)
        StdDraw.filledRectangle(0.5, 0.15, 0.5, 0.15);

        // Draw a house
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(0.5, 0.4, 0.15, 0.15);  // House body

        // Draw the roof (triangle)
        StdDraw.setPenColor(139, 69, 19);  // Brown
        double[] roofX = {0.35, 0.5, 0.65};
        double[] roofY = {0.55, 0.7, 0.55};
        StdDraw.filledPolygon(roofX, roofY);

        // Draw a door
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(0.5, 0.35, 0.04, 0.08);

        // Draw windows
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.filledSquare(0.42, 0.45, 0.03);
        StdDraw.filledSquare(0.58, 0.45, 0.03);

        // Draw the sun
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(0.8, 0.8, 0.08);

        // Draw a tree
        StdDraw.setPenColor(139, 69, 19);  // Brown trunk
        StdDraw.filledRectangle(0.2, 0.35, 0.03, 0.1);

        StdDraw.setPenColor(34, 139, 34);  // Green leaves
        StdDraw.filledCircle(0.2, 0.5, 0.08);

        // Add some text
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(StdDraw.getFont().deriveFont(24f));
        StdDraw.text(0.5, 0.05, "Welcome to Graphics!");

        // That's it - the image stays on screen until you close it
    }    }

