package connectfour.player;

import connectfour.model.Board;
import connectfour.model.GameState;
import connectfour.model.Piece;

import java.util.function.IntConsumer;

public class MinMaxIDPlayer extends Player {
    private final int maxDepth;
    private final long moveTimeLimitMs;

    private IntConsumer depthDisplayCallback;
    private IntConsumer bestMoveUpdateCallback;

    public MinMaxIDPlayer(String name, int maxDepth, long moveTimeLimitMs) {
        super(name);
        this.maxDepth = maxDepth;
        this.moveTimeLimitMs = moveTimeLimitMs;
        this.depthDisplayCallback = ignored -> {
        };
        this.bestMoveUpdateCallback = ignored -> {
        };
    }

    public void setDepthDisplayCallback(IntConsumer depthDisplayCallback) {
        this.depthDisplayCallback = depthDisplayCallback == null ? ignored -> {
        } : depthDisplayCallback;
    }

    public void setBestMoveUpdateCallback(IntConsumer bestMoveUpdateCallback) {
        this.bestMoveUpdateCallback = bestMoveUpdateCallback == null ? ignored -> {
        } : bestMoveUpdateCallback;
    }

    @Override
    public int getMove(Board board, Piece color) {
        int bestMoveSoFar = firstValidColumn(board);
        if (bestMoveSoFar == -1) {
            return -1;
        }

        long deadlineNanos = System.nanoTime() + moveTimeLimitMs * 1_000_000L;

        for (int depth = 1; depth <= maxDepth; depth++) {
            if (isTimeUp(deadlineNanos)) {
                break;
            }

            depthDisplayCallback.accept(depth);

            SearchResult depthResult = searchAtDepth(board, color, depth, deadlineNanos, bestMoveSoFar);
            if (depthResult.bestMove != -1) {
                bestMoveSoFar = depthResult.bestMove;
                bestMoveUpdateCallback.accept(bestMoveSoFar);
            }

            if (depthResult.timedOut) {
                break;
            }
        }

        return bestMoveSoFar;
    }

    private SearchResult searchAtDepth(Board board, Piece color, int depth, long deadlineNanos, int fallbackMove) {
        int bestMove = fallbackMove;

        if (color == Piece.BLUE) {
            double bestScore = Double.NEGATIVE_INFINITY;
            for (int col = 0; col < Board.COLS; col++) {
                if (isTimeUp(deadlineNanos)) {
                    return new SearchResult(bestMove, true);
                }
                if (!board.isValidMove(col)) {
                    continue;
                }

                Board next = board.copy();
                next.dropPiece(col, color);

                SearchScore score = minimax(next, depth - 1, Piece.RED, deadlineNanos);
                if (score.timedOut) {
                    return new SearchResult(bestMove, true);
                }

                if (score.value > bestScore) {
                    bestScore = score.value;
                    bestMove = col;
                }
            }
        } else {
            double bestScore = Double.POSITIVE_INFINITY;
            for (int col = 0; col < Board.COLS; col++) {
                if (isTimeUp(deadlineNanos)) {
                    return new SearchResult(bestMove, true);
                }
                if (!board.isValidMove(col)) {
                    continue;
                }

                Board next = board.copy();
                next.dropPiece(col, color);

                SearchScore score = minimax(next, depth - 1, Piece.BLUE, deadlineNanos);
                if (score.timedOut) {
                    return new SearchResult(bestMove, true);
                }

                if (score.value < bestScore) {
                    bestScore = score.value;
                    bestMove = col;
                }
            }
        }

        return new SearchResult(bestMove, false);
    }

    private SearchScore minimax(Board board, int depthRemaining, Piece turn, long deadlineNanos) {
        if (isTimeUp(deadlineNanos)) {
            return new SearchScore(0.0, true);
        }

        GameState state = board.getGameState();
        if (state != GameState.PLAYING) {
            return new SearchScore(evaluateTerminal(state), false);
        }

        if (depthRemaining <= 0) {
            return new SearchScore(0.0, false);
        }

        if (turn == Piece.BLUE) {
            double best = Double.NEGATIVE_INFINITY;
            for (int col = 0; col < Board.COLS; col++) {
                if (isTimeUp(deadlineNanos)) {
                    return new SearchScore(0.0, true);
                }
                if (!board.isValidMove(col)) {
                    continue;
                }

                Board next = board.copy();
                next.dropPiece(col, Piece.BLUE);
                SearchScore score = minimax(next, depthRemaining - 1, Piece.RED, deadlineNanos);
                if (score.timedOut) {
                    return score;
                }
                if (score.value > best) {
                    best = score.value;
                }
            }
            return new SearchScore(best, false);
        }

        double best = Double.POSITIVE_INFINITY;
        for (int col = 0; col < Board.COLS; col++) {
            if (isTimeUp(deadlineNanos)) {
                return new SearchScore(0.0, true);
            }
            if (!board.isValidMove(col)) {
                continue;
            }

            Board next = board.copy();
            next.dropPiece(col, Piece.RED);
            SearchScore score = minimax(next, depthRemaining - 1, Piece.BLUE, deadlineNanos);
            if (score.timedOut) {
                return score;
            }
            if (score.value < best) {
                best = score.value;
            }
        }
        return new SearchScore(best, false);
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

    private boolean isTimeUp(long deadlineNanos) {
        return System.nanoTime() >= deadlineNanos;
    }

    private int firstValidColumn(Board board) {
        for (int col = 0; col < Board.COLS; col++) {
            if (board.isValidMove(col)) {
                return col;
            }
        }
        return -1;
    }

    private static class SearchResult {
        private final int bestMove;
        private final boolean timedOut;

        private SearchResult(int bestMove, boolean timedOut) {
            this.bestMove = bestMove;
            this.timedOut = timedOut;
        }
    }

    private static class SearchScore {
        private final double value;
        private final boolean timedOut;

        private SearchScore(double value, boolean timedOut) {
            this.value = value;
            this.timedOut = timedOut;
        }
    }
}
