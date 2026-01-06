import java.util.ArrayList;

public class OrbSimulation {
    public static final int FPS = 60;
    public static final double DELTA_T = 1.0 / FPS;
    private static ArrayList<Orb> orbs;

    // Write a createOrbs method to add orbs to the 'orbs' list

    // Main animation loop
    public static void run() {
        // Set up the drawing canvas
        StdDraw.setXscale(0, Orb.WIDTH);
        StdDraw.setYscale(0, Orb.HEIGHT);
        StdDraw.setCanvasSize(Orb.WIDTH, Orb.HEIGHT);
        StdDraw.enableDoubleBuffering();

        // Animation loop
        while (true) {
            // Clear the canvas
            StdDraw.clear(StdDraw.WHITE);

            // Update and draw all orbs
            for (Orb orb : orbs) {
                orb.update(DELTA_T);
                orb.draw();
            }

            // Show the frame
            StdDraw.show();
            StdDraw.pause(1000 / FPS);
        }
    }

    public static void main(String[] args) {
        // createOrbs(); // uncomment when you write this
        run();
    }
}