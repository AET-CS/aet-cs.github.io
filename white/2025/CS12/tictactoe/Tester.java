import java.util.Random;

public class Tester {
    private static final int GAMES = 100;
    private static final int[][] WIN_LINES = {
            { 0, 1, 2 },
            { 3, 4, 5 },
            { 6, 7, 8 },
            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 },
            { 0, 4, 8 },
            { 2, 4, 6 }
    };

    public static void main(String[] args) {
        Random random = new Random();
        int[] wins = { 0, 0 };
        int draws = 0;

        for (int game = 0; game < GAMES; game++) {
            MyPlayer[] players = { new MyPlayer(), new MyPlayer() };

            int startingPlayer = random.nextBoolean() ? 0 : 1;
            char startingMark = random.nextBoolean() ? 'x' : 'o';
            char[] marks = { startingMark, (startingMark == 'x') ? 'o' : 'x' };

            char[] board = new char[9];
            for (int i = 0; i < board.length; i++) {
                board[i] = '.';
            }

            int currentPlayer = startingPlayer;
            char currentMark = marks[currentPlayer];

            while (true) {
                String boardString = new String(board);
                int move = players[currentPlayer].getMove(boardString, currentMark);

                int index = move - 1;
                if (index < 0 || index >= 9 || board[index] != '.') {
                    index = firstEmptySquare(board);
                }

                if (index == -1) {
                    draws++;
                    break;
                }

                board[index] = currentMark;

                if (isWin(board, currentMark)) {
                    wins[currentPlayer]++;
                    break;
                }

                if (isDraw(board)) {
                    draws++;
                    break;
                }

                currentPlayer = 1 - currentPlayer;
                currentMark = marks[currentPlayer];
            }
        }

        System.out.println("MyPlayer 1 wins: " + wins[0]);
        System.out.println("MyPlayer 2 wins: " + wins[1]);
        System.out.println("Draws: " + draws);
    }

    private static boolean isWin(char[] board, char mark) {
        for (int[] line : WIN_LINES) {
            if (board[line[0]] == mark && board[line[1]] == mark && board[line[2]] == mark) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDraw(char[] board) {
        for (char c : board) {
            if (c == '.') {
                return false;
            }
        }
        return true;
    }

    private static int firstEmptySquare(char[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '.') {
                return i;
            }
        }
        return -1;
    }
}
