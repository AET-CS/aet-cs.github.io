import java.util.ArrayList;

/**
 * Tests Hanoi.move without opening the window.
 * <p>
 * Run this file (not Hanoi). Each test sets up the pegs, calls
 * move, and checks three things:
 * - the pegs afterward are right
 * - view.show was called exactly once, with the same two pegs
 * - view.show was called AFTER the pegs were changed
 * <p>
 * Pegs are written bottom to top, so [3, 1] means disk 3 on the
 * bottom with disk 1 on top of it.
 */
public class TestMove {

  static int passed = 0;
  static int total = 0;

  static void main(String[] args) {
    int[] none = {};

    System.out.println("Onto an empty peg");
    check(0, 2, new int[]{3, 2, 1}, none, none,
            new int[]{3, 2}, none, new int[]{1});
    check(1, 0, none, new int[]{2}, none,
            new int[]{2}, none, none);

    System.out.println("Onto a larger disk");
    check(0, 1, new int[]{3, 1}, new int[]{2}, none,
            new int[]{3}, new int[]{2, 1}, none);
    check(2, 0, new int[]{4}, none, new int[]{3, 2},
            new int[]{4, 2}, none, new int[]{3});

    System.out.println("Only the TOP disk moves");
    check(0, 1, new int[]{5, 4, 1}, none, none,
            new int[]{5, 4}, new int[]{1}, none);
    check(1, 2, none, new int[]{6, 3, 2}, new int[]{5, 4},
            none, new int[]{6, 3}, new int[]{5, 4, 2});

    System.out.println("The third peg is left alone");
    check(2, 1, new int[]{7, 6}, new int[]{5}, new int[]{4, 1},
            new int[]{7, 6}, new int[]{5, 1}, new int[]{4});

    System.out.println();
    System.out.println(passed + " of " + total + " tests passed.");
  }

  /**
   * Puts the given disks on pegs 0, 1, 2, calls move(from, to), and
   * compares the pegs to the expected ones (e0, e1, e2).
   */
  static void check(int from, int to, int[] p0, int[] p1, int[] p2,
                    int[] e0, int[] e1, int[] e2) {
    load(e0, e1, e2);
    String expected = Hanoi.pegs.toString();
    load(p0, p1, p2);
    String before = Hanoi.pegs.toString();

    HanoiView tester = HanoiView.forTesting(Hanoi.pegs);
    Hanoi.view = tester;

    String call = "move(" + from + ", " + to + ")";
    String right = "show(" + from + ", " + to + ")";
    String start = "  FAIL  " + call + "  pegs " + before + "  ";
    total++;

    try {
      Hanoi.move(from, to);
    } catch (Exception e) {
      System.out.println(start + "crashed: " + e);
      return;
    }

    String after = Hanoi.pegs.toString();
    int calls = tester.shownMoves.size();

    if (!after.equals(expected)) {
      System.out.println(start + "expected " + expected + ", got " + after);
    } else if (calls == 0) {
      System.out.println(start + "pegs are right, but view.show was never called");
    } else if (calls > 1) {
      System.out.println(start + "view.show was called " + calls + " times");
    } else if (!tester.shownMoves.get(0).equals(right)) {
      System.out.println(start + "called view." + tester.shownMoves.get(0)
              + ", should be view." + right);
    } else if (!tester.shownPegs.get(0).equals(expected)) {
      System.out.println(start + "view.show was called before the pegs were changed");
    } else {
      System.out.println("  pass  " + call + "  pegs " + before + "  became " + after);
      passed++;
    }
  }

  /**
   * Replaces the contents of Hanoi.pegs.
   */
  static void load(int[]... disks) {
    Hanoi.pegs.clear();
    for (int[] peg : disks) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (int d : peg) {
        list.add(d);
      }
      Hanoi.pegs.add(list);
    }
  }
}
