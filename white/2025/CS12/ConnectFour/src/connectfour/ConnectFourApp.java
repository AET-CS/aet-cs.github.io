package connectfour;

import connectfour.controller.GameController;
import connectfour.view.ConnectFourFrame;

import javax.swing.SwingUtilities;

public class ConnectFourApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnectFourFrame frame = new ConnectFourFrame();
            GameController controller = new GameController(frame);
            controller.show();
        });
    }
}
