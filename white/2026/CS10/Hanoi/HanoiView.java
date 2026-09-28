import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Drawing and animation for the Towers of Hanoi.
 * <p>
 * You do not need to read or change this file.
 * <p>
 * The view reads your pegs (it never changes them). Each peg is an
 * ArrayList<Integer> of disk sizes, bottom disk at index 0, top disk at
 * the end. Disk sizes are 1 (smallest) up to n.
 * <p>
 * Methods you call:
 * new HanoiView(pegs)  open the window showing your pegs
 * running()            true until the user closes the window
 * nextEvent()          wait for the user; returns "solve", "reset",
 * "size", "click", or "quit" (window closed)
 * clickedPeg()         after "click": which peg was clicked (0, 1, 2)
 * size()               the number of disks chosen in the menu
 * show(from, to)       call right AFTER you move a disk in your pegs;
 * animates it and waits until the animation ends
 * refresh()            redraw your pegs without animating (after a reset)
 * select(peg)          highlight a peg (-1 for none)
 * setStatus(text)      put a message at the bottom of the window
 */
public class HanoiView {

  private static final String[] NAMES = {"solve", "reset", "size", "click", "quit"};
  private static final Color BG = new Color(250, 248, 243);
  private static final Color WOOD = new Color(120, 90, 60);
  private static final Color SELECT = new Color(255, 200, 60);
  private static final Color BAD = new Color(220, 30, 30);
  final List<String> shownMoves = new ArrayList<>();   // like "show(0, 2)"
  final List<String> shownPegs = new ArrayList<>();    // the pegs at that moment
  // ---------------------------------------------------------------- state
  private final List<? extends List<Integer>> pegs;      // the student's model
  private final BlockingQueue<int[]> events = new LinkedBlockingQueue<>();
  private final Semaphore stepPermit = new Semaphore(0);
  // ---------------------------------------------------------------- testing
  // A view with no window, used by the test programs. Its show() does not
  // draw anything; it just records each call and what the pegs were then.
  private final boolean testing;
  private List<List<Integer>> shown = new ArrayList<>(); // what is drawn at rest
  private int totalDisks = 1;
  // the disk in flight (0 = none)
  private volatile int flyDisk = 0;
  private volatile int flyFrom, flyTo, flyFromLevel, flyToLevel;
  private volatile double flyT;
  private volatile int badPeg = -1;   // top disk of this peg is drawn red
  private volatile int selectedPeg = -1;
  private int moveCount = 0;
  private int lastPeg = -1;
  private volatile boolean running = true;
  private boolean quitDelivered = false;
  private volatile boolean stepMode = false;
  // ---------------------------------------------------------------- widgets
  private JFrame frame;
  private Board board;
  private JSlider speed;
  private JComboBox<Integer> sizeBox;
  private JCheckBox stepBox;
  private JLabel status, counter;
  private JTextArea log;

  public HanoiView(List<? extends List<Integer>> pegs) {
    this.pegs = pegs;
    this.testing = false;
    onEdt(this::build);
    refresh();
  }

  private HanoiView(List<? extends List<Integer>> pegs, boolean testing) {
    this.pegs = pegs;
    this.testing = testing;
  }

  /**
   * A view with no window, for testing.
   */
  static HanoiView forTesting(List<? extends List<Integer>> pegs) {
    return new HanoiView(pegs, true);
  }

  // ================================================================ student API

  private static List<List<Integer>> copy(List<? extends List<Integer>> src) {
    List<List<Integer>> out = new ArrayList<>();
    for (List<Integer> p : src) out.add(new ArrayList<>(p));
    return out;
  }

  private static int top(List<Integer> p) {
    return p.get(p.size() - 1);
  }

