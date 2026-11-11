import java.util.Random;

public class TicTacToeModel {
    public static final char EMPTY = ' ';

    // Students may read and modify the individual squares directly.
    public final char[][] board = new char[3][3];

    private char currentPlayer = 'X';

    public TicTacToeModel() {
        // A
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    // A move is valid only when it is numbered 1-9 and the square is empty.
    public boolean checkValid(int i) {
        return true;
    }

    // Place the current player's mark in the numbered square.
    public void updateBoard(int i) {
        if (!checkValid(i)) {
            throw new IllegalArgumentException("Invalid move: " + i);
        }
        // B
    }

    // Return X or O for a winner, or EMPTY if there is no winner.
    public char getWinner() {
        // C
        return EMPTY;
    }

    // The game is over when someone has won or no empty squares remain.
    public boolean isGameOver() {
        // D
        return false;
    }
}
