package connectfour.view;

import connectfour.model.Board;
import connectfour.model.Piece;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.IntConsumer;

public class BoardPanel extends JPanel {
    private static final int CELL_SIZE = 90;
    private static final int PADDING = 20;
    private static final Color BOARD_YELLOW = new Color(245, 196, 66);

    private Board board;
    private boolean interactive = false;
    private IntConsumer onColumnClicked;

    private boolean animating;
    private int animCol;
    private int animTargetRow;
    private int animCenterY;
    private Piece animPiece;
    private Timer animationTimer;
    private Runnable onAnimationDone;

    private boolean winBlinking;
    private boolean winBlinkVisible;
    private boolean[][] winningCells;
    private Timer winBlinkTimer;

    public BoardPanel() {
        this.board = new Board();
        this.winningCells = new boolean[Board.ROWS][Board.COLS];
        setBackground(new Color(245, 247, 252));
        setPreferredSize(new Dimension(Board.COLS * CELL_SIZE + PADDING * 2, Board.ROWS * CELL_SIZE + PADDING * 2));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!interactive || animating || winBlinking || onColumnClicked == null) {
                    return;
                }
                int col = nearestColumn(e.getX());
                onColumnClicked.accept(col);
            }
        });
    }

    public void setBoard(Board board) {
        this.board = board.copy();
        repaint();
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }

    public void setOnColumnClicked(IntConsumer onColumnClicked) {
        this.onColumnClicked = onColumnClicked;
    }

    public void animateDrop(int col, int targetRow, Piece piece, Runnable onDone) {
        clearWinningHighlight();
        this.animating = true;
        this.animCol = col;
        this.animTargetRow = targetRow;
        this.animPiece = piece;
        this.onAnimationDone = onDone;
        this.animCenterY = PADDING - CELL_SIZE / 2;

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(12, e -> {
            int targetCenterY = PADDING + animTargetRow * CELL_SIZE + CELL_SIZE / 2;
            animCenterY += 18;
            if (animCenterY >= targetCenterY) {
                animCenterY = targetCenterY;
                animationTimer.stop();
                animating = false;
                repaint();
                if (onAnimationDone != null) {
                    onAnimationDone.run();
                }
                return;
            }
            repaint();
        });
        animationTimer.start();
    }

    public void animateWinningCells(int[][] cells, int durationMs, Runnable onDone) {
        clearWinningHighlight();

        if (cells == null || cells.length == 0) {
            if (onDone != null) {
                onDone.run();
            }
            return;
        }

        for (int[] cell : cells) {
            if (cell.length < 2) {
                continue;
            }
            int row = cell[0];
            int col = cell[1];
            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS) {
                winningCells[row][col] = true;
            }
        }

        winBlinking = true;
        winBlinkVisible = true;
        repaint();

        final int blinkIntervalMs = 200;
        final int[] elapsedMs = { 0 };

        winBlinkTimer = new Timer(blinkIntervalMs, e -> {
            elapsedMs[0] += blinkIntervalMs;
            winBlinkVisible = !winBlinkVisible;
            repaint();

            if (elapsedMs[0] >= durationMs) {
                ((Timer) e.getSource()).stop();
                winBlinking = false;
                winBlinkVisible = true;
                repaint();
                if (onDone != null) {
                    onDone.run();
                }
            }
        });
        winBlinkTimer.start();
    }

    private void clearWinningHighlight() {
        if (winBlinkTimer != null && winBlinkTimer.isRunning()) {
            winBlinkTimer.stop();
        }
        winBlinking = false;
        winBlinkVisible = true;
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                winningCells[row][col] = false;
            }
        }
    }

    private int nearestColumn(int mouseX) {
        float normalized = (mouseX - PADDING - CELL_SIZE / 2.0f) / CELL_SIZE;
        int col = Math.round(normalized);
        if (col < 0) {
            return 0;
        }
        if (col >= Board.COLS) {
            return Board.COLS - 1;
        }
        return col;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int boardX = PADDING;
        int boardY = PADDING;
        int boardW = Board.COLS * CELL_SIZE;
        int boardH = Board.ROWS * CELL_SIZE;

        g2.setColor(BOARD_YELLOW);
        g2.fillRoundRect(boardX, boardY, boardW, boardH, 24, 24);

        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                boolean skipForAnimation = animating && row == animTargetRow && col == animCol;
                boolean hideWinningPiece = winBlinking && !winBlinkVisible && winningCells[row][col];
                Piece piece = (skipForAnimation || hideWinningPiece) ? Piece.EMPTY : board.getCell(row, col);

                int cx = boardX + col * CELL_SIZE + CELL_SIZE / 2;
                int cy = boardY + row * CELL_SIZE + CELL_SIZE / 2;
                drawSlot(g2, cx, cy, piece);
            }
        }

        if (animating && animPiece != null) {
            int cx = boardX + animCol * CELL_SIZE + CELL_SIZE / 2;
            drawPiece(g2, cx, animCenterY, animPiece);
        }

        g2.dispose();
    }

    private void drawSlot(Graphics2D g2, int centerX, int centerY, Piece piece) {
        g2.setColor(Color.WHITE);
        g2.fillOval(centerX - 34, centerY - 34, 68, 68);

        if (piece != Piece.EMPTY) {
            drawPiece(g2, centerX, centerY, piece);
        }

        g2.setColor(new Color(0, 0, 0, 40));
        g2.setStroke(new BasicStroke(1.4f));
        g2.drawOval(centerX - 34, centerY - 34, 68, 68);
    }

    private void drawPiece(Graphics2D g2, int centerX, int centerY, Piece piece) {
        if (piece == Piece.RED) {
            g2.setColor(new Color(220, 52, 52));
        } else if (piece == Piece.BLUE) {
            g2.setColor(new Color(52, 110, 220));
        } else {
            g2.setColor(Color.WHITE);
        }

        g2.fillOval(centerX - 30, centerY - 30, 60, 60);
        g2.setColor(new Color(0, 0, 0, 55));
        g2.drawOval(centerX - 30, centerY - 30, 60, 60);
    }
}
