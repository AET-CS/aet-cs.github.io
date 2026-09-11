import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.concurrent.*;

/**
 * Drawing support and user interface for fixed-point pictures. You do not need
 * to read this file or change anything in it. Everything it draws, it draws
 * from arrays that you build.
 * <p>
 * The pattern is: wait for something to happen, then hand over fresh arrays.
 * <p>
 * Cobweb.open(catalog);
 * while (true) {
 * String what = Cobweb.nextEvent();
 * ... build xs, ys, orbit ...
 * Cobweb.drawCurve(xs, ys);
 * Cobweb.drawCobweb(o);
 * }
 */
public class Cobweb {

    // No part of the plane outside this box is ever shown.
    public static final double LIMIT = 5.0;

    private static final Color CURVE = new Color(20, 90, 180);
    private static final Color WEB = new Color(205, 70, 40, 50);
    private static final Color IDENTITY = new Color(150, 150, 150);
    private static final Color AXES = new Color(170, 170, 170);
    private static final Color BG = Color.WHITE;

    // ---- the recorded scene ------------------------------------------------

    private static double[] curveX, curveY, orbit;
    private static int orbitSteps = 0; // how much of the web to show

    // ---- the view ----------------------------------------------------------

    private static double vx0 = -2, vx1 = 2, vy0 = -2, vy1 = 2;
    private static double homeHalf = 2;
    private static int delay = 25;

    // ---- interface state ---------------------------------------------------

    private static Function[] catalog;
    private static Function current;
    private static double parameter;
    private static int orbitLength = 50;
    private static double clickX;
    private static double mouseX, mouseY;
    private static boolean mouseInCanvas;

    private static JFrame frame;
    private static Canvas canvas;
    private static JSlider slider;
    private static JTextField rField;
    // The slider's integer model is much finer than the pixels, so an arrow key
    // moves r by a hair rather than by a pixel's worth.
    private static final int TICKS = 10000;
    // True while we are moving the slider ourselves; the change listener must
    // not then snap r back onto the slider's grid.
    private static boolean syncing = false;
    private static JLabel status;
    private static final BlockingQueue<String> events = new LinkedBlockingQueue<>();

