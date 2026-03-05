package connectfour.model;

public class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final int CONNECT_N = 4;

    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                this.grid[row][col] = Piece.EMPTY;
            }
        }
    }

    public Board(Board other) {
        this.grid = new Piece[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            System.arraycopy(other.grid[row], 0, this.grid[row], 0, COLS);
        }
    }

    public Board copy() {
        return new Board(this);
    }

    public Piece getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean isValidMove(int col) {
        return col >= 0 && col < COLS && grid[0][col] == Piece.EMPTY;
    }

    public int dropPiece(int col, Piece piece) {
        if (!isValidMove(col)) {
            return -1;
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col] == Piece.EMPTY) {
                grid[row][col] = piece;
                return row;
            }
        }
        return -1;
    }

    public boolean isFull() {
        for (int col = 0; col < COLS; col++) {
            if (grid[0][col] == Piece.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public GameState getGameState() {
        if (findConnectFour(Piece.RED) != null) {
            return GameState.RED_WINS;
        }
        if (findConnectFour(Piece.BLUE) != null) {
            return GameState.BLUE_WINS;
        }
        if (isFull()) {
            return GameState.TIE;
        }
        return GameState.PLAYING;
    }

    public int[][] getWinningCells() {
        int[][] redWin = findConnectFour(Piece.RED);
        if (redWin != null) {
            return redWin;
        }

        int[][] blueWin = findConnectFour(Piece.BLUE);
        if (blueWin != null) {
            return blueWin;
        }

        return new int[0][0];
    }

    private int[][] findConnectFour(Piece piece) {
        int[][] directions = {
                { 0, 1 }, // horizontal
                { 1, 0 }, // vertical
                { 1, 1 }, // diagonal down-right
                { 1, -1 } // diagonal down-left
        };

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] != piece) {
                    continue;
                }
                for (int[] direction : directions) {
                    int[][] cells = collectConnectFour(row, col, direction[0], direction[1], piece);
                    if (cells != null) {
                        return cells;
                    }
                }
            }
        }
        return null;
    }

    private int[][] collectConnectFour(int startRow, int startCol, int deltaRow, int deltaCol, Piece piece) {
        int[][] cells = new int[CONNECT_N][2];

        for (int i = 0; i < CONNECT_N; i++) {
            int row = startRow + i * deltaRow;
            int col = startCol + i * deltaCol;

            if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
                return null;
            }
            if (grid[row][col] != piece) {
                return null;
            }

            cells[i][0] = row;
            cells[i][1] = col;
        }

        return cells;
    }
}
