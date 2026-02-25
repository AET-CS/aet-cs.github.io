import java.util.ArrayList;
import java.util.Random;

public class MiniMaxPlayer {
    enum GameState {
        X,
        O,
        DRAW,
        NOT_OVER
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
        return 1;
    }

    public int getMove(String board, char player) {
        return 1;
    }

    public static void main(String[] args) {
        MiniMaxPlayer p = new MiniMaxPlayer();
        Random random = new Random();

        int games = 100;
        int mmWins = 0;
        int randomWins = 0;
        int draws = 0;

        for (int game = 0; game < games; game++) {
            String board = ".........";
            char mmPlayer = (game % 2 == 0) ? 'x' : 'o';
            char current = 'x';

            GameState state = GameState.NOT_OVER;
            while (state == GameState.NOT_OVER) {
                if (current == mmPlayer) {
                    // MinMax Player
                    int move = p.getMove(board, current) - 1;
                    board = p.makeMove(board, move, current);
                } else {
                    // RandomPlayer
                    ArrayList<Integer> moves = p.getPossibleMoves(board);
                    int move = moves.get(random.nextInt(moves.size()));
                    board = p.makeMove(board, move, current);
                }
                state = p.checkBoard(board);
                if (state == GameState.NOT_OVER) {
                    current = p.other(current);
                }
            }

            if ((state == GameState.X && mmPlayer == 'x') || (state == GameState.O && mmPlayer == 'o')) {
                mmWins++;
            } else if (state == GameState.DRAW) {
                draws++;
            } else {
                randomWins++;
            }
        }

        System.out.println("Games played = " + games);
        System.out.println("MM wins = " + mmWins);
        System.out.println("Random wins = " + randomWins);
        System.out.println("Draws = " + draws);
    }

}
