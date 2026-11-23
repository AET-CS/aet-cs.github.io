import java.util.ArrayList;
import java.util.Random;

/**
 * Tests for VendingMachine. Run it after each part.
 *
 * Grids in this file are written as strings, one per row:
 *     "chips:1.50  gum:0.75  -"
 * means chips ($1.50) in col 0, gum ($0.75) in col 1, and an empty slot in col 2.
 *
 * In the output, a snack is shown with the slot it started in, like
 *     mints $0.75 [0][3]
 * so you can tell two snacks with the same name apart.
 */
public class Tester {
    private static int passed;
    private static int failed;
    private static int sectionPassed;
    private static int sectionFailed;
    private static ArrayList<String> summary = new ArrayList<>();

    // The grid a test started with, used to label snacks with their starting slot.
    private static Snack[][] start;

    public static void main(String[] args) {
        testRestock();
        testBuyCheapestFull();
        testBuyCheapest();

        System.out.println("\n=== Summary ===");
        for (String line : summary) {
            System.out.println(line);
        }
        System.out.println("Total: " + passed + " passed, " + failed + " failed.");
    }

    // ==================================================================
    // Part (a): restock
    // ==================================================================

    private static void testRestock() {
        section("Part (a): restock");

        test("FRQ example", () -> {
            VendingMachine m = machine(
                    "chips:1  pretzels:1  -      -",
                    "gum:1    mints:1     candy:1 cookies:1",
                    "-        granola:1   -      popcorn:1");
            Snack[][] expected = copy(start);
            Snack crackers = new Snack("crackers", 1.00);
            Snack jerky = new Snack("jerky", 2.00);

            checkValue("restock(0, crackers)", true, m.restock(0, crackers));
            expected[0][2] = crackers;
            checkGrid("restock(0, crackers)", expected, m);

            checkValue("restock(1, jerky)", false, m.restock(1, jerky));
            checkGrid("restock(1, jerky)", expected, m);

            checkValue("restock(2, jerky)", true, m.restock(2, jerky));
            expected[2][0] = jerky;
            checkGrid("restock(2, jerky)", expected, m);
        });

        test("Empty machine: each row fills column 0", () -> {
            VendingMachine m = new VendingMachine(3, 4);
            start = new Snack[3][4];
            Snack[][] expected = copy(start);
            for (int r = 0; r < 3; r++) {
                Snack s = new Snack("snack" + r, 1.00);
                checkValue("restock(" + r + ", snack" + r + ")", true, m.restock(r, s));
                expected[r][0] = s;
                checkGrid("restock(" + r + ", snack" + r + ")", expected, m);
            }
        });

        test("Fill one row left to right, then it is full", () -> {
            VendingMachine m = new VendingMachine(2, 4);
            start = new Snack[2][4];
            Snack[][] expected = copy(start);
            for (int c = 0; c < 4; c++) {
                Snack s = new Snack("s" + c, 1.00);
                checkValue("restock(1, s" + c + ")", true, m.restock(1, s));
                expected[1][c] = s;
                checkGrid("restock(1, s" + c + ")", expected, m);
            }
            checkValue("restock(1, extra)", false, m.restock(1, new Snack("extra", 1.00)));
            checkGrid("restock(1, extra)", expected, m);
        });

        test("Full row returns false and changes nothing", () -> {
            VendingMachine m = machine(
                    "a:1 b:1 c:1",
                    "d:1 -   -",
                    "e:1 f:1 g:1");
            Snack[][] expected = copy(start);
            checkValue("restock(0, new)", false, m.restock(0, new Snack("new", 1.00)));
            checkGrid("restock(0, new)", expected, m);
            checkValue("restock(2, new)", false, m.restock(2, new Snack("new", 1.00)));
            checkGrid("restock(2, new)", expected, m);
        });

        test("Only the last slot in the row is empty", () -> {
            VendingMachine m = machine(
                    "a:1 b:1 c:1 d:1 -",
                    "-   -   -   -   -");
            Snack[][] expected = copy(start);
            Snack s = new Snack("new", 1.00);
            checkValue("restock(0, new)", true, m.restock(0, s));
            expected[0][4] = s;
            checkGrid("restock(0, new)", expected, m);
        });

        test("Gaps in the middle of a row: leftmost gap first", () -> {
            VendingMachine m = machine(
                    "a:1 - b:1 - c:1 -",
                    "d:1 e:1 f:1 g:1 h:1 i:1");
            Snack[][] expected = copy(start);
            int[] cols = {1, 3, 5};
            for (int i = 0; i < cols.length; i++) {
                Snack s = new Snack("new" + i, 1.00);
                checkValue("restock(0, new" + i + ")", true, m.restock(0, s));
                expected[0][cols[i]] = s;
                checkGrid("restock(0, new" + i + ")", expected, m);
            }
            checkValue("restock(0, new3)", false, m.restock(0, new Snack("new3", 1.00)));
            checkGrid("restock(0, new3)", expected, m);
        });

        test("Only the requested row changes (other rows have empty slots)", () -> {
            VendingMachine m = machine(
                    "-   -   -",
                    "a:1 -   b:1",
                    "-   -   -");
            Snack[][] expected = copy(start);
            Snack s = new Snack("new", 1.00);
            checkValue("restock(1, new)", true, m.restock(1, s));
            expected[1][1] = s;
            checkGrid("restock(1, new)", expected, m);
        });

        test("1 x 1 machine", () -> {
            VendingMachine m = new VendingMachine(1, 1);
            start = new Snack[1][1];
            Snack[][] expected = copy(start);
            Snack s = new Snack("only", 1.00);
            checkValue("restock(0, only)", true, m.restock(0, s));
            expected[0][0] = s;
            checkGrid("restock(0, only)", expected, m);
            checkValue("restock(0, another)", false, m.restock(0, new Snack("another", 1.00)));
            checkGrid("restock(0, another)", expected, m);
        });

        test("Tall machine with 1 column", () -> {
            VendingMachine m = machine("a:1", "-", "b:1", "-", "-");
            Snack[][] expected = copy(start);
            for (int r = 0; r < 5; r++) {
                Snack s = new Snack("new" + r, 1.00);
                boolean wasEmpty = start[r][0] == null;
                checkValue("restock(" + r + ", new" + r + ")", wasEmpty, m.restock(r, s));
                if (wasEmpty) {
                    expected[r][0] = s;
                }
                checkGrid("restock(" + r + ", new" + r + ")", expected, m);
            }
        });

        endSection();
    }

