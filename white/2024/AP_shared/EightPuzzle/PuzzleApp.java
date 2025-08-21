import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;

public class PuzzleApp {
    public static void main(String[] args) {
        // Set default values
        int size = 3; // For 8-puzzle (3x3)
        // Uncomment the following line for 15-puzzle (4x4)
        // int size = 4;
        Timer timer = new Timer(500, null);
        timer.start();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sliding Puzzle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Create MVC components
            PuzzleModel model = new PuzzleModel(size);
            PuzzleView view = new PuzzleView(model);
            PuzzleController controller = new PuzzleController(model, view);
            
            // Add components to the frame
            frame.add(view, BorderLayout.CENTER);
            
            // Create a control panel
            JPanel controlPanel = new JPanel();
            JButton newGameButton = new JButton("New Game");
            newGameButton.addActionListener(e -> {
                model.shuffle();
                view.repaint();
            });
            controlPanel.add(newGameButton);

            // Add random move
            JButton randomMoveButton = new JButton("Random Move");
            randomMoveButton.addActionListener(e -> {
                model.randomMove();
                view.repaint();
            });
            controlPanel.add(randomMoveButton);

            // Add solution button
            JButton solveButton = new JButton("Solve");
            solveButton.addActionListener(e -> {
               Stack<Integer> solution =  model.solve(view);
               timer.addActionListener( ee-> {
                   if (!solution.isEmpty()) {
                       int position = solution.pop();
                       System.out.println(position);
                       model.move(position);
                       view.repaint();
                   }
               });
              });
            controlPanel.add(solveButton);

            frame.add(controlPanel, BorderLayout.SOUTH);
            
            // Set frame properties
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
