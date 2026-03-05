package connectfour.model;

public enum Piece {
    EMPTY,
    RED,
    BLUE;

    public Piece opposite() {
        return this == RED ? BLUE : RED;
    }
}
