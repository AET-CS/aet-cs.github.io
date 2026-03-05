package connectfour.view;

import connectfour.model.Board;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.function.IntConsumer;

public class ConnectFourFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final JLabel statusLabel;
    private final JLabel moveLogLabel;
    private final JProgressBar batchProgressBar;
    private final JButton newGameButton;

    public ConnectFourFrame() {
        super("Connect Four (AP CS MVP)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Choose player types and start.", SwingConstants.CENTER);
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD, 16f));

        moveLogLabel = new JLabel("Move log: (none)", SwingConstants.CENTER);
        batchProgressBar = new JProgressBar(0, 100);
        batchProgressBar.setStringPainted(true);
        batchProgressBar.setString("Batch progress");
        batchProgressBar.setVisible(false);

        boardPanel = new BoardPanel();

        newGameButton = new JButton("New Game");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(statusLabel, BorderLayout.CENTER);
        topPanel.add(moveLogLabel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(newGameButton);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(batchProgressBar, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void setBoard(Board board) {
        boardPanel.setBoard(board);
    }

    public void setBoardInteractive(boolean enabled) {
        boardPanel.setInteractive(enabled);
    }

    public void onBoardColumnClick(IntConsumer listener) {
        boardPanel.setOnColumnClicked(listener);
    }

    public void onNewGame(Runnable action) {
        newGameButton.addActionListener(e -> action.run());
    }

    public void setStatus(String text) {
        statusLabel.setText(text);
    }

    public void setMoveLog(String text) {
        moveLogLabel.setText(text);
    }

    public void setBatchProgressVisible(boolean visible) {
        batchProgressBar.setVisible(visible);
    }

    public void setBatchProgressMax(int max) {
        batchProgressBar.setMinimum(0);
        batchProgressBar.setMaximum(max);
        batchProgressBar.setValue(0);
        batchProgressBar.setString("0/" + max);
    }

    public void setBatchProgressValue(int value) {
        batchProgressBar.setValue(value);
        batchProgressBar.setString(value + "/" + batchProgressBar.getMaximum());
    }

    public void animateDrop(int col, int row, connectfour.model.Piece piece, Runnable onDone) {
        boardPanel.animateDrop(col, row, piece, onDone);
    }

    public void animateWinningCells(int[][] cells, int durationMs, Runnable onDone) {
        boardPanel.animateWinningCells(cells, durationMs, onDone);
    }
}