    // ==================================================================
    // Part (b1): buyCheapestFull  (every slot has a snack)
    // ==================================================================

    private static void testBuyCheapestFull() {
        section("Part (b1): buyCheapestFull");
        runFullGridTests(true);
        endSection();
    }

    /** Scenarios where every slot is filled. Used for both b1 and b2. */
    private static void runFullGridTests(boolean full) {
        String name = full ? "buyCheapestFull()" : "buyCheapest()";

        test("1 x 1 machine", () ->
                buyOnce(full, 0, 0, "only:1.00"));

        test("Single cheapest snack in the top-left corner", () ->
                buyOnce(full, 0, 0,
                        "a:0.50 b:1.00 c:1.00 d:1.00",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:1.00 j:1.00 k:1.00 l:1.00"));

        test("Single cheapest snack in the top-right corner", () ->
                buyOnce(full, 0, 3,
                        "a:1.00 b:1.00 c:1.00 d:0.50",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:1.00 j:1.00 k:1.00 l:1.00"));

        test("Single cheapest snack in the bottom-left corner", () ->
                buyOnce(full, 2, 0,
                        "a:1.00 b:1.00 c:1.00 d:1.00",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:0.50 j:1.00 k:1.00 l:1.00"));

        test("Single cheapest snack in the bottom-right corner", () ->
                buyOnce(full, 2, 3,
                        "a:1.00 b:1.00 c:1.00 d:1.00",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:1.00 j:1.00 k:1.00 l:0.50"));

        test("Single cheapest snack in the middle", () ->
                buyOnce(full, 1, 2,
                        "a:3.00 b:2.50 c:2.00 d:4.00",
                        "e:1.75 f:2.25 g:0.95 h:1.10",
                        "i:5.00 j:1.25 k:3.50 l:1.00"));

        test("Prices only one cent apart", () ->
                buyOnce(full, 0, 1,
                        "a:1.01 b:1.00",
                        "c:1.01 d:1.01"));

        test("Tie in the same column: bottom row wins", () ->
                buyOnce(full, 2, 3,
                        "a:1.00 b:1.00 c:1.00 d:0.50",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:1.00 j:1.00 k:1.00 l:0.50"));

        test("Tie in the same column, not the last column: bottom row wins", () ->
                buyOnce(full, 1, 1,
                        "a:1.00 b:0.50 c:1.00 d:1.00",
                        "e:1.00 f:0.50 g:1.00 h:1.00",
                        "i:1.00 j:2.00 k:1.00 l:1.00"));

        test("Tie between top-left and bottom-right: bottom-right wins", () ->
                buyOnce(full, 2, 3,
                        "a:0.50 b:1.00 c:1.00 d:1.00",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:1.00 j:1.00 k:1.00 l:0.50"));

        test("Tie between bottom-left and top-right: top-right wins (columns come first)", () ->
                buyOnce(full, 0, 3,
                        "a:1.00 b:1.00 c:1.00 d:0.50",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:0.50 j:1.00 k:1.00 l:1.00"));

        test("Three-way tie across two columns", () ->
                buyOnce(full, 1, 2,
                        "a:1.00 b:0.75 c:0.75 d:1.00",
                        "e:1.00 f:1.00 g:0.75 h:1.00",
                        "i:0.75 j:1.00 k:1.00 l:1.00"));

        test("Every snack the same price: bottom-right wins", () ->
                buyOnce(full, 2, 3,
                        "a:1.00 b:1.00 c:1.00 d:1.00",
                        "e:1.00 f:1.00 g:1.00 h:1.00",
                        "i:1.00 j:1.00 k:1.00 l:1.00"));

        test("One row, tie: rightmost wins", () ->
                buyOnce(full, 0, 3, "a:0.50 b:1.00 c:0.50 d:0.50 e:2.00"));

        test("One column, tie: bottom wins", () ->
                buyOnce(full, 3, 0, "a:0.50", "b:1.00", "c:0.50", "d:0.50", "e:2.00"));

        test("Tall, narrow machine (5 x 2)", () ->
                buyOnce(full, 3, 0,
                        "a:2.00 b:1.50",
                        "c:1.00 d:1.25",
                        "e:0.60 f:0.90",
                        "g:0.60 h:0.70",
                        "i:3.00 j:0.65"));

        test("Wide machine (2 x 6), cheapest snack is in an early column", () ->
                buyOnce(full, 1, 0,
                        "a:0.95 b:1.00 c:1.00 d:1.00 e:1.00 f:1.00",
                        "g:0.90 h:1.00 i:1.00 j:1.00 k:1.00 l:1.00"));

        test("Cheapest snack is the very last one checked, after a tie", () ->
                buyOnce(full, 0, 0,
                        "a:0.25 b:0.50",
                        "c:0.50 d:0.50"));

        test("Machine of all different prices (" + name + ")", () ->
                buyOnce(full, 2, 1,
                        "a:1.10 b:1.20 c:1.30",
                        "d:1.40 e:1.50 f:1.60",
                        "g:1.70 h:0.80 i:1.90"));
    }

    // ==================================================================
    // Part (b2): buyCheapest  (slots may be empty)
    // ==================================================================

    private static void testBuyCheapest() {
        section("Part (b2): buyCheapest");

        test("FRQ example: three calls in a row", () -> {
            VendingMachine m = machine(
                    "-            gum:0.75   chips:1.50     mints:0.75",
                    "candy:1.25   -          pretzels:1.00  -",
                    "cookies:2.00 mints:0.75 -              popcorn:1.75");
            Snack[][] expected = copy(start);
            int[][] order = {{0, 3}, {2, 1}, {0, 1}};
            for (int i = 0; i < order.length; i++) {
                String call = "buyCheapest() call " + (i + 1);
                int r = order[i][0];
                int c = order[i][1];
                checkSnack(call, start[r][c], m.buyCheapest());
                expected[r][c] = null;
                checkGrid(call, expected, m);
            }
        });

        test("Empty machine returns null and changes nothing", () -> {
            VendingMachine m = new VendingMachine(3, 4);
            start = new Snack[3][4];
            checkSnack("buyCheapest()", null, m.buyCheapest());
            checkGrid("buyCheapest()", start, m);
        });

        test("Empty 1 x 1 machine returns null", () -> {
            VendingMachine m = new VendingMachine(1, 1);
            start = new Snack[1][1];
            checkSnack("buyCheapest()", null, m.buyCheapest());
            checkGrid("buyCheapest()", start, m);
        });

        test("Top-left slot [0][0] is empty", () ->
                buyOnce(false, 1, 1,
                        "-      b:1.00 c:1.00",
                        "d:1.00 e:0.25 f:1.00"));

        test("Bottom-right slot is empty (first slot checked)", () ->
                buyOnce(false, 0, 2,
                        "a:1.00 b:1.00 c:0.50",
                        "d:1.00 e:1.00 -"));

        test("Last column is completely empty", () ->
                buyOnce(false, 1, 0,
                        "a:1.00 b:2.00 -",
                        "c:0.50 d:1.00 -",
                        "e:3.00 f:0.75 -"));

        test("Last row is completely empty", () ->
                buyOnce(false, 0, 1,
                        "a:1.00 b:0.10 c:1.00",
                        "-      -      -"));

        test("Only one snack in the machine (top-left)", () ->
                buyOnce(false, 0, 0,
                        "a:9.99 -  -",
                        "-      -  -",
                        "-      -  -"));

        test("Only one snack in the machine (middle)", () ->
                buyOnce(false, 1, 1,
                        "-  -      -",
                        "-  a:9.99 -",
                        "-  -      -"));

        test("Only one snack in the machine (bottom-left)", () ->
                buyOnce(false, 2, 0,
                        "-      -  -",
                        "-      -  -",
                        "a:9.99 -  -"));

        test("Tie in a column with an empty slot between them: bottom wins", () ->
                buyOnce(false, 3, 2,
                        "a:1.00 -      b:0.50",
                        "-      c:1.00 -",
                        "d:1.00 -      -",
                        "-      e:1.00 f:0.50"));

        test("Tie in different columns with empty slots: rightmost column wins", () ->
                buyOnce(false, 0, 2,
                        "-      -      g:0.40 -",
                        "a:0.40 -      -      -",
                        "-      b:2.00 -      -"));

        test("Buy everything from the FRQ machine, then get null", () -> {
            VendingMachine m = machine(
                    "-            gum:0.75   chips:1.50     mints:0.75",
                    "candy:1.25   -          pretzels:1.00  -",
                    "cookies:2.00 mints:0.75 -              popcorn:1.75");
            int[][] order = {{0, 3}, {2, 1}, {0, 1}, {1, 2}, {1, 0}, {0, 2}, {2, 3}, {2, 0}};
            Snack[][] expected = copy(start);
            for (int i = 0; i < order.length; i++) {
                String call = "buyCheapest() call " + (i + 1);
                int r = order[i][0];
                int c = order[i][1];
                checkSnack(call, start[r][c], m.buyCheapest());
                expected[r][c] = null;
                checkGrid(call, expected, m);
            }
            checkSnack("buyCheapest() call 9 (machine is empty)", null, m.buyCheapest());
            checkGrid("buyCheapest() call 9 (machine is empty)", expected, m);
        });

        test("restock then buyCheapest work together", () -> {
            VendingMachine m = new VendingMachine(2, 3);
            start = new Snack[2][3];
            Snack a = new Snack("a", 2.00);
            Snack b = new Snack("b", 1.00);
            Snack c = new Snack("c", 1.00);
            m.restock(0, a);
            m.restock(0, b);
            m.restock(1, c);
            // b is at [0][1], c is at [1][0]. Column 1 is checked before column 0.
            checkSnack("buyCheapest() after restocking a, b, c", b, m.buyCheapest());
            checkSnack("buyCheapest() again", c, m.buyCheapest());
            checkSnack("buyCheapest() again", a, m.buyCheapest());
            checkSnack("buyCheapest() on empty machine", null, m.buyCheapest());
        });

        System.out.println("\n--- Full machines (buyCheapest must handle these too) ---");
        runFullGridTests(false);

        System.out.println("\n--- Random machines: buy every snack, then null ---");
        Random rand = new Random(2026);
        for (int t = 1; t <= 25; t++) {
            int rows = 1 + rand.nextInt(5);
            int cols = 1 + rand.nextInt(6);
            Snack[][] g = new Snack[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (rand.nextInt(10) < 6) {
                        // few distinct prices, so there are lots of ties
                        double price = 0.25 * (1 + rand.nextInt(6));
                        g[r][c] = new Snack("s" + r + c, price);
                    }
                }
            }
            test("Random " + rows + " x " + cols + " machine #" + t, () -> buyAll(g));
        }

        endSection();
    }

    // ==================================================================
    // Test helpers
    // ==================================================================

    /** Calls the buy method once and checks the snack returned and the grid after. */
    private static void buyOnce(boolean full, int expRow, int expCol, String... rows) {
        VendingMachine m = machine(rows);
        String call = full ? "buyCheapestFull()" : "buyCheapest()";
        Snack got = full ? m.buyCheapestFull() : m.buyCheapest();
        checkSnack(call, start[expRow][expCol], got);
        Snack[][] expected = copy(start);
        expected[expRow][expCol] = null;
        checkGrid(call, expected, m);
    }

    /**
     * Buys every snack from a machine and checks each one. The expected order is
     * cheapest first; among equal prices, the order the FRQ's traversal finds them.
     */
    private static void buyAll(Snack[][] g) {
        start = g;
        VendingMachine m = new VendingMachine(copy(g));
        int rows = g.length;
        int cols = g[0].length;

        ArrayList<Snack> expectedOrder = new ArrayList<>();
        ArrayList<Integer> rank = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (g[r][c] != null) {
                    int myRank = (cols - 1 - c) * rows + (rows - 1 - r);
                    int i = 0;
                    while (i < expectedOrder.size()
                            && (expectedOrder.get(i).getPrice() < g[r][c].getPrice()
                                || (expectedOrder.get(i).getPrice() == g[r][c].getPrice()
                                    && rank.get(i) < myRank))) {
                        i++;
                    }
                    expectedOrder.add(i, g[r][c]);
                    rank.add(i, myRank);
                }
            }
        }

        Snack[][] expected = copy(g);
        for (int i = 0; i < expectedOrder.size(); i++) {
            String call = "buyCheapest() call " + (i + 1);
            Snack want = expectedOrder.get(i);
            checkSnack(call, want, m.buyCheapest());
            removeFrom(expected, want);
            checkGrid(call, expected, m);
        }
        String last = "buyCheapest() call " + (expectedOrder.size() + 1) + " (machine is empty)";
        checkSnack(last, null, m.buyCheapest());
    }

    private static void checkValue(String call, boolean expected, boolean received) {
        if (expected != received) {
            throw new AssertionError(call + " expected " + expected + " received " + received);
        }
    }

    private static void checkSnack(String call, Snack expected, Snack received) {
        if (expected != received) {
            throw new AssertionError(call + " expected " + describe(expected)
                    + " received " + describe(received));
        }
    }

    /** Every slot must hold exactly the expected Snack object (or null). */
    private static void checkGrid(String call, Snack[][] expected, VendingMachine m) {
        String problems = "";
        for (int r = 0; r < expected.length; r++) {
            for (int c = 0; c < expected[r].length; c++) {
                Snack got = m.getSnack(r, c);
                if (got != expected[r][c]) {
                    problems += "\n      slot [" + r + "][" + c + "] expected "
                            + describe(expected[r][c]) + " received " + describe(got);
                }
            }
        }
        if (!problems.isEmpty()) {
            throw new AssertionError("after " + call + ", the machine is wrong:" + problems
                    + "\n      machine now:\n" + indent(m.toString()));
        }
    }

    private static void test(String description, Runnable check) {
        try {
            check.run();
            passed++;
            sectionPassed++;
            System.out.println("PASS: " + description);
        } catch (AssertionError e) {
            failed++;
            sectionFailed++;
            System.out.println("FAIL: " + description + "\n    " + e.getMessage());
        } catch (RuntimeException e) {
            failed++;
            sectionFailed++;
            StackTraceElement where = findStudentLine(e);
            System.out.println("FAIL: " + description + "\n    threw " + e
                    + (where == null ? "" : " at " + where));
        }
    }

    private static StackTraceElement findStudentLine(Throwable e) {
        for (StackTraceElement el : e.getStackTrace()) {
            if (el.getClassName().equals("VendingMachine")) {
                return el;
            }
        }
        return null;
    }

    private static String sectionName;

    private static void section(String name) {
        sectionName = name;
        sectionPassed = 0;
        sectionFailed = 0;
        System.out.println("\n=== " + name + " ===");
    }

    private static void endSection() {
        summary.add(String.format("%-28s %3d / %3d passed", sectionName,
                sectionPassed, sectionPassed + sectionFailed));
    }

    // ==================================================================
    // Grid building helpers
    // ==================================================================

    /** Builds a machine from rows like "chips:1.50 - gum:0.75". Sets start. */
    private static VendingMachine machine(String... rows) {
        Snack[][] g = new Snack[rows.length][];
        for (int r = 0; r < rows.length; r++) {
            String[] cells = rows[r].trim().split("\\s+");
            g[r] = new Snack[cells.length];
            for (int c = 0; c < cells.length; c++) {
                if (!cells[c].equals("-")) {
                    String[] parts = cells[c].split(":");
                    g[r][c] = new Snack(parts[0], Double.parseDouble(parts[1]));
                }
            }
        }
        start = g;
        return new VendingMachine(copy(g));
    }

    private static Snack[][] copy(Snack[][] g) {
        Snack[][] out = new Snack[g.length][];
        for (int r = 0; r < g.length; r++) {
            out[r] = g[r].clone();
        }
        return out;
    }

    private static void removeFrom(Snack[][] g, Snack s) {
        for (int r = 0; r < g.length; r++) {
            for (int c = 0; c < g[r].length; c++) {
                if (g[r][c] == s) {
                    g[r][c] = null;
                }
            }
        }
    }

    /** "mints $0.75 [0][3]" using the slot the snack started in, or "null" / "--". */
    private static String describe(Snack s) {
        if (s == null) {
            return "null";
        }
        if (start != null) {
            for (int r = 0; r < start.length; r++) {
                for (int c = 0; c < start[r].length; c++) {
                    if (start[r][c] == s) {
                        return s + " [" + r + "][" + c + "]";
                    }
                }
            }
        }
        return s.toString();
    }

    private static String indent(String text) {
        return "      " + text.replaceAll("\\s+$", "").replace("\n", "\n      ");
    }
}
