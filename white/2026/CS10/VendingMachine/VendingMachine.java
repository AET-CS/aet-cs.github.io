/**
 * A vending machine whose slots are arranged in a rectangular grid. An empty
 * slot is null.
 *
 * The constructors, getters, and toString are done for you. YOUR JOB: complete
 * restock, buyCheapestFull, and buyCheapest.
 */
public class VendingMachine {
    /** slots contains at least one row and is initialized in the constructor. */
    private Snack[][] slots;

    /** Creates an empty machine with the given number of rows and columns. */
    public VendingMachine(int rows, int cols) {
        slots = new Snack[rows][cols];
    }

    /** Creates a machine that uses the given grid of snacks (null = empty slot). */
    public VendingMachine(Snack[][] slots) {
        this.slots = slots;
    }

    public int getNumRows() {
        return slots.length;
    }

    public int getNumCols() {
        return slots[0].length;
    }

    /** Returns the snack at (row, col), or null if that slot is empty. */
    public Snack getSnack(int row, int col) {
        return slots[row][col];
    }

    // ------------------------------------------------------------------
    // Part (a) See assignment
    // ------------------------------------------------------------------

    public boolean restock(int row, Snack s) {
        // TODO: Part (a)
        return false;
    }

    // ------------------------------------------------------------------
    // Part (b1) -- See assignment
    // ------------------------------------------------------------------

    public Snack buyCheapestFull() {
        // TODO: Part (b1)
        return null;
    }

    // ------------------------------------------------------------------
    // Part (b2) -- See assignment
    // ------------------------------------------------------------------

    public Snack buyCheapest() {
        // TODO: Part (b2)
        return null;
    }

    // ------------------------------------------------------------------

    /** Draws the machine as a text grid. Empty slots show as "--". */
    public String toString() {
        String out = "        ";
        for (int c = 0; c < slots[0].length; c++) {
            out += String.format("%-16s", "col " + c);
        }
        out += "\n";
        for (int r = 0; r < slots.length; r++) {
            out += String.format("%-8s", "row " + r);
            for (int c = 0; c < slots[r].length; c++) {
                String cell = slots[r][c] == null ? "--" : slots[r][c].toString();
                out += String.format("%-16s", cell);
            }
            out += "\n";
        }
        return out;
    }
}
