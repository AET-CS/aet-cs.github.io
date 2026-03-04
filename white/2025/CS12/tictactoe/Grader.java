import java.util.ArrayList;
import java.util.Random;

public class Grader {

    private static final int[][] LINES = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },

            { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },

            { 0, 4, 8 }, { 2, 4, 6 } };

    private static MiniMaxPlayer.GameState checkBoard(String s) {
        for (int[] line : LINES) {
            if (s.charAt(line[0]) == s.charAt(line[1]) && s.charAt(line[1]) == s.charAt(line[2])) {
                char c = s.charAt(line[0]);
                if (c == 'x')
                    return MiniMaxPlayer.GameState.X;
                if (c == 'o')
                    return MiniMaxPlayer.GameState.O;
            }
        }
        if (!s.contains(".")) {
            return MiniMaxPlayer.GameState.DRAW;
        }
        return MiniMaxPlayer.GameState.NOT_OVER;
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
            char mmPlayerChar = (game % 2 == 0) ? 'x' : 'o';
            char current = 'x';

            MiniMaxPlayer.GameState state = MiniMaxPlayer.GameState.NOT_OVER;
            while (state == MiniMaxPlayer.GameState.NOT_OVER) {
                if (current == mmPlayerChar) {
                    // MinMax Player
                    int move = p.getMove(board, current) - 1;
                    board = p.makeMove(board, move, current);
                } else {
                    // RandomPlayer
                    ArrayList<Integer> moves = p.getPossibleMoves(board);
                    int move = moves.get(random.nextInt(moves.size()));
                    board = p.makeMove(board, move, current);
                }
                state = checkBoard(board);
                if (state == MiniMaxPlayer.GameState.NOT_OVER) {
                    current = current == 'x' ? 'o' : 'x';
                }
            }

            if ((state == MiniMaxPlayer.GameState.X && mmPlayerChar == 'x')
                    || (state == MiniMaxPlayer.GameState.O && mmPlayerChar == 'o')) {
                mmWins++;
            } else if (state == MiniMaxPlayer.GameState.DRAW) {
                draws++;
            } else {
                randomWins++;
            }
        }

        System.out.println("Games played = " + games);
        System.out.println("MM wins = " + mmWins);
        System.out.println("Random wins = " + randomWins);
        System.out.println("Draws = " + draws);

        System.out.println("\nGRADE: " + (100 - randomWins));
    }
}
