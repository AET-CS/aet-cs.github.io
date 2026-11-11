import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.function.IntConsumer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class TicTacToeView extends JFrame {
    private final JLabel[][] squares = new JLabel[3][3];
    private final JLabel status = new JLabel("", SwingConstants.CENTER);
    private final Action[] moveActions = new Action[9];

    public TicTacToeView() {
        super("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JLabel square = new JLabel("", SwingConstants.CENTER);
                square.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                squares[row][col] = square;
                boardPanel.add(square);
            }
        }

        add(status, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(new JLabel("Press 1-9 to choose a square", SwingConstants.CENTER),
                BorderLayout.SOUTH);
        setSize(360, 420);
        setLocationRelativeTo(null);
    }

    public void addMoveListener(IntConsumer listener) {
        for (int i = 1; i <= 9; i++) {
            final int move = i;
            String actionName = "move" + move;
            Action action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    listener.accept(move);
                }
            };
            moveActions[i - 1] = action;
            getRootPane().getActionMap().put(actionName, action);
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_1 + i - 1, 0), actionName);
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1 + i - 1, 0), actionName);
        }
    }

    public void showBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char square = board[row][col];
                squares[row][col].setText(square == TicTacToeModel.EMPTY
                        ? Integer.toString(row * 3 + col + 1)
                        : Character.toString(square));
                squares[row][col].setForeground(square == TicTacToeModel.EMPTY
                        ? Color.GRAY : Color.BLACK);
            }
        }
    }

    public void setStatus(String message) {
        status.setText(message);
    }

    public void setMovesEnabled(boolean enabled) {
        for (Action action : moveActions) {
            action.setEnabled(enabled);
        }
    }

    public boolean showGameOver(String message) {
        setStatus(message);
        setMovesEnabled(false);
        Object[] options = {"Play Again", "Quit"};
        int choice = JOptionPane.showOptionDialog(this, message, "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
        return choice == 0;
    }
}
