public class Tester {
    private static int passed;
    private static int failed;

    public static void main(String[] args) {
        testConstructor();
        testCheckValid();
        testUpdateBoard();
        testGetWinner();
        testIsGameOver();

        System.out.println("\nResults: " + passed + " passed, " + failed + " failed.");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testConstructor() {
        System.out.println("\n=== Constructor ===");
        test("Creates an empty 3x3 board and chooses X or O", () -> {
            TicTacToeModel model = new TicTacToeModel();
            require(model.board != null && model.board.length == 3, "Expected 3 rows");
            for (int row = 0; row < 3; row++) {
                require(model.board[row] != null && model.board[row].length == 3,
                        "Expected 3 columns in row " + (row + 1));
                for (int col = 0; col < 3; col++) {
                    require(model.board[row][col] == TicTacToeModel.EMPTY,
                            "Square " + (row * 3 + col + 1) + " is not empty");
                }
            }
            char player = model.getCurrentPlayer();
            require(player == 'X' || player == 'O', "First player must be X or O");
        });
    }

    private static void testCheckValid() {
        System.out.println("\n=== checkValid ===");
        for (int bad : new int[] {-1, 0, 10, 100}) {
            final int move = bad;
            test("Rejects out-of-range square " + move, () ->
                    require(!board("   ", "   ", "   ").checkValid(move),
                            "Square " + move + " is not on the board"));
        }
        for (int square = 1; square <= 9; square++) {
            final int move = square;
            test("Accepts empty square " + move, () ->
                    require(board("   ", "   ", "   ").checkValid(move),
                            "Square " + move + " is empty"));
        }
        String[] rows = {"X O", " O ", "X  "};
        for (int square = 1; square <= 9; square++) {
            final int move = square;
            final boolean taken = rows[(move - 1) / 3].charAt((move - 1) % 3) != ' ';
            test((taken ? "Rejects occupied" : "Accepts empty") + " square " + move
                    + " on a partly filled board", () ->
                    require(board(rows).checkValid(move) == !taken,
                            "Expected " + !taken + " for square " + move));
        }
        test("Rejects every square on a full board", () -> {
            TicTacToeModel model = board("XOX", "XOO", "OXX");
            for (int square = 1; square <= 9; square++) {
                require(!model.checkValid(square), "Square " + square + " is taken");
            }
        });
        test("checkValid does not change the board", () -> {
            TicTacToeModel model = board("X O", " O ", "X  ");
            for (int square = 0; square <= 10; square++) {
                model.checkValid(square);
            }
            require(model.board[0][0] == 'X' && model.board[1][1] == 'O'
                            && model.board[0][1] == ' ' && model.board[2][2] == ' ',
                    "checkValid must only read the board");
        });
    }

    private static void testUpdateBoard() {
        System.out.println("\n=== updateBoard ===");
        for (char player : new char[] {'X', 'O'}) {
            for (int square = 1; square <= 9; square++) {
                final int move = square;
                final char mark = player;
                test("Places " + mark + " in square " + move, () -> {
                    TicTacToeModel model = board("   ", "   ", "   ");
                    if (model.getCurrentPlayer() != mark) {
                        model.nextPlayer();
                    }
                    require(model.getCurrentPlayer() == mark, "Could not select " + mark);
                    model.updateBoard(move);
                    for (int row = 0; row < 3; row++) {
                        for (int col = 0; col < 3; col++) {
                            char expected = row * 3 + col + 1 == move
                                    ? mark : TicTacToeModel.EMPTY;
                            require(model.board[row][col] == expected,
                                    "Unexpected value in square " + (row * 3 + col + 1));
                        }
                    }
                });
            }
        }
        test("Preserves existing marks when placing a move", () -> {
            TicTacToeModel model = board("X O", " O ", "X  ");
            char player = model.getCurrentPlayer();
            model.updateBoard(9);
            require(model.board[0][0] == 'X' && model.board[0][2] == 'O'
                            && model.board[1][1] == 'O' && model.board[2][0] == 'X',
                    "An existing mark changed");
            require(model.board[2][2] == player, "Expected current player's mark in square 9");
        });
    }

    private static void testGetWinner() {
        System.out.println("\n=== getWinner ===");
        test("Empty board has no winner", () ->
                require(board("   ", "   ", "   ").getWinner() == TicTacToeModel.EMPTY,
                        "An empty board cannot have a winner"));
        test("Partial line has no winner", () ->
                require(board("XX ", " O ", "   ").getWinner() == TicTacToeModel.EMPTY,
                        "Two matching marks are not a win"));

        for (char player : new char[] {'X', 'O'}) {
            final char mark = player;
            for (int row = 0; row < 3; row++) {
                final int line = row;
                test(mark + " wins row " + (line + 1), () ->
                        require(winningBoard(mark, line, 0).getWinner() == mark,
                                "Expected winner " + mark));
            }
        }
        for (char player : new char[] {'X', 'O'}) {
            final char mark = player;
            for (int col = 0; col < 3; col++) {
                final int line = col;
                test(mark + " wins column " + (line + 1), () ->
                        require(winningBoard(mark, line, 1).getWinner() == mark,
                                "Expected winner " + mark));
            }
        }
        for (char player : new char[] {'X', 'O'}) {
            final char mark = player;
            for (int diagonal = 0; diagonal < 2; diagonal++) {
                final int line = diagonal;
                test(mark + " wins diagonal " + (line + 1), () ->
                        require(winningBoard(mark, line, 2).getWinner() == mark,
                                "Expected winner " + mark));
            }
        }
        test("Full draw has no winner", () ->
                require(board("XOX", "XOO", "OXX").getWinner() == TicTacToeModel.EMPTY,
                        "A draw cannot have a winner"));
    }

    private static void testIsGameOver() {
        System.out.println("\n=== isGameOver ===");
        test("Empty board is not over", () ->
                require(!board("   ", "   ", "   ").isGameOver(),
                        "Expected game to continue"));
        test("Partially filled board without a winner is not over", () ->
                require(!board("XO ", " X ", "O  ").isGameOver(),
                        "Expected game to continue"));

        String[] types = {"row", "column", "diagonal"};
        for (int type = 0; type < types.length; type++) {
            for (char player : new char[] {'X', 'O'}) {
                int count = type == 2 ? 2 : 3;
                for (int line = 0; line < count; line++) {
                    final int lineType = type;
                    final int lineNumber = line;
                    final char mark = player;
                    test(mark + " wins " + types[type] + " " + (line + 1), () ->
                            require(winningBoard(mark, lineNumber, lineType).isGameOver(),
                                    "Expected game to be over"));
                }
            }
        }
        test("Full board with no winner is a draw", () ->
                require(board("XOX", "XOO", "OXX").isGameOver(),
                        "A full board must end the game"));
        test("Full board with a winner is over", () ->
                require(board("XOX", "OXO", "OXX").isGameOver(),
                        "A winning full board must end the game"));
    }

    private static TicTacToeModel board(String... rows) {
        TicTacToeModel model = new TicTacToeModel();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                model.board[row][col] = rows[row].charAt(col);
            }
        }
        return model;
    }

    private static TicTacToeModel winningBoard(char mark, int line, int type) {
        TicTacToeModel model = board("   ", "   ", "   ");
        for (int i = 0; i < 3; i++) {
            int row = type == 0 ? line : i;
            int col = type == 0 ? i : type == 1 ? line : line == 0 ? i : 2 - i;
            model.board[row][col] = mark;
        }
        return model;
    }

    private static void test(String description, Runnable check) {
        try {
            check.run();
            passed++;
            System.out.println("PASS: " + description);
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("FAIL: " + description + " - " + error);
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