    // Opens the window. The first entry of the catalog is selected to start.
    public static void open(Function[] functions) {
        catalog = functions;
        current = functions[0];
        parameter = current.rStart();
        setHome(current.window());

        try {
            SwingUtilities.invokeAndWait(Cobweb::build);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void build() {
        frame = new JFrame("Cobweb");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Function");
        ButtonGroup group = new ButtonGroup();
        for (Function fn : catalog) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(fn.name(), fn == current);
            item.addActionListener(e -> selectFunction(fn));
            group.add(item);
            menu.add(item);
        }
        bar.add(menu);

        JMenu view = new JMenu("View");
        JMenuItem reset = new JMenuItem("Reset view");
        reset.addActionListener(e -> {
            setHome(current.window());
            post("view");
        });
        view.add(reset);
        bar.add(view);

        JMenu orbit = new JMenu("Orbit");
        int[] lengths = { 50, 100, 250, 500, 1000 };
        for (int length : lengths) {
            JMenuItem item = new JMenuItem("" + length);
            item.addActionListener(e -> setOrbitLength(length));
            orbit.add(item);
        }
        bar.add(orbit);
        frame.setJMenuBar(bar);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(720, 720));
        frame.add(canvas, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout(8, 0));
        slider = new JSlider(0, TICKS, 0);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (syncing || !current.hasR())
                    return;
                double t = slider.getValue() / (double) TICKS;
                parameter = current.rMin() + t * (current.rMax() - current.rMin());
                rField.setText(String.format("%.4f", parameter));
                post(slider.getValueIsAdjusting() ? "parameter" : "release");
            }
        });
        south.add(new JLabel(" r "), BorderLayout.WEST);
        south.add(slider, BorderLayout.CENTER);

        rField = new JTextField(8);
        rField.setToolTipText("type an exact value and press Enter");
        rField.addActionListener(e -> commitField());
        south.add(rField, BorderLayout.EAST);
        status = new JLabel(" ");
        south.add(status, BorderLayout.SOUTH);
        frame.add(south, BorderLayout.SOUTH);

        syncSlider();

        canvas.setFocusable(true);
        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                canvas.press(e);
            }

            public void mouseReleased(MouseEvent e) {
                canvas.release(e);
            }

            public void mouseEntered(MouseEvent e) {
                canvas.move(e);
            }

            public void mouseExited(MouseEvent e) {
                mouseInCanvas = false;
                repaint();
            }
        });
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                canvas.drag(e);
            }

            public void mouseMoved(MouseEvent e) {
                canvas.move(e);
            }
        });
        canvas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                int size = e.isShiftDown() ? 100 : 1; // shift for a coarser step
                if (k == KeyEvent.VK_LEFT)
                    nudge(-size);
                if (k == KeyEvent.VK_RIGHT)
                    nudge(size);
            }

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == '+' || c == '=') {
                    zoom(1 / 1.3);
                    post("view");
                } else if (c == '-' || c == '_') {
                    zoom(1.3);
                    post("view");
                } else if (c == 'r' || c == 'R') {
                    setHome(current.window());
                    post("view");
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas.requestFocusInWindow();
        post("start");
    }

    private static void setOrbitLength(int i) {
        orbitLength = i;
        post("orbit");
    }

    private static void selectFunction(Function fn) {
        current = fn;
        parameter = fn.rStart();
        setHome(fn.window());
        orbit = null;
        syncSlider();
        post("function");
    }

    private static void syncSlider() {
        syncing = true;
        slider.setEnabled(current.hasR());
        if (current.hasR()) {
            double t = (parameter - current.rMin()) / (current.rMax() - current.rMin());
            slider.setValue((int) Math.round(t * TICKS));
        }
        if (rField != null) {
            rField.setEnabled(current.hasR());
            rField.setText(current.hasR() ? String.format("%.4f", parameter) : "");
        }
        syncing = false;
    }

    // Reads an exact value typed into the box.
    private static void commitField() {
        if (!current.hasR())
            return;
        try {
            double v = Double.parseDouble(rField.getText().trim());
            parameter = Math.max(current.rMin(), Math.min(current.rMax(), v));
        } catch (NumberFormatException ex) {
            // not a number: put the old value back and carry on
        }
        syncSlider();
        canvas.requestFocusInWindow();
        post("release");
    }

    // Moves r by n thousandths. A flat step, not a fraction of the range, so
    // an arrow key means the same thing whatever function is selected.
    private static final double STEP = 0.001;

    private static void nudge(int n) {
        if (!current.hasR())
            return;
        double v = Math.round((parameter + n * STEP) / STEP) * STEP; // stay on the grid
        parameter = Math.max(current.rMin(), Math.min(current.rMax(), v));
        syncSlider();
        post("release");
    }

    private static void setHome(double half) {
        homeHalf = Math.min(half, LIMIT);
        vx0 = vy0 = -homeHalf;
        vx1 = vy1 = homeHalf;
    }

    // Only the most recent view or parameter event is worth keeping; a fast drag
    // must not build a backlog of repaints.
    private static void post(String what) {
        if (what.equals("view") || what.equals("parameter"))
            events.remove(what);
        events.offer(what);
    }

    // ---- what student code calls -------------------------------------------

    /**
     * Blocks until the user does something. Returns one of:
     * "start", "click", "view", "function", "parameter", "release", "orbit".
     */
    public static String nextEvent() {
        try {
            return events.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Function getFunction() {
        return current;
    }

    public static double getParameter() {
        return parameter;
    }

    public static int getOrbitLength() {
        return orbitLength;
    }

    public static double getClickX() {
        return clickX;
    }

    public static double viewLo() {
        return vx0;
    }

    public static double viewHi() {
        return vx1;
    }

    /** Records the curve through (xs[i], ys[i]) and repaints. */
    public static void drawCurve(double[] xs, double[] ys) {
        if (xs == null || ys == null) {
            hint("drawCurve was handed a null array -- sampleX or applyF is not returning anything yet.");
            return;
        }
        if (xs.length != ys.length)
            throw new IllegalArgumentException("drawCurve: xs has length " + xs.length
                    + " but ys has length " + ys.length + " -- they must match");
        curveX = xs;
        curveY = ys;
        repaint();
    }

    /** Shows the whole cobweb at once. */
    public static void drawCobweb(double[] o) {
        orbit = check(o);
        if (orbit == null) {
            repaint();
            return;
        }
        orbitSteps = o.length;
        repaint();
    }

    /** Shows the cobweb one step at a time. */
    public static void animateCobweb(double[] o) {
        orbit = check(o);
        if (orbit == null) {
            repaint();
            return;
        }
        for (int i = 0; i <= o.length; i++) {
            orbitSteps = i;
            repaint();
            if (delay > 0)
                sleep(delay);
            if (!events.isEmpty())
                break; // something new happened; stop animating
        }
        orbitSteps = o.length;
        repaint();
    }

    /** Milliseconds per cobweb step; 0 turns the animation off. */
    public static void initAnimation(int ms) {
        delay = ms;
    }

    /** Writes the picture to a png. */
    public static void save(String filename) {
        try {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                    canvas.getWidth(), canvas.getHeight(), java.awt.image.BufferedImage.TYPE_INT_RGB);
            Graphics2D g = img.createGraphics();
            canvas.paintScene(g, canvas.getWidth(), canvas.getHeight());
            g.dispose();
            javax.imageio.ImageIO.write(img, "png", new java.io.File(filename));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ---- internals ---------------------------------------------------------

    private static double[] check(double[] o) {
        if (o == null) {
            hint("the cobweb was handed a null array -- orbit is not returning anything yet.");
            return null;
        }
        if (o.length == 0)
            throw new IllegalArgumentException("cobweb: orbit must contain a starting value");
        return o;
    }

    // Says a thing once, so a half-finished program explains itself instead of
    // filling the console with the same line sixty times a second.
    private static final java.util.Set<String> said = new java.util.HashSet<>();

    private static void hint(String message) {
        if (said.add(message))
            System.out.println("[Cobweb] " + message);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    private static void zoom(double factor) {
        double cx = (vx0 + vx1) / 2, cy = (vy0 + vy1) / 2;
        double half = (vx1 - vx0) / 2 * factor;
        half = Math.min(half, LIMIT);
        vx0 = cx - half;
        vx1 = cx + half;
        vy0 = cy - half;
        vy1 = cy + half;
    }

    private static void repaint() {
        if (canvas != null)
            canvas.repaint();
    }

    // ---- the drawing surface ----------------------------------------------

    private static class Canvas extends JPanel {

        private int pressPx, pressPy;
        private double anchorX, anchorY;
        private double moved;

        void press(MouseEvent e) {
            requestFocusInWindow();
            move(e);
            pressPx = e.getX();
            pressPy = e.getY();
            anchorX = ux(e.getX());
            anchorY = uy(e.getY());
            moved = 0;
        }

        void drag(MouseEvent e) {
            double dx = ux(e.getX()) - anchorX, dy = uy(e.getY()) - anchorY;
            moved += Math.abs(e.getX() - pressPx) + Math.abs(e.getY() - pressPy);
            vx0 -= dx;
            vx1 -= dx;
            vy0 -= dy;
            vy1 -= dy;
            clampView();
            pressPx = e.getX();
            pressPy = e.getY();
            move(e);
            post("view");
            repaint();
        }

        void move(MouseEvent e) {
            mouseX = ux(e.getX());
            mouseY = uy(e.getY());
            mouseInCanvas = true;
            repaint();
        }

        void release(MouseEvent e) {
            if (moved < 6) { // barely moved: that was a click
                clickX = ux(e.getX());
                post("click");
            }
        }

        private void clampView() {
            double half = (vx1 - vx0) / 2;
            double cx = Math.max(-LIMIT + half, Math.min(LIMIT - half, (vx0 + vx1) / 2));
            double cy = Math.max(-LIMIT + half, Math.min(LIMIT - half, (vy0 + vy1) / 2));
            if (half >= LIMIT) {
                cx = 0;
                cy = 0;
            }
            vx0 = cx - half;
            vx1 = cx + half;
            vy0 = cy - half;
            vy1 = cy + half;
        }

        private double ux(int px) {
            return vx0 + (vx1 - vx0) * px / Math.max(1, getWidth());
        }

        private double uy(int py) {
            return vy1 - (vy1 - vy0) * py / Math.max(1, getHeight());
        }

        private double px(double x) {
            return (x - vx0) / (vx1 - vx0) * getWidth();
        }

        private double py(double y) {
            return (vy1 - y) / (vy1 - vy0) * getHeight();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintScene((Graphics2D) g, getWidth(), getHeight());
        }

        void paintScene(Graphics2D g, int w, int h) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(BG);
            g.fillRect(0, 0, w, h);

            g.setColor(AXES);
            g.setStroke(new BasicStroke(1f));
            if (vy0 <= 0 && vy1 >= 0)
                line(g, vx0, 0, vx1, 0);
            if (vx0 <= 0 && vx1 >= 0)
                line(g, 0, vy0, 0, vy1);

            g.setColor(IDENTITY);
            g.setStroke(new BasicStroke(1.5f));
            double a = Math.min(vx0, vy0), b = Math.max(vx1, vy1);
            line(g, a, a, b, b);

            if (curveX != null) {
                g.setColor(CURVE);
                g.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                double span = vy1 - vy0;
                Path2D.Double path = new Path2D.Double();
                boolean started = false;
                for (int i = 0; i < curveX.length; i++) {
                    double x = curveX[i], y = curveY[i];
                    boolean usable = !Double.isNaN(y) && !Double.isInfinite(y)
                            && y > vy0 - 4 * span && y < vy1 + 4 * span;
                    if (!usable) {
                        started = false;
                        continue;
                    }
                    if (!started) {
                        path.moveTo(px(x), py(y));
                        started = true;
                    } else
                        path.lineTo(px(x), py(y));
                }
                g.draw(path);
            }

            if (orbit != null) {
                g.setColor(WEB);
                g.setStroke(new BasicStroke(1.6f));
                for (int i = 0; i < orbitSteps - 1 && i < orbit.length - 1; i++) {
                    double x = orbit[i], y = orbit[i + 1];
                    if (!on(x) || !on(y))
                        break;
                    line(g, x, x, x, y);
                    line(g, x, y, y, y);
                }
                if (orbit.length > 0 && on(orbit[0])) {
                    double d = 7;
                    g.fill(new Ellipse2D.Double(px(orbit[0]) - d / 2, py(orbit[0]) - d / 2, d, d));
                }
            }

            if (status != null) {
                String s = current.name()
                        + (current.hasR() ? String.format("     r = %.4f", parameter) : "")
                        + (orbit == null ? "     click a starting point"
                                : String.format("     start x = %.4f end x = %.4f", orbit[0], orbit[orbit.length - 1]))
                        + (mouseInCanvas ? String.format("     pointer (%.4f, %.4f)", mouseX, mouseY) : "");
                status.setText(s + "     drag to pan, + / - to zoom, R to reset");
            }
        }

        private boolean on(double v) {
            return !Double.isNaN(v) && !Double.isInfinite(v) && Math.abs(v) <= LIMIT * 4;
        }

        private void line(Graphics2D g, double x0, double y0, double x1, double y1) {
            g.draw(new Line2D.Double(px(x0), py(y0), px(x1), py(y1)));
        }
    }
}
