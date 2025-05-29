import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PuzzleView extends JPanel {
    private PuzzleModel model;
    private static final int TILE_SIZE = 120;
    private static final int TILE_MARGIN = 5;

    // Animation properties
    private static final int ANIMATION_FRAMES = 10;
    private static final int ANIMATION_DELAY = 16; // ~60 FPS (16ms per frame)

    private boolean isAnimating = false;
    private int animatingTile = 0;
    private Point animationStart = new Point();
    private Point animationEnd = new Point();
    private int currentFrame = 0;
    private Timer animationTimer;

    public PuzzleView(PuzzleModel model) {
        this.model = model;

        int boardSize = model.getSize() * (TILE_SIZE + TILE_MARGIN) + TILE_MARGIN;
        setPreferredSize(new Dimension(boardSize, boardSize));
        setBackground(Color.DARK_GRAY);

        // Initialize animation timer
        animationTimer = new Timer(ANIMATION_DELAY, e -> updateAnimation());
    }

    // Call this method instead of directly calling model.moveTile()
    public boolean animatedMoveTile(int row, int col) {
        if (isAnimating) {
            return false; // Don't allow moves during animation
        }

        // Check if move is valid (same logic as model.moveTile)
        int emptyRow = findEmptyRow();
        int emptyCol = findEmptyCol();

        if (!((Math.abs(row - emptyRow) == 1 && col == emptyCol) ||
                (Math.abs(col - emptyCol) == 1 && row == emptyRow))) {
            return false; // Invalid move
        }

        // Set up animation
        animatingTile = model.getTileValue(row, col);

        // Calculate pixel positions
        animationStart.x = TILE_MARGIN + col * (TILE_SIZE + TILE_MARGIN);
        animationStart.y = TILE_MARGIN + row * (TILE_SIZE + TILE_MARGIN);
        animationEnd.x = TILE_MARGIN + emptyCol * (TILE_SIZE + TILE_MARGIN);
        animationEnd.y = TILE_MARGIN + emptyRow * (TILE_SIZE + TILE_MARGIN);

        // Start animation
        isAnimating = true;
        currentFrame = 0;
        animationTimer.start();

        // Immediately update the model (the animation is just visual)
        model.moveTile(row, col);

        return true;
    }

    private void updateAnimation() {
        currentFrame++;

        if (currentFrame >= ANIMATION_FRAMES) {
            // Animation complete
            isAnimating = false;
            animationTimer.stop();
        }

        repaint(); // Trigger redraw
    }

    // Helper methods to find empty tile position
    private int findEmptyRow() {
        for (int row = 0; row < model.getSize(); row++) {
            for (int col = 0; col < model.getSize(); col++) {
                if (model.getTileValue(row, col) == 0) {
                    return row;
                }
            }
        }
        return -1;
    }

    private int findEmptyCol() {
        for (int row = 0; row < model.getSize(); row++) {
            for (int col = 0; col < model.getSize(); col++) {
                if (model.getTileValue(row, col) == 0) {
                    return col;
                }
            }
        }
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the tiles
        for (int row = 0; row < model.getSize(); row++) {
            for (int col = 0; col < model.getSize(); col++) {
                int value = model.getTileValue(row, col);

                if (value != 0) {  // Skip the empty tile
                    // Skip the animating tile (we'll draw it separately)
                    if (isAnimating && value == animatingTile) {
                        continue;
                    }

                    int x = TILE_MARGIN + col * (TILE_SIZE + TILE_MARGIN);
                    int y = TILE_MARGIN + row * (TILE_SIZE + TILE_MARGIN);

                    drawTile(g2d, value, x, y);
                }
            }
        }

        // Draw the animating tile if animation is in progress
        if (isAnimating) {
            // Calculate interpolated position
            float progress = (float) currentFrame / ANIMATION_FRAMES;

            // Use easing function for smoother animation (optional)
            progress = easeInOutQuad(progress);

            int animX = (int) (animationStart.x + (animationEnd.x - animationStart.x) * progress);
            int animY = (int) (animationStart.y + (animationEnd.y - animationStart.y) * progress);

            drawTile(g2d, animatingTile, animX, animY);
        }

        // If puzzle is solved, draw a congratulation message
        if (model.isSolved() && !isAnimating) {
            drawCongratulations(g2d);
        }
    }

    // Separate method to draw individual tiles
    private void drawTile(Graphics2D g2d, int value, int x, int y) {
        // Draw tile background
        g2d.setColor(new Color(50, 150, 200));
        g2d.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);

        // Draw tile border
        g2d.setColor(new Color(30, 100, 150));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);

        // Draw tile value
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
        String text = String.valueOf(value);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        g2d.drawString(text, x + (TILE_SIZE - textWidth) / 2,
                y + (TILE_SIZE + textHeight / 2) / 2);
    }

    // Easing function for smoother animation (optional)
    private float easeInOutQuad(float t) {
        return t < 0.5f ? 2 * t * t : 1 - (float) Math.pow(-2 * t + 2, 2) / 2;
    }

    private void drawCongratulations(Graphics2D g2d) {
        int width = getWidth();
        int height = getHeight();

        // Create a semi-transparent overlay
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, width, height);

        // Draw congratulation text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
        String text = "Puzzle Solved!";

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);

        g2d.drawString(text, (width - textWidth) / 2, height / 2);
    }

    public Point getTilePosition(int x, int y) {
        if (x < TILE_MARGIN || y < TILE_MARGIN) {
            return null;
        }

        int col = (x - TILE_MARGIN) / (TILE_SIZE + TILE_MARGIN);
        int row = (y - TILE_MARGIN) / (TILE_SIZE + TILE_MARGIN);

        if (col >= model.getSize() || row >= model.getSize()) {
            return null;
        }

        return new Point(row, col);
    }

    // Getter to check if animation is in progress
    public boolean isAnimating() {
        return isAnimating;
    }
}