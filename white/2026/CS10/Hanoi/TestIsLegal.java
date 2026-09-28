import java.util.ArrayList;

/**
 * Tests Hanoi.isLegal without opening the window.
 * <p>
 * Run this file (not Hanoi). Each test sets up the pegs, calls
 * isLegal, and compares your answer to the right one.
 * <p>
 * Pegs are written bottom to top, so [3, 1] means disk 3 on the
 * bottom with disk 1 on top of it.
 */
public class TestIsLegal {

  static int passed = 0;
  static int total = 0;

  static void main(String[] args) {
    int[] none = {};

    System.out.println("Empty pegs");
    check(false, 0, 1, none, none, none);                    // nothing to move
    check(false, 1, 0, new int[]{3, 2}, none, none);         // 'from' is empty
    check(true, 0, 2, new int[]{3, 2, 1}, none, none);      // 'to' is empty

    System.out.println("Smaller onto larger");
    check(true, 0, 1, new int[]{3, 1}, new int[]{2}, none);
    check(true, 2, 0, new int[]{4}, none, new int[]{2});

    System.out.println("Larger onto smaller");
    check(false, 1, 0, new int[]{3, 1}, new int[]{2}, none);
    check(false, 0, 2, new int[]{4}, none, new int[]{2});

    System.out.println("Only the TOP disks matter");
    check(true, 0, 1, new int[]{5, 1}, new int[]{4, 2}, none);
    check(false, 1, 0, new int[]{5, 1}, new int[]{4, 2}, none);
    check(true, 0, 1, new int[]{2}, new int[]{5, 4, 3}, none);

    System.out.println("A peg onto itself");
    check(false, 0, 0, new int[]{3, 2, 1}, none, none);

    System.out.println();
    System.out.println(passed + " of " + total + " tests passed.");
  }

  /**
   * Puts the given disks on pegs 0, 1, 2, calls isLegal(from, to),
   * and prints PASS or FAIL.
   */
  static void check(boolean expected, int from, int to, int[] p0, int[] p1, int[] p2) {
    load(p0, p1, p2);
    String before = Hanoi.pegs.toString();
    String call = "isLegal(" + from + ", " + to + ")";
    total++;

    boolean answer;
    try {
      answer = Hanoi.isLegal(from, to);
    } catch (Exception e) {
      System.out.println("  FAIL  " + call + "  pegs " + before
              + "  crashed: " + e);
      return;
    }

    String after = Hanoi.pegs.toString();
    if (!after.equals(before)) {
      System.out.println("  FAIL  " + call + "  pegs " + before
              + "  isLegal changed the pegs to " + after);
    } else if (answer != expected) {
      System.out.println("  FAIL  " + call + "  pegs " + before
              + "  expected " + expected + ", got " + answer);
    } else {
      System.out.println("  pass  " + call + "  pegs " + before
              + "  " + answer);
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
