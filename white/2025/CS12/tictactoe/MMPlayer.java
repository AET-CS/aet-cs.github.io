import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MMPlayer {
    enum GameState {
        X,
        O,
        DRAW,
        NOT_OVER
    }

    static HashMap<BoardPlayerKey, Integer> allGames = new HashMap<BoardPlayerKey, Integer>();

    private static class BoardPlayerKey {
        private final String board;
        private final char player;

        BoardPlayerKey(String board, char player) {
            this.board = board;
            this.player = player;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BoardPlayerKey)) {
                return false;
            }
            BoardPlayerKey other = (BoardPlayerKey) obj;
            return board.equals(other.board) && player == other.player;
        }

        @Override
        public int hashCode() {
            int result = board.hashCode();
            result = 31 * result + player;
            return result;
        }

        @Override
        public String toString() {
            return board + "," + player;
        }
    }

    private final int[][] LINES = {
            { 0, 1, 2 },
            { 3, 4, 5 },
            { 6, 7, 8 },

            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 },

            { 0, 4, 8 },
            { 2, 4, 6 } };

    public ArrayList<Integer> getPossibleMoves(String s) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                result.add(i);
            }
        }
        return result;
    }

    public String makeMove(String board, int i, char player) {
        String newBoard = board.substring(0, i) + player + board.substring(i + 1);
        return newBoard;
    }

    private GameState checkBoard(String s) {
        for (int[] line : LINES) {
            if (s.charAt(line[0]) == s.charAt(line[1]) && s.charAt(line[1]) == s.charAt(line[2])) {
                char c = s.charAt(line[0]);
                if (c == 'x')
                    return GameState.X;
                if (c == 'o')
                    return GameState.O;
            }
        }
        if (!s.contains(".")) {
            return GameState.DRAW;
        }
        return GameState.NOT_OVER;
    }

    private int scoreBoard(GameState g) {
        switch (g) {
            case X:
                return 1;
            case O:
                return -1;
            default:
                return 0;
        }
    }

    private char other(char player) {
        return player == 'o' ? 'x' : 'o';
    }

    private int minimax(String board, int depth, char player) {
        GameState boardState = checkBoard(board);
        if (depth == 0 || boardState != GameState.NOT_OVER) {
            return scoreBoard(boardState);
        }

        if (player == 'x') {
            int value = -1;
            for (int move : getPossibleMoves(board)) {
                String newBoard = makeMove(board, move, 'x');
                value = Math.max(value, minimax(newBoard, depth - 1, 'o'));
            }
            return value;
        }

        else {
            int value = +1;
            for (int move : getPossibleMoves(board)) {
                String newBoard = makeMove(board, move, 'o');
                value = Math.min(value, minimax(newBoard, depth - 1, 'x'));
            }
            return value;
        }
    }

    public int getMove(String board, char player) {
        int bestMove = -1;

        if (player == 'x') {
            int bestScore = -1000;
            for (int move : getPossibleMoves(board)) {
                String newBoard = makeMove(board, move, 'x');
                int score = minimax(newBoard, 10, 'o');
                if (score > bestScore) {
                    bestMove = move;
                    bestScore = score;
                }
            }
        }
        if (player == 'o') {
            int bestScore = 1000;
            for (int move : getPossibleMoves(board)) {
                String newBoard = makeMove(board, move, 'o');
                int score = minimax(newBoard, 10, 'x');
                if (score < bestScore) {
                    bestMove = move;
                    bestScore = score;
                }
            }
        }
        return bestMove + 1;
    }

    private static String boardToSquare(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (i % 3 == 2 && i != s.length() - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String board = ".........";
        MMPlayer p = new MMPlayer();
        ArrayList<Integer> moves = p.getPossibleMoves(board);
        char player = 'o';
        int best_move = p.getMove(board, player);

        System.out.println("board=" + board);
        System.out.println("board (square):\n" + boardToSquare(board));
        System.out.println("player=" + player);
        System.out.println("moves=" + moves);
        System.out.println("best_move=" + best_move);

        System.out.println("Num of boards = " + allGames.size());
        ArrayList<BoardPlayerKey> keys = new ArrayList<BoardPlayerKey>(allGames.keySet());
        Random random = new Random();
        int count = Math.min(100, keys.size());
        for (int i = 0; i < count; i++) {
            BoardPlayerKey key = keys.get(random.nextInt(keys.size()));
            System.out.println("random board=" + key + " score = " + allGames.get(key));
        }
    }

}
