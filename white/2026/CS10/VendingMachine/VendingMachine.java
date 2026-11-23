/**
 * A vending machine whose slots are arranged in a rectangular grid.
 * An empty slot is null.
 *
 * The constructors, getters, and toString are done for you.
 * YOUR JOB: complete restock, buyCheapestFull, and buyCheapest.
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
    // Part (a)
    // ------------------------------------------------------------------

    /**
     * Places s in the leftmost empty slot of the given row, if possible.
     * Returns true if s was placed and false otherwise.
     * Precondition: row is a valid row index in slots; s is not null.
     */
    public boolean restock(int row, Snack s) {
        // TODO: Part (a)
        return false;
    }

    // ------------------------------------------------------------------
    // Part (b1)  -- warm-up: the machine is completely full
    // ------------------------------------------------------------------

    /**
     * Removes from slots and returns the lowest-priced snack.
     * Ties go to the first snack found when traversing slots starting
     * with the last column, bottom row to top row, then the next-to-last
     * column bottom to top, and so on, ending with the first column.
     *
     * Precondition: EVERY element of slots contains a snack (no nulls).
     */
    public Snack buyCheapestFull() {
        // TODO: Part (b1)
        return null;
    }

    // ------------------------------------------------------------------
    // Part (b2)  -- the real FRQ: slots may be empty
    // ------------------------------------------------------------------

    /**
     * Removes from slots and returns the lowest-priced snack, or returns null
     * if slots contains no snacks. Same traversal order and tie rule as
     * buyCheapestFull, but any element of slots may be null.
     */
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
