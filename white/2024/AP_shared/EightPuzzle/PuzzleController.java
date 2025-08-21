import java.awt.*;
import java.awt.event.*;

class PuzzleController {
    private PuzzleModel model;
    private PuzzleView view;
    
    public PuzzleController(PuzzleModel model, PuzzleView view) {
        this.model = model;
        this.view = view;
        
        // Add mouse listener to the view
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point tilePos = view.getTilePosition(e.getX(), e.getY());
                
                if (tilePos != null) {
                    view.animatedMoveTile(tilePos.x, tilePos.y);
                }
            }
        });
    }
}
