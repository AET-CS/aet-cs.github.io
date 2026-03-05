package connectfour.controller;

import connectfour.model.Board;
import connectfour.model.GameState;
import connectfour.model.Piece;
import connectfour.player.HumanPlayer;
import connectfour.player.MinMaxPlayer;
import connectfour.player.Player;
import connectfour.player.RandomPlayer;
import connectfour.view.ConnectFourFrame;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {
    private static final int AI_MOVE_DELAY_MS = 450;
    private static final int WIN_BLINK_DURATION_MS = 2400;

    private final ConnectFourFrame frame;
    private final ExecutorService moveExecutor = Executors.newSingleThreadExecutor();

    private Board board;
    private Player redPlayer;
    private Player bluePlayer;
    private Piece currentTurn;
    private GameState gameState;
    private boolean awaitingHumanMove;
    private boolean humanMoveWorkerStarted;
    private int turnRequestId;
    private int moveCount;

    public GameController(ConnectFourFrame frame) {
        this.frame = frame;
        this.board = new Board();
        this.currentTurn = Piece.RED;
        this.gameState = GameState.PLAYING;
        resetTurnTracking();

        frame.onBoardColumnClick(this::handleBoardClick);
        frame.onNewGame(this::startFromDialog);
    }

    public void show() {
        frame.setVisible(true);
        startFromDialog();
    }

    public void startFromDialog() {
        String mode = chooseMode();
        if (mode == null) {
            return;
        }

        if ("Batch Play".equals(mode)) {
            startBatchFromDialog();
            return;
        }

        Player chosenRed = choosePlayer("Red", true, true);
        if (chosenRed == null) {
            return;
        }

        Player chosenBlue = choosePlayer("Blue", true, true);
        if (chosenBlue == null) {
            return;
        }

        startNewGame(chosenRed, chosenBlue);
    }

    public void startNewGame(Player red, Player blue) {
        this.board = new Board();
        this.redPlayer = red;
        this.bluePlayer = blue;
        this.currentTurn = Piece.RED;
        this.gameState = GameState.PLAYING;
        resetTurnTracking();

        frame.setBoard(board);
        frame.setBoardInteractive(false);
        frame.setBatchProgressVisible(false);
        frame.setStatus("New game: " + redPlayer.getName() + " (Red) vs " + bluePlayer.getName() + " (Blue)");
        frame.setMoveLog("Move log: (none)");

        requestNextMove();
    }

    private String chooseMode() {
        String[] options = { "Single Game", "Batch Play" };

        return (String) JOptionPane.showInputDialog(
                frame,
                "Select mode:",
                "Start Mode",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    private void startBatchFromDialog() {
        Player chosenRed = choosePlayer("Red", false, false);
        if (chosenRed == null) {
            return;
        }

        Player chosenBlue = choosePlayer("Blue", false, false);
        if (chosenBlue == null) {
            return;
        }

        Integer gameCount = chooseBatchGameCount();
        if (gameCount == null) {
            return;
        }

        startBatchPlay(chosenRed, chosenBlue, gameCount);
    }

    private Integer chooseBatchGameCount() {
        Integer[] options = { 5, 10, 15, 20, 100 };

        return (Integer) JOptionPane.showInputDialog(
                frame,
                "How many games?",
                "Batch Size",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    private Player choosePlayer(String colorName, boolean allowHuman, boolean showDepthStatus) {
        String[] options = allowHuman
                ? new String[] { "Human", "Random", "MinMax-2", "MinMax-4", "MinMax-6", "MinMax-8", "MinMax-10" }
                : new String[] { "Random", "MinMax-2", "MinMax-4", "MinMax-6", "MinMax-8", "MinMax-10" };

        String selection = (String) JOptionPane.showInputDialog(
                frame,
                "Select " + colorName + " player type:",
                "Player Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selection == null) {
            return null;
        }

        if (allowHuman && "Human".equals(selection)) {
            return new HumanPlayer(colorName + " Human");
        }

        if ("Random".equals(selection)) {
            return new RandomPlayer(colorName + " Random");
        }

        int depth = Integer.parseInt(selection.substring(selection.indexOf('-') + 1));
        MinMaxPlayer minMaxPlayer = new MinMaxPlayer(colorName + " MinMax-" + depth, depth);
        if (showDepthStatus) {
            minMaxPlayer.setDepthDisplayCallback(searchDepth -> SwingUtilities.invokeLater(
                    () -> frame.setStatus(colorName + " MinMax searching depth " + searchDepth + "...")));
        }
        return minMaxPlayer;
    }

    private void startBatchPlay(Player redBatchPlayer, Player blueBatchPlayer, int gameCount) {
        frame.setBoardInteractive(false);
        frame.setBatchProgressVisible(true);
        frame.setBatchProgressMax(gameCount);
        frame.setStatus("Batch running: 0/" + gameCount);
        frame.setMoveLog("Batch mode: " + redBatchPlayer.getName() + " vs " + blueBatchPlayer.getName());

        moveExecutor.submit(() -> {
            int redWins = 0;
            int blueWins = 0;
            int ties = 0;

            for (int gameIndex = 1; gameIndex <= gameCount; gameIndex++) {
                GameState result = playOneSimulation(redBatchPlayer, blueBatchPlayer);
                if (result == GameState.RED_WINS) {
                    redWins++;
                } else if (result == GameState.BLUE_WINS) {
                    blueWins++;
                } else {
                    ties++;
                }

                int completed = gameIndex;
                SwingUtilities.invokeLater(() -> {
                    frame.setStatus("Batch running: " + completed + "/" + gameCount);
                    frame.setBatchProgressValue(completed);
                });
            }

            int finalRedWins = redWins;
            int finalBlueWins = blueWins;
            int finalTies = ties;
            SwingUtilities.invokeLater(() -> showBatchResults(redBatchPlayer, blueBatchPlayer, gameCount, finalRedWins, finalBlueWins, finalTies));
        });
    }

    private GameState playOneSimulation(Player redSimPlayer, Player blueSimPlayer) {
        Board simBoard = new Board();
        Piece simTurn = Piece.RED;
        int moves = 0;

        while (moves < Board.ROWS * Board.COLS) {
            Player currentPlayer = simTurn == Piece.RED ? redSimPlayer : blueSimPlayer;
            int col = currentPlayer.getMove(simBoard.copy(), simTurn);
            if (!simBoard.isValidMove(col)) {
                col = firstValidColumn(simBoard);
                if (col == -1) {
                    return GameState.TIE;
                }
            }

            simBoard.dropPiece(col, simTurn);
            moves++;

            GameState state = simBoard.getGameState();
            if (state != GameState.PLAYING) {
                return state;
            }

            simTurn = simTurn.opposite();
        }

        return GameState.TIE;
    }

    private int firstValidColumn(Board board) {
        for (int col = 0; col < Board.COLS; col++) {
            if (board.isValidMove(col)) {
                return col;
            }
        }
        return -1;
    }

    private void showBatchResults(Player redBatchPlayer, Player blueBatchPlayer, int gameCount, int redWins, int blueWins, int ties) {
        DecimalFormat pct = new DecimalFormat("0.0");
        String message = "Batch complete: " + gameCount + " games\n\n"
                + redBatchPlayer.getName() + " (Red) wins: " + redWins + " (" + pct.format(100.0 * redWins / gameCount) + "%)\n"
                + blueBatchPlayer.getName() + " (Blue) wins: " + blueWins + " (" + pct.format(100.0 * blueWins / gameCount) + "%)\n"
                + "Ties: " + ties + " (" + pct.format(100.0 * ties / gameCount) + "%)";

        frame.setStatus("Batch complete: " + gameCount + " games.");
        frame.setBatchProgressValue(gameCount);
        JOptionPane.showMessageDialog(frame, message, "Batch Results", JOptionPane.INFORMATION_MESSAGE);
        frame.setBatchProgressVisible(false);
    }

    private void requestNextMove() {
        if (gameState != GameState.PLAYING) {
            return;
        }

        Player currentPlayer = getCurrentPlayer();
        int requestId = ++turnRequestId;
        String turnText = currentTurn == Piece.RED ? "Red" : "Blue";
        frame.setStatus(turnText + " turn: " + currentPlayer.getName());

        if (currentPlayer.isHuman()) {
            frame.setBoardInteractive(true);
            awaitingHumanMove = true;
            humanMoveWorkerStarted = false;
        } else {
            awaitingHumanMove = false;
            humanMoveWorkerStarted = false;
            frame.setBoardInteractive(false);
            javax.swing.Timer delayTimer = new javax.swing.Timer(AI_MOVE_DELAY_MS, e -> {
                ((javax.swing.Timer) e.getSource()).stop();
                if (!isCurrentTurnRequest(currentPlayer, currentTurn, requestId)) {
                    return;
                }
                computeAndApplyMove(currentPlayer, currentTurn, requestId, false);
            });
            delayTimer.setRepeats(false);
            delayTimer.start();
        }
    }

    private void handleBoardClick(int col) {
        if (gameState != GameState.PLAYING) {
            return;
        }

        Player currentPlayer = getCurrentPlayer();
        if (!(currentPlayer instanceof HumanPlayer humanPlayer)) {
            return;
        }

        if (!awaitingHumanMove) {
            return;
        }

        humanPlayer.submitMove(col);
        if (!humanMoveWorkerStarted) {
            humanMoveWorkerStarted = true;
            computeAndApplyMove(currentPlayer, currentTurn, turnRequestId, true);
        }
    }

    private void computeAndApplyMove(Player player, Piece turnColor, int requestId, boolean isHumanTurn) {
        if (!isCurrentTurnRequest(player, turnColor, requestId)) {
            return;
        }

        if (!isHumanTurn) {
            frame.setBoardInteractive(false);
        }

        moveExecutor.submit(() -> {
            int col = player.getMove(board.copy(), turnColor);
            SwingUtilities.invokeLater(() -> applyMove(col, turnColor, requestId));
        });
    }

    private void applyMove(int col, Piece moveColor, int requestId) {
        if (!isCurrentTurnRequest(getCurrentPlayer(), moveColor, requestId)) {
            return;
        }

        if (!board.isValidMove(col)) {
            frame.setStatus("Invalid move. Pick another column.");
            requestNextMove();
            return;
        }

        awaitingHumanMove = false;
        humanMoveWorkerStarted = false;
        frame.setBoardInteractive(false);

        int row = board.dropPiece(col, moveColor);
        moveCount++;
        String colorText = moveColor == Piece.RED ? "Red" : "Blue";
        frame.setMoveLog("Move " + moveCount + ": " + colorText + " -> column " + (col + 1));
        frame.setBoard(board);
        frame.animateDrop(col, row, moveColor, this::afterDropAnimation);
    }

    private void afterDropAnimation() {
        this.gameState = board.getGameState();
        frame.setBoard(board);

        if (gameState == GameState.PLAYING) {
            currentTurn = currentTurn.opposite();
            requestNextMove();
            return;
        }

        if (gameState == GameState.RED_WINS || gameState == GameState.BLUE_WINS) {
            String message = gameState == GameState.RED_WINS ? "Game over: Red wins!" : "Game over: Blue wins!";
            frame.setStatus(message);
            int[][] winningCells = board.getWinningCells();
            frame.animateWinningCells(winningCells, WIN_BLINK_DURATION_MS,
                    () -> JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.INFORMATION_MESSAGE));
            return;
        }

        String message = "Game over: Tie.";
        frame.setStatus(message);
        JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private Player getCurrentPlayer() {
        return currentTurn == Piece.RED ? redPlayer : bluePlayer;
    }

    private boolean isCurrentTurnRequest(Player player, Piece turnColor, int requestId) {
        return gameState == GameState.PLAYING
                && requestId == turnRequestId
                && turnColor == currentTurn
                && player == getCurrentPlayer();
    }

    private void resetTurnTracking() {
        this.awaitingHumanMove = false;
        this.humanMoveWorkerStarted = false;
        this.turnRequestId = 0;
        this.moveCount = 0;
    }
}
