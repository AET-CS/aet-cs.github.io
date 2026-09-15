public class Jiggler {
    public static void main(String[] args) {
        // define frames per second
        int fps = 60;
        // create canvas
        StdDraw.setCanvasSize(600, 600);
        // this makes the animation magic happen
        StdDraw.enableDoubleBuffering();

        // set a scale
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        // set up an animation loop with a counter
        for (int i=0; i<1000; i++) {
            // clear the screen
            StdDraw.clear();
            // draw the whole frame
            // if you want things to move, the position (and/or size) should probably be based on "i"
            // or, to swap between two frames just check  if (i % 2 == 0)
            StdDraw.setPenColor(250, 25, 60);  // Dark blue/purple
            StdDraw.filledSquare(300+Math.random()*20, 300+Math.random()*20, 50);
            StdDraw.setPenColor(0, 250, 250);
            StdDraw.filledCircle(300 + (50+ i / 5.0)*Math.cos(i / 20.0), 300+(50+ i / 5.0)*Math.sin(i / 20.0), 10);
            // call 'show' to actually show the new frame
            StdDraw.show();
            // pause before drawing. the pause is based on frames per second. 30-60 is good for smooth animation
            // simpler effects might use fps = 2-5
            StdDraw.pause(1000/fps);
        }
    }
}
