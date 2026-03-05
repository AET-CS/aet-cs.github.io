package connectfour.view;

import connectfour.model.Board;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.function.IntConsumer;

public class ConnectFourFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final JLabel statusLabel;
    private final JLabel moveLogLabel;
    private final JButton newGameButton;

    public ConnectFourFrame() {
        super("Connect Four (AP CS MVP)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Choose player types and start.", SwingConstants.CENTER);
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD, 16f));

        moveLogLabel = new JLabel("Move log: (none)", SwingConstants.CENTER);

        boardPanel = new BoardPanel();

        newGameButton = new JButton("New Game");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(statusLabel, BorderLayout.CENTER);
        topPanel.add(moveLogLabel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(newGameButton);

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

    public void animateDrop(int col, int row, connectfour.model.Piece piece, Runnable onDone) {
        boardPanel.animateDrop(col, row, piece, onDone);
    }

    public void animateWinningCells(int[][] cells, int durationMs, Runnable onDone) {
        boardPanel.animateWinningCells(cells, durationMs, onDone);
    }
}
