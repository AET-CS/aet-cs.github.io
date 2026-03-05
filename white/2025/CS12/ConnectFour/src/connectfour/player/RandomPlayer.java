package connectfour.player;

import connectfour.model.Board;
import connectfour.model.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
    private final Random random = new Random();

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public int getMove(Board board, Piece color) {
        List<Integer> validColumns = new ArrayList<>();
        for (int col = 0; col < Board.COLS; col++) {
            if (board.isValidMove(col)) {
                validColumns.add(col);
            }
        }

        if (validColumns.isEmpty()) {
            return -1;
        }

        return validColumns.get(random.nextInt(validColumns.size()));
    }
}
