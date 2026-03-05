package connectfour.player;

import connectfour.model.Board;
import connectfour.model.GameState;
import connectfour.model.Piece;

import java.util.Random;
import java.util.function.IntConsumer;

public class MinMaxPlayer extends Player {
    private static final double SCORE_EPSILON = 1e-9;

    private final int depth;
    private final Random random;
    private IntConsumer depthDisplayCallback;

    public MinMaxPlayer(String name, int maxDepth) {
        super(name);
        this.depth = maxDepth;
        this.random = new Random();
        this.depthDisplayCallback = ignored -> {
        };
    }

    public void setDepthDisplayCallback(IntConsumer depthDisplayCallback) {
        this.depthDisplayCallback = depthDisplayCallback == null ? ignored -> {
        } : depthDisplayCallback;
    }

    @Override
    public int getMove(Board board, Piece color) {
        depthDisplayCallback.accept(depth);

        int bestCol = -1;
        int tiedCount = 0;

        if (color == Piece.BLUE) {
            double bestScore = Double.NEGATIVE_INFINITY;
            for (int col = 0; col < Board.COLS; col++) {
                if (!board.isValidMove(col)) {
                    continue;
                }
                Board next = board.copy();
                next.dropPiece(col, color);
                double score = minimax(next, depth - 1, Piece.RED);
                if (score > bestScore + SCORE_EPSILON) {
                    bestScore = score;
                    bestCol = col;
                    tiedCount = 1;
                } else if (Math.abs(score - bestScore) <= SCORE_EPSILON) {
                    tiedCount++;
                    if (random.nextInt(tiedCount) == 0) {
                        bestCol = col;
                    }
                }
            }
        } else {
            double bestScore = Double.POSITIVE_INFINITY;
            for (int col = 0; col < Board.COLS; col++) {
                if (!board.isValidMove(col)) {
                    continue;
                }
                Board next = board.copy();
                next.dropPiece(col, color);
                double score = minimax(next, depth - 1, Piece.BLUE);
                if (score < bestScore - SCORE_EPSILON) {
                    bestScore = score;
                    bestCol = col;
                    tiedCount = 1;
                } else if (Math.abs(score - bestScore) <= SCORE_EPSILON) {
                    tiedCount++;
                    if (random.nextInt(tiedCount) == 0) {
                        bestCol = col;
                    }
                }
            }
        }

        return bestCol;
    }

    private double minimax(Board board, int depthRemaining, Piece turn) {
        GameState state = board.getGameState();
        if (state != GameState.PLAYING) {
            return evaluateTerminal(state);
        }

        if (depthRemaining <= 0) {
            return 0.0;
        }

        if (turn == Piece.BLUE) {
            double best = Double.NEGATIVE_INFINITY;
            for (int col = 0; col < Board.COLS; col++) {
                if (!board.isValidMove(col)) {
                    continue;
                }
                Board next = board.copy();
                next.dropPiece(col, Piece.BLUE);
                double score = minimax(next, depthRemaining - 1, Piece.RED);
                if (score > best) {
                    best = score;
                }
            }
            return best;
        }

        double best = Double.POSITIVE_INFINITY;
        for (int col = 0; col < Board.COLS; col++) {
            if (!board.isValidMove(col)) {
                continue;
            }
            Board next = board.copy();
            next.dropPiece(col, Piece.RED);
            double score = minimax(next, depthRemaining - 1, Piece.BLUE);
            if (score < best) {
                best = score;
            }
        }
        return best;
    }

    private double evaluateTerminal(GameState state) {
        if (state == GameState.BLUE_WINS) {
            return 100.0;
        }
        if (state == GameState.RED_WINS) {
            return -100.0;
        }
        return 0.0;
    }
}
