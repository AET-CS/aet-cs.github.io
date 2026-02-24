import java.lang.reflect.Method;

public class CheckMinMax {
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
        OffenseDefensePlayer mmPlayer = new OffenseDefensePlayer();
        OffenseDefensePlayer odPlayer = new OffenseDefensePlayer();
        Method mmGetMove = getMmGetMove();

        int mmWins = 0;
        int odWins = 0;
        int draws = 0;
        int mmWinsAsFirst = 0;
        int mmWinsAsSecond = 0;
        int odWinsAsFirst = 0;
        int odWinsAsSecond = 0;

        boolean[] choices = { true, false };
        for (boolean mmFirst : choices) {
            for (boolean mmIsX : choices) {
                for (int game = 0; game < GAMES; game++) {
                    GameResult result = playGame(mmPlayer, odPlayer, mmGetMove, mmFirst, mmIsX);
                    if (result == GameResult.MM_WIN) {
                        mmWins++;
                        if (mmFirst) {
                            mmWinsAsFirst++;
                        } else {
                            mmWinsAsSecond++;
                        }
                    } else if (result == GameResult.OD_WIN) {
                        odWins++;
                        if (mmFirst) {
                            odWinsAsSecond++;
                        } else {
                            odWinsAsFirst++;
                        }
                    } else {
                        draws++;
                    }
                }
            }
        }

        String headerFormat = "%-28s %10s %10s %10s%n";
        String rowFormat = "%-28s %10d %10d %10d%n";
        int separatorLength = 28 + (3 * 11);

        System.out.println("Matchup: MMPlayer vs OffenseDefensePlayer");
        System.out.printf(headerFormat, "Type/Order", "First", "Second", "Total");
        System.out.println(separatorLine(separatorLength));
        System.out.printf(rowFormat, "MMPlayer wins", mmWinsAsFirst, mmWinsAsSecond, mmWins);
        System.out.printf(rowFormat, "OffenseDefense wins", odWinsAsFirst, odWinsAsSecond, odWins);
        System.out.printf("%-28s %10s %10s %10d%n", "Draws", "-", "-", draws);
    }

    private enum GameResult {
        MM_WIN,
        OD_WIN,
        DRAW
    }

    private static GameResult playGame(Player mmPlayer, Player odPlayer, Method mmGetMove,
            boolean mmFirst, boolean mmIsX) {
        Object[] players = new Object[2];
        players[0] = mmFirst ? mmPlayer : odPlayer;
        players[1] = mmFirst ? odPlayer : mmPlayer;
        char mmMark = mmIsX ? 'x' : 'o';
        char odMark = otherMark(mmMark);
        char[] marks = mmFirst ? new char[] { mmMark, odMark } : new char[] { odMark, mmMark };

        char[] board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = '.';
        }

        int currentPlayer = 0;
        char currentMark = marks[currentPlayer];

        while (true) {
            String boardString = new String(board);
            int move = getMove(players[currentPlayer], mmGetMove, boardString, currentMark);
            int index = normalizeMove(move);
            if (index < 0 || index >= 9 || board[index] != '.') {
                index = firstEmptySquare(board);
            }

            if (index == -1) {
                return GameResult.DRAW;
            }

            board[index] = currentMark;

            if (isWin(board, currentMark)) {
                if (mmFirst && currentPlayer == 0 || !mmFirst && currentPlayer == 1) {
                    return GameResult.MM_WIN;
                }
                return GameResult.OD_WIN;
            }

            if (isDraw(board)) {
                return GameResult.DRAW;
            }

            currentPlayer = 1 - currentPlayer;
            currentMark = marks[currentPlayer];
        }
    }

    private static Method getMmGetMove() {
        try {
            Method method = MMPlayer.class.getDeclaredMethod("getMove", String.class, char.class);
            method.setAccessible(true);
            return method;
        } catch (Exception ex) {
            throw new RuntimeException("Unable to access MMPlayer.getMove", ex);
        }
    }

    private static int getMove(Object player, Method mmGetMove, String board, char mark) {
        if (player instanceof MMPlayer) {
            try {
                return (int) mmGetMove.invoke(player, board, mark);
            } catch (Exception ex) {
                throw new RuntimeException("MMPlayer failed", ex);
            }
        }
        if (player instanceof OffenseDefensePlayer) {
            return ((OffenseDefensePlayer) player).getMove(board, mark);
        }
        throw new IllegalArgumentException("Unknown player type.");
    }

    private static int normalizeMove(int move) {
        if (move >= 1 && move <= 9) {
            return move - 1;
        }
        return move;
    }

    private static char otherMark(char mark) {
        return mark == 'x' ? 'o' : 'x';
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

    private static String separatorLine(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append('-');
        }
        return sb.toString();
    }
}