  private static void onEdt(Runnable r) {
    if (SwingUtilities.isEventDispatchThread()) {
      r.run();
      return;
    }
    try {
      SwingUtilities.invokeAndWait(r);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * True until the user closes the window.
   */
  public boolean running() {
    return running;
  }

  /**
   * Waits until the user does something.
   */
  public String nextEvent() {
    if (!running) {
      if (quitDelivered)
        throw new IllegalStateException(
                "The window was closed. Loop with while (view.running()) instead of while (true).");
      quitDelivered = true;
      return "quit";
    }
    try {
      int[] e = events.take();
      lastPeg = e[1];
      return NAMES[e[0]];
    } catch (InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * After a "click" event, the peg that was clicked: 0, 1 or 2.
   */
  public int clickedPeg() {
    return lastPeg;
  }

  /**
   * The number of disks chosen in the menu.
   */
  public int size() {
    final int[] n = new int[1];
    onEdt(() -> n[0] = (Integer) sizeBox.getSelectedItem());
    return n[0];
  }

  /**
   * Highlights one peg, or none if peg is -1.
   */
  public void select(int peg) {
    selectedPeg = peg;
    board.repaint();
  }

  // ================================================================ internals

  /**
   * Shows a message at the bottom of the window.
   */
  public void setStatus(String text) {
    SwingUtilities.invokeLater(() -> status.setText(" " + text));
  }

  /**
   * Redraws the pegs with no animation, clears the log and the move count.
   */
  public void refresh() {
    List<List<Integer>> now = copyPegs();
    int n = 0;
    for (List<Integer> p : now) n += p.size();
    final int total = Math.max(n, 1);
    moveCount = 0;
    onEdt(() -> {
      shown = now;
      totalDisks = total;
      flyDisk = 0;
      badPeg = -1;
      log.setText("");
      counter.setText("Moves: 0");
      status.setForeground(Color.BLACK);
      status.setText(" ");
      board.repaint();
    });
  }

  /**
   * Call this right after your code moves the top disk of peg 'from'
   * onto peg 'to'. Animates the move and returns when it is done.
   */
  public void show(int from, int to) {
    if (testing) {
      shownMoves.add("show(" + from + ", " + to + ")");
      shownPegs.add(pegs.toString());
      return;
    }
    if (SwingUtilities.isEventDispatchThread())
      throw new IllegalStateException("show() cannot be called from a Swing listener");
    if (!running) return;   // window closed: let the student's code finish quickly
    events.clear(); // clicks made while moving are ignored

    List<List<Integer>> before = shown;
    List<List<Integer>> after = copyPegs();
    int depth = recursionDepth();

    // --- does the student's model match "move top of from onto to"?
    String problem = null;
    int disk = 0;
    if (from < 0 || from > 2 || to < 0 || to > 2) {
      problem = "show(" + from + ", " + to + "): pegs are numbered 0, 1, 2";
    } else if (before.get(from).isEmpty()) {
      problem = "show(" + from + ", " + to + "): peg " + from + " was already empty";
    } else {
      disk = top(before.get(from));
      List<List<Integer>> expect = copy(before);
      expect.get(from).remove(expect.get(from).size() - 1);
      expect.get(to).add(disk);
      if (!expect.equals(after))
        problem = "show(" + from + ", " + to + "): your pegs don't match moving disk "
                + disk + " from " + from + " to " + to + ".  Pegs are " + after;
    }

    waitForStep();
    moveCount++;
    final int count = moveCount;
    if (problem != null) {
      final String msg = problem;
      onEdt(() -> {
        shown = after;
        flyDisk = 0;
        counter.setText("Moves: " + count);
        status.setText(" " + msg);
        status.setForeground(BAD);
        addLog(count, depth, from + " → " + to + "   ???");
        board.repaint();
      });
      pauseForStep();
      return;
    }

    boolean illegal = !before.get(to).isEmpty() && top(before.get(to)) < disk;
    final int d = disk;
    onEdt(() -> {
      counter.setText("Moves: " + count);
      addLog(count, depth, from + " → " + to + "   (disk " + d + ")" + (illegal ? "  !" : ""));
    });

    animate(before, after, from, to, disk, illegal);

    if (illegal) {
      String below = "" + top(before.get(to));
      onEdt(() -> {
        status.setForeground(BAD);
        status.setText(" Illegal move " + count + ": disk " + d + " placed on disk " + below
                + ".  Paused: press Step or uncheck Step mode.");
      });
      pauseForStep();
    } else if (after.get(2).size() == totalDisks && totalDisks > 0) {
      onEdt(() -> {
        status.setForeground(Color.BLACK);
        status.setText(" Solved in " + count + " moves.");
      });
    }
  }

  private void animate(List<List<Integer>> before, List<List<Integer>> after,
                       int from, int to, int disk, boolean bad) {
    long ms = duration();
    if (ms > 0) {
      CountDownLatch done = new CountDownLatch(1);
      onEdt(() -> {
        List<List<Integer>> rest = copy(before);
        rest.get(from).remove(rest.get(from).size() - 1);
        shown = rest;
        flyDisk = disk;
        flyFrom = from;
        flyTo = to;
        flyFromLevel = before.get(from).size() - 1;
        flyToLevel = before.get(to).size();
        flyT = 0;
        long start = System.currentTimeMillis();
        javax.swing.Timer timer = new javax.swing.Timer(15, null);
        timer.addActionListener(e -> {
          long total = Math.max(duration(), 1);
          flyT = Math.min(1.0, (System.currentTimeMillis() - start) / (double) total);
          if (flyT >= 1.0 || !running) {
            timer.stop();
            done.countDown();
          }
          board.repaint();
        });
        timer.start();
      });
      try {
        done.await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    onEdt(() -> {
      shown = after;
      flyDisk = 0;
      badPeg = bad ? to : -1;
      board.repaint();
    });
  }

  /**
   * Milliseconds per move from the speed slider (0 = instant).
   */
  private long duration() {
    double s = speed.getValue() / 100.0;
    return Math.round(1500 * (1 - s) * (1 - s));
  }

  private void waitForStep() {
    while (stepMode && running) {
      try {
        if (stepPermit.tryAcquire(50, TimeUnit.MILLISECONDS)) return;
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void pauseForStep() {
    stepMode = true;
    stepPermit.drainPermits();
    onEdt(() -> stepBox.setSelected(true));
    waitForStep();
    onEdt(() -> {
      badPeg = -1;
      board.repaint();
      status.setForeground(Color.BLACK);
      status.setText(" ");
    });
  }

  /**
   * How deep in the recursion is the call to show()? Finds the method
   * that called move(...) and counts how many copies of it are on the stack.
   */
  private int recursionDepth() {
    StackTraceElement[] st = Thread.currentThread().getStackTrace();
    int i = 0;
    while (i < st.length && !(st[i].getClassName().equals(HanoiView.class.getName())
            && st[i].getMethodName().equals("show"))) i++;
    int callerOfMove = i + 2;
    if (callerOfMove >= st.length) return 0;
    String cls = st[callerOfMove].getClassName();
    String name = st[callerOfMove].getMethodName();
    if (name.equals("main")) return 0;
    int count = 0;
    for (int k = callerOfMove; k < st.length; k++)
      if (st[k].getClassName().equals(cls) && st[k].getMethodName().equals(name)) count++;
    return count;
  }

  private void addLog(int count, int depth, String text) {
    String indent = "  ".repeat(Math.max(depth - 1, 0));
    log.append(String.format("%5d  %s%s%n", count, indent, text));
    log.setCaretPosition(log.getDocument().getLength());
  }

  private List<List<Integer>> copyPegs() {
    if (pegs.size() != 3)
      throw new IllegalStateException("pegs must contain exactly 3 lists, found " + pegs.size());
    return copy(pegs);
  }

  // ================================================================ window

  private void build() {
    frame = new JFrame("Towers of Hanoi");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        running = false;
        events.offer(new int[]{4, -1});   // wake up nextEvent()
      }
    });

    board = new Board();
    board.setPreferredSize(new Dimension(720, 440));
    board.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int peg = Math.min(2, Math.max(0, e.getX() * 3 / Math.max(board.getWidth(), 1)));
        events.offer(new int[]{3, peg});
      }
    });

    JButton solve = new JButton("Solve");
    solve.addActionListener(e -> events.offer(new int[]{0, -1}));
    JButton reset = new JButton("Reset");
    reset.addActionListener(e -> events.offer(new int[]{1, -1}));

    Integer[] sizes = new Integer[10];
    for (int i = 0; i < 10; i++) sizes[i] = i + 1;
    sizeBox = new JComboBox<>(sizes);
    sizeBox.setSelectedItem(4);
    sizeBox.addActionListener(e -> events.offer(new int[]{2, -1}));

    speed = new JSlider(0, 100, 50);
    speed.setPreferredSize(new Dimension(140, speed.getPreferredSize().height));

    stepBox = new JCheckBox("Step mode");
    stepBox.addActionListener(e -> stepMode = stepBox.isSelected());
    JButton step = new JButton("Step");
    step.addActionListener(e -> releaseStep());

    counter = new JLabel("Moves: 0");

    JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
    controls.add(new JLabel("Disks"));
    controls.add(sizeBox);
    controls.add(solve);
    controls.add(reset);
    controls.add(Box.createHorizontalStrut(10));
    controls.add(new JLabel("Slow"));
    controls.add(speed);
    controls.add(new JLabel("Fast"));
    controls.add(Box.createHorizontalStrut(10));
    controls.add(stepBox);
    controls.add(step);
    controls.add(Box.createHorizontalStrut(10));
    controls.add(counter);
    for (Component c : controls.getComponents()) c.setFocusable(false);

    log = new JTextArea(20, 46);
    log.setEditable(false);
    log.setFocusable(false);
    log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    JScrollPane logPane = new JScrollPane(log);
    logPane.setBorder(BorderFactory.createTitledBorder("Moves"));

    status = new JLabel(" ");
    status.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));

    JPanel main = new JPanel(new BorderLayout());
    main.add(controls, BorderLayout.NORTH);
    main.add(board, BorderLayout.CENTER);
    main.add(logPane, BorderLayout.EAST);
    main.add(status, BorderLayout.SOUTH);

    // space bar = Step
    main.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "step");
    main.getActionMap().put("step", new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        releaseStep();
      }
    });

    frame.setContentPane(main);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private void releaseStep() {
    if (stepPermit.availablePermits() == 0) stepPermit.release();
  }

  /**
   * The drawing area.
   */
  private class Board extends JPanel {
    Board() {
      setBackground(BG);
    }

    // layout, recomputed from the current size
    double pegX(int p) {
      return getWidth() * (2 * p + 1) / 6.0;
    }

    double baseY() {
      return getHeight() - 50;
    }

    double diskH() {
      return Math.min(40, (getHeight() - 130) / (totalDisks + 1.5));
    }

    double pegTop() {
      return baseY() - diskH() * (totalDisks + 1.2);
    }

    double liftY() {
      return pegTop() - diskH() - 10;
    }

    double diskW(int d) {
      double maxW = getWidth() / 3.0 - 24;
      double minW = Math.min(Math.max(2.5 * diskH(), 0.3 * maxW), 0.6 * maxW);
      if (totalDisks == 1) return (maxW + minW) / 2;
      return minW + (maxW - minW) * (d - 1) / (totalDisks - 1);
    }

    double restY(int level) {
      return baseY() - diskH() * (level + 1);
    } // top edge

    @Override
    protected void paintComponent(Graphics g0) {
      super.paintComponent(g0);
      Graphics2D g = (Graphics2D) g0;
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

      // base
      g.setColor(WOOD);
      g.fillRoundRect(10, (int) baseY(), getWidth() - 20, 12, 6, 6);

      // pegs and labels
      g.setFont(getFont().deriveFont(Font.BOLD, 16f));
      for (int p = 0; p < 3; p++) {
        int x = (int) pegX(p);
        if (p == selectedPeg) {
          g.setColor(SELECT);
          g.fillRoundRect(x - 9, (int) pegTop() - 4, 18, (int) (baseY() - pegTop()) + 8, 9, 9);
        }
        g.setColor(WOOD);
        g.fillRoundRect(x - 5, (int) pegTop(), 10, (int) (baseY() - pegTop()) + 2, 6, 6);
        String label = "" + p;
        g.setColor(Color.DARK_GRAY);
        g.drawString(label, x - g.getFontMetrics().stringWidth(label) / 2, (int) baseY() + 34);
      }

      // resting disks
      List<List<Integer>> s = shown;
      for (int p = 0; p < 3 && p < s.size(); p++) {
        List<Integer> peg = s.get(p);
        for (int level = 0; level < peg.size(); level++)
          drawDisk(g, peg.get(level), pegX(p), restY(level),
                  p == badPeg && level == peg.size() - 1);
      }

      // flying disk
      if (flyDisk > 0) {
        double[] xy = flightPoint();
        drawDisk(g, flyDisk, xy[0], xy[1], false);
      }
    }

    /**
     * Lift, slide, drop, with easing over the whole path.
     */
    double[] flightPoint() {
      double x0 = pegX(flyFrom), y0 = restY(flyFromLevel);
      double x1 = pegX(flyTo), y1 = restY(flyToLevel);
      double top = liftY();
      double up = y0 - top, across = Math.abs(x1 - x0), down = y1 - top;
      double total = up + across + down;
      double t = flyT;
      double e = t * t * (3 - 2 * t); // smoothstep
      double dist = e * total;
      if (total == 0) return new double[]{x1, y1};
      if (dist <= up) return new double[]{x0, y0 - dist};
      dist -= up;
      if (dist <= across) return new double[]{x0 + Math.signum(x1 - x0) * dist, top};
      dist -= across;
      return new double[]{x1, top + dist};
    }

    void drawDisk(Graphics2D g, int d, double cx, double y, boolean bad) {
      double w = diskW(d), h = diskH() - 2;
      float hue = totalDisks == 1 ? 0.55f : 0.62f - 0.55f * (d - 1) / (totalDisks - 1);
      Color c = bad ? BAD : Color.getHSBColor(hue, 0.55f, 0.92f);
      int x = (int) Math.round(cx - w / 2), yy = (int) Math.round(y + 1);
      g.setColor(c);
      g.fillRoundRect(x, yy, (int) w, (int) h, (int) h, (int) h);
      g.setColor(c.darker());
      g.drawRoundRect(x, yy, (int) w, (int) h, (int) h, (int) h);
      if (h >= 14) {
        g.setFont(getFont().deriveFont(Font.BOLD, (float) Math.min(13, h - 3)));
        String label = "" + d;
        FontMetrics fm = g.getFontMetrics();
        g.setColor(bad ? Color.WHITE : new Color(40, 40, 40));
        g.drawString(label, (int) (cx - fm.stringWidth(label) / 2.0),
                (int) (yy + (h + fm.getAscent() - fm.getDescent()) / 2));
      }
    }
  }
}