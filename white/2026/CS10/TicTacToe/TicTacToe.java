import javax.swing.SwingUtilities;

public class TicTacToe {
    private TicTacToeModel model;
    private final TicTacToeView view;

    public TicTacToe() {
        view = new TicTacToeView();
        view.addMoveListener(this::playMove);
        startGame();
        view.setVisible(true);
    }

    private void startGame() {
        model = new TicTacToeModel();
        view.showBoard(model.board);
        view.setStatus("Player " + model.getCurrentPlayer() + " goes first!");
        view.setMovesEnabled(true);
    }

    private void playMove(int move) {
        if (!model.checkValid(move)) {
            view.setStatus("Choose an empty square from 1 to 9. Player "
                    + model.getCurrentPlayer() + "'s turn.");
            return;
        }

        model.updateBoard(move);
        view.showBoard(model.board);
        if (model.isGameOver()) {
            char winner = model.getWinner();
            if (view.showGameOver(winner == TicTacToeModel.EMPTY
                    ? "It's a draw!" : "Player " + winner + " wins!")) {
                startGame();
            } else {
                view.dispose();
            }
        } else {
            model.nextPlayer();
            view.setStatus("Player " + model.getCurrentPlayer() + "'s turn.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
