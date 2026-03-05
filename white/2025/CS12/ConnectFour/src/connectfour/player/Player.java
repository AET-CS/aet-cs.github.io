package connectfour.player;

import connectfour.model.Board;
import connectfour.model.Piece;

public abstract class Player {
    private final String name;

    protected Player(String name) {
        this.name = name;
    }

    public abstract int getMove(Board board, Piece color);

    public String getName() {
        return name;
    }

    public boolean isHuman() {
        return false;
    }
}
