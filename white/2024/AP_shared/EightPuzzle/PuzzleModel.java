import java.awt.Point;
import java.util.*;

class PuzzleModel {
    private int size;
    private int[][] board;
    private int emptyRow;
    private int emptyCol;
    private final int MAX_SHUFFLES = 1000;

    public PuzzleModel(int size) {
        this.size = size;
        board = new int[size][size];
        initializeBoard();
        shuffle();
    }
    
    private void initializeBoard() {
        // Initialize the board in solved state
        int value = 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = value++;
            }
        }
        
        // Set the last cell as empty (represented by 0)
        emptyRow = size - 1;
        emptyCol = size - 1;
        board[emptyRow][emptyCol] = 0;
    }
    
    public void shuffle() {
        // Simple shuffle: make 1000 random valid moves
        Random random = new Random();
        for (int i = 0; i < MAX_SHUFFLES; i++) {
            // Get all possible moves from current position
            List<Point> possibleMoves = getPossibleMoves();
            
            // Select a random move
            if (!possibleMoves.isEmpty()) {
                int randomIndex = random.nextInt(possibleMoves.size());
                Point move = possibleMoves.get(randomIndex);
                moveTile(move.x, move.y);
            }
        }
    }
    
    private List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        
        // Check all four directions
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir : directions) {
            int newRow = emptyRow + dir[0];
            int newCol = emptyCol + dir[1];
            
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                moves.add(new Point(newRow, newCol));
            }
        }
        
        return moves;
    }
    
    public boolean moveTile(int row, int col) {
        // Check if the tile is adjacent to the empty space
        if ((Math.abs(row - emptyRow) == 1 && col == emptyCol) ||
            (Math.abs(col - emptyCol) == 1 && row == emptyRow)) {
            
            // Move the tile
            board[emptyRow][emptyCol] = board[row][col];
            board[row][col] = 0;
            
            // Update empty position
            emptyRow = row;
            emptyCol = col;
            
            return true;
        }
        return false;
    }
    
    // Move piece at position (0-referenced, row-major order) to the blank
    public boolean move(int position) {
        int row = position / size;
        int col = position % size;
        
        return moveTile(row, col);
    }
    
    // Return a 1D array representation of the board
    public int[] getBoard() {
        int[] result = new int[size * size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result[row * size + col] = board[row][col];
            }
        }
        return result;
    }
    
    // Return a 2D array representation of the board
    public int[][] getBoard2D() {
        int[][] result = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result[row][col] = board[row][col];
            }
        }
        return result;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getTileValue(int row, int col) {
        return board[row][col];
    }
    
    public boolean isSolved() {
        int value = 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // Skip the last cell which should be empty
                if (row == size - 1 && col == size - 1) {
                    if (board[row][col] != 0) {
                        return false;
                    }
                } else if (board[row][col] != value++) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean randomMove() {
        // move a *valid* random tile by calling 'move'
        // this will test that you know how the state is stored
        // and how the main methods work for getting and moving tile
        return false;
    }

    public Stack<Integer> solve(PuzzleView view) {
        // create a new PuzzleSolver, pass it the state
        // and return the solutionPath
        Stack<Integer> solutionPath = new Stack<>();
        return solutionPath;
    }
}
