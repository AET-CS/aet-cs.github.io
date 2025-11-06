import javax.swing.*;
import java.awt.*;

public class SierpinskiTriangle extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    private static final int DEPTH = 7; // recursion depth

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define the three vertices of the outer triangle
        int x1 = WIDTH / 2;
        int y1 = 50;

        int x2 = 50;
        int y2 = HEIGHT - 50;

        int x3 = WIDTH - 50;
        int y3 = HEIGHT - 50;

        // Draw the Sierpinski triangle
        drawSierpinski(g, DEPTH, x1, y1, x2, y2, x3, y3);
    }

    /**
     * Recursively draws a Sierpinski triangle
     *
     * @param g     Graphics object
     * @param depth Current recursion depth
     * @param x1,   y1 First vertex
     * @param x2,   y2 Second vertex
     * @param x3,   y3 Third vertex
     */
    private void drawSierpinski(Graphics g, int depth,
            int x1, int y1,
            int x2, int y2,
            int x3, int y3) {
        // your code here
    }

    /**
     * Draws a single triangle given three vertices
     */
    private void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        int[] xPoints = { x1, x2, x3 };
        int[] yPoints = { y1, y2, y3 };
        g.drawPolygon(xPoints, yPoints, 3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle");
        SierpinskiTriangle panel = new SierpinskiTriangle();

        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setBackground(Color.WHITE);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}