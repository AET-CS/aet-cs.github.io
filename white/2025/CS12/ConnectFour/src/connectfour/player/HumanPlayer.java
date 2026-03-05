package connectfour.player;

import connectfour.model.Board;
import connectfour.model.Piece;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class HumanPlayer extends Player {
    private final BlockingQueue<Integer> selectedColumns = new LinkedBlockingQueue<>();

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int getMove(Board board, Piece color) {
        while (true) {
            try {
                int col = selectedColumns.take();
                if (board.isValidMove(col)) {
                    return col;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return -1;
            }
        }
    }

    public void submitMove(int col) {
        selectedColumns.offer(col);
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
