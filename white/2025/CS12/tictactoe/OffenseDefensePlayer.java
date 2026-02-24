public class OffenseDefensePlayer extends Player {
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

    @Override
    public int getMove(String board, char player) {
        if (board == null || board.length() != 9) {
            throw new IllegalArgumentException("Board must be a 9-character string.");
        }

        if (player != 'x' && player != 'o') {
            throw new IllegalArgumentException("Player must be 'x' or 'o'.");
        }

        char opponent = (player == 'x') ? 'o' : 'x';
        int winMove = findTwoInRow(board, player);
        if (winMove != -1) {
            return winMove;
        }

        int blockMove = findTwoInRow(board, opponent);
        if (blockMove != -1) {
            return blockMove;
        }

        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '.')
                return i + 1;
        }
        return -1;
    }

    private int findTwoInRow(String board, char mark) {
        for (int[] line : WIN_LINES) {
            int emptyIndex = -1;
            int markCount = 0;

            for (int i = 0; i < line.length; i++) {
                char c = board.charAt(line[i]);
                if (c == '.') {
                    emptyIndex = line[i];
                } else if (c == mark) {
                    markCount++;
                }
            }

            if (markCount == 2 && emptyIndex != -1) {
                return emptyIndex + 1;
            }
        }

        return -1;
    }
}
