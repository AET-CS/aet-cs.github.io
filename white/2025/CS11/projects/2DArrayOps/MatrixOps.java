/**
 * MatrixOps.java
 *
 * AP Computer Science A - 2D Array Assignment: Matrix Operations
 *
 * In this assignment you will implement several matrix operations as
 * static methods. There are no classes to write — just methods that
 * take 2D int arrays as parameters and return results.
 *
 * Matrices are represented as int[][] where:
 * - matrix.length gives the number of rows
 * - matrix[0].length gives the number of columns
 *
 * For each operation, you must check that the dimensions are valid
 * before performing the calculation. If dimensions are incompatible,
 * print an error message and return null (or -1 for int-returning methods).
 */
public class MatrixOps {

    // =========================================================
    // UTILITY METHODS
    // =========================================================

    /**
     * getRows - Returns the number of rows in the matrix.
     *
     * @param matrix a 2D int array
     * @return the number of rows, or -1 if matrix is null or empty
     */
    public static int getRows(int[][] matrix) {
        return 0;
    }

    /**
     * getCols - Returns the number of columns in the matrix.
     * Assumes a rectangular matrix (all rows have the same length).
     *
     * @param matrix a 2D int array
     * @return the number of columns, or -1 if matrix is null or empty
     */
    public static int getCols(int[][] matrix) {
        return 0;
    }

    /**
     * getSize - Returns a string describing the dimensions of the matrix
     * in the format "rows x cols". For example, a 2-by-3 matrix returns "2 x 3".
     *
     * @param matrix a 2D int array
     * @return a String like "rows x cols", or "invalid" if matrix is null/empty
     */
    public static String getSize(int[][] matrix) {
        return "0";
    }

    /**
     * printMatrix - Prints the matrix in a nicely formatted grid.
     * Each element is right-justified in a field of width 6.
     *
     * @param matrix a 2D int array
     */
    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("null matrix");
            return;
        }
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.printf("%6d", matrix[r][c]);
            }
            System.out.println();
        }
    }

    // =========================================================
    // ARITHMETIC METHODS
    // =========================================================

    /**
     * addMatrices - Returns a NEW matrix that is the element-wise sum of a and b.
     *
     * Addition requires both matrices to have the SAME dimensions.
     * Result[r][c] = a[r][c] + b[r][c]
     *
     * @param a first matrix
     * @param b second matrix
     * @return a new matrix representing a + b, or null if dimensions don't match
     */
    public static int[][] addMatrices(int[][] a, int[][] b) {
        return null;
    }

    /**
     * subtractMatrices - Returns a NEW matrix that is the element-wise difference a
     * - b.
     *
     * Subtraction requires both matrices to have the SAME dimensions.
     * Result[r][c] = a[r][c] - b[r][c]
     *
     * @param a first matrix
     * @param b second matrix
     * @return a new matrix representing a - b, or null if dimensions don't match
     */
    public static int[][] subtractMatrices(int[][] a, int[][] b) {
        return null;
    }

    /**
     * multiplyMatrices - Returns a NEW matrix that is the matrix product of a and
     * b.
     *
     * Multiplication requires that the number of COLUMNS in a equals
     * the number of ROWS in b.
     * - If a is (m x n) and b is (n x p), the result is (m x p).
     * - Each element Result[r][c] = sum of a[r][k] * b[k][c] for k = 0..n-1
     *
     * Example: if a is 2x3 and b is 3x2, result is 2x2.
     *
     * @param a first matrix (m x n)
     * @param b second matrix (n x p)
     * @return a new matrix representing a * b, or null if inner dimensions don't
     *         match
     */
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        return null;
    }

    /**
     * determinant - Returns the determinant of a SQUARE matrix.
     *
     * The matrix must be square (rows == cols).
     *
     * Uses cofactor expansion along the first row (recursive):
     * - Base case: 1x1 matrix → return the single element
     * - Base case: 2x2 matrix → ad - bc
     * - Recursive case: expand along row 0 using cofactors
     * det(A) = sum of a[0][c] * (-1)^c * det(minor(A, 0, c))
     *
     * The "minor" of A at (row, col) is the matrix formed by deleting
     * that row and column.
     *
     * NOTE:: Implement only for 2x2 or 3x3. Larger is a CHALLENGE!
     *
     * @param matrix a square 2D int array
     * @return the determinant, or 0 with an error message if not square
     */
    public static int determinant(int[][] matrix) {
        return 0;
    }

    /**
     * getMinor - Returns a NEW matrix with the specified row and column removed.
     *
     * If the input is n x n, the result is (n-1) x (n-1).
     * This is a helper method used by the determinant calculation.
     *
     * @param matrix  a square 2D int array
     * @param skipRow the row index to remove
     * @param skipCol the column index to remove
     * @return a new (n-1) x (n-1) matrix
     */
    public static int[][] getMinor(int[][] matrix, int skipRow, int skipCol) {
        int n = getRows(matrix);
        int[][] minor = new int[n - 1][n - 1];

        int mr = 0; // minor row index
        for (int r = 0; r < n; r++) {
            if (r == skipRow)
                continue;
            int mc = 0; // minor col index
            for (int c = 0; c < n; c++) {
                if (c == skipCol)
                    continue;
                minor[mr][mc] = matrix[r][c];
                mc++;
            }
            mr++;
        }
        return minor;
    }

    // =========================================================
    // TEST HARNESS
    // =========================================================

    public static void main(String[] args) {

        int passed = 0;
        int failed = 0;

        System.out.println("==============================================");
        System.out.println("       MATRIX OPERATIONS — TEST SUITE");
        System.out.println("==============================================\n");

        // ----- Define test matrices -----

        int[][] a = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        }; // 2 x 3

        int[][] b = {
                { 7, 8, 9 },
                { 10, 11, 12 }
        }; // 2 x 3

        int[][] c = {
                { 1, 2 },
                { 3, 4 },
                { 5, 6 }
        }; // 3 x 2

        int[][] sq2 = {
                { 3, 8 },
                { 4, 6 }
        }; // 2 x 2

        int[][] sq3 = {
                { 6, 1, 1 },
                { 4, -2, 5 },
                { 2, 8, 7 }
        }; // 3 x 3

        int[][] identity3 = {
                { 1, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 1 }
        }; // 3 x 3 identity

        // ============================
        // TEST getRows / getCols / getSize
        // ============================

        System.out.println("--- Test: getRows, getCols, getSize ---");

        if (getRows(a) == 2) {
            passed++;
            System.out.println("  PASS: getRows(a) == 2");
        } else {
            failed++;
            System.out.println("  FAIL: getRows(a) expected 2, got " + getRows(a));
        }

        if (getCols(a) == 3) {
            passed++;
            System.out.println("  PASS: getCols(a) == 3");
        } else {
            failed++;
            System.out.println("  FAIL: getCols(a) expected 3, got " + getCols(a));
        }

        if (getSize(a).equals("2 x 3")) {
            passed++;
            System.out.println("  PASS: getSize(a) == \"2 x 3\"");
        } else {
            failed++;
            System.out.println("  FAIL: getSize(a) expected \"2 x 3\", got \"" + getSize(a) + "\"");
        }

        if (getRows(c) == 3) {
            passed++;
            System.out.println("  PASS: getRows(c) == 3");
        } else {
            failed++;
            System.out.println("  FAIL: getRows(c) expected 3, got " + getRows(c));
        }

        if (getCols(c) == 2) {
            passed++;
            System.out.println("  PASS: getCols(c) == 2");
        } else {
            failed++;
            System.out.println("  FAIL: getCols(c) expected 2, got " + getCols(c));
        }

        // Edge case: null matrix
        if (getRows(null) == -1) {
            passed++;
            System.out.println("  PASS: getRows(null) == -1");
        } else {
            failed++;
            System.out.println("  FAIL: getRows(null) expected -1");
        }

        System.out.println();

        // ============================
        // TEST addMatrices
        // ============================

        System.out.println("--- Test: addMatrices ---");

        int[][] sum = addMatrices(a, b);
        // { 8, 10, 12 }
        // { 14, 16, 18 }

        if (sum != null && sum[0][0] == 8 && sum[0][1] == 10 && sum[0][2] == 12
                && sum[1][0] == 14 && sum[1][1] == 16 && sum[1][2] == 18) {
            passed++;
            System.out.println("  PASS: addMatrices(a, b) correct");
        } else {
            failed++;
            System.out.println("  FAIL: addMatrices(a, b) incorrect");
            if (sum != null) {
                printMatrix(sum);
            }
        }

        // Dimension mismatch: a is 2x3, c is 3x2
        System.out.print("  Expected error: ");
        int[][] badSum = addMatrices(a, c);
        if (badSum == null) {
            passed++;
            System.out.println("  PASS: addMatrices(a, c) returned null (dimension mismatch)");
        } else {
            failed++;
            System.out.println("  FAIL: addMatrices(a, c) should have returned null");
        }

        System.out.println();

        // ============================
        // TEST subtractMatrices
        // ============================

        System.out.println("--- Test: subtractMatrices ---");

        int[][] diff = subtractMatrices(b, a);
        // { 6, 6, 6 }
        // { 6, 6, 6 }

        if (diff != null && diff[0][0] == 6 && diff[0][1] == 6 && diff[0][2] == 6
                && diff[1][0] == 6 && diff[1][1] == 6 && diff[1][2] == 6) {
            passed++;
            System.out.println("  PASS: subtractMatrices(b, a) correct (all 6s)");
        } else {
            failed++;
            System.out.println("  FAIL: subtractMatrices(b, a) incorrect");
            if (diff != null) {
                printMatrix(diff);
            }
        }

        // Dimension mismatch
        System.out.print("  Expected error: ");
        int[][] badDiff = subtractMatrices(a, c);
        if (badDiff == null) {
            passed++;
            System.out.println("  PASS: subtractMatrices(a, c) returned null");
        } else {
            failed++;
            System.out.println("  FAIL: subtractMatrices(a, c) should have returned null");
        }

        System.out.println();

        // ============================
        // TEST multiplyMatrices
        // ============================

        System.out.println("--- Test: multiplyMatrices ---");

        // a (2x3) * c (3x2) = result (2x2)
        // a = {{1,2,3},{4,5,6}} c = {{1,2},{3,4},{5,6}}
        // result[0][0] = 1*1 + 2*3 + 3*5 = 22
        // result[0][1] = 1*2 + 2*4 + 3*6 = 28
        // result[1][0] = 4*1 + 5*3 + 6*5 = 49
        // result[1][1] = 4*2 + 5*4 + 6*6 = 64

        int[][] product = multiplyMatrices(a, c);
        if (product != null && getRows(product) == 2 && getCols(product) == 2
                && product[0][0] == 22 && product[0][1] == 28
                && product[1][0] == 49 && product[1][1] == 64) {
            passed++;
            System.out.println("  PASS: multiplyMatrices(a, c) = {{22,28},{49,64}}");
        } else {
            failed++;
            System.out.println("  FAIL: multiplyMatrices(a, c) incorrect");
            if (product != null) {
                printMatrix(product);
            }
        }

        // Identity multiplication: sq3 * identity3 should equal sq3
        int[][] identityTest = multiplyMatrices(sq3, identity3);
        boolean identityPass = true;
        if (identityTest != null) {
            for (int r = 0; r < 3 && identityPass; r++) {
                for (int col = 0; col < 3 && identityPass; col++) {
                    if (identityTest[r][col] != sq3[r][col]) {
                        identityPass = false;
                    }
                }
            }
        } else {
            identityPass = false;
        }
        if (identityPass) {
            passed++;
            System.out.println("  PASS: sq3 * identity3 == sq3");
        } else {
            failed++;
            System.out.println("  FAIL: sq3 * identity3 should equal sq3");
        }

        // Dimension mismatch: a (2x3) * b (2x3) should fail
        System.out.print("  Expected error: ");
        int[][] badProd = multiplyMatrices(a, b);
        if (badProd == null) {
            passed++;
            System.out.println("  PASS: multiplyMatrices(a, b) returned null (inner dims don't match)");
        } else {
            failed++;
            System.out.println("  FAIL: multiplyMatrices(a, b) should have returned null");
        }

        System.out.println();

        // ============================
        // TEST determinant
        // ============================

        System.out.println("--- Test: determinant ---");

        // 1x1
        int[][] oneByOne = { { 7 } };
        int det1 = determinant(oneByOne);
        if (det1 == 7) {
            passed++;
            System.out.println("  PASS: det({{7}}) == 7");
        } else {
            failed++;
            System.out.println("  FAIL: det({{7}}) expected 7, got " + det1);
        }

        // 2x2: det(sq2) = 3*6 - 8*4 = 18 - 32 = -14
        int det2 = determinant(sq2);
        if (det2 == -14) {
            passed++;
            System.out.println("  PASS: det(sq2) == -14");
        } else {
            failed++;
            System.out.println("  FAIL: det(sq2) expected -14, got " + det2);
        }

        // 3x3: det(sq3) = 6(-2*7 - 5*8) - 1(4*7 - 5*2) + 1(4*8 - (-2)*2)
        // = 6(-14 - 40) - 1(28 - 10) + 1(32 + 4)
        // = 6(-54) - 18 + 36 = -324 - 18 + 36 = -306
        int det3 = determinant(sq3);
        if (det3 == -306) {
            passed++;
            System.out.println("  PASS: det(sq3) == -306");
        } else {
            failed++;
            System.out.println("  FAIL: det(sq3) expected -306, got " + det3);
        }

        // Identity matrix: det should be 1
        int detI = determinant(identity3);
        if (detI == 1) {
            passed++;
            System.out.println("  PASS: det(identity3) == 1");
        } else {
            failed++;
            System.out.println("  FAIL: det(identity3) expected 1, got " + detI);
        }

        // Non-square: should print error and return 0
        System.out.print("  Expected error: ");
        int detBad = determinant(a);
        if (detBad == 0) {
            passed++;
            System.out.println("  PASS: det(non-square) returned 0");
        } else {
            failed++;
            System.out.println("  FAIL: det(non-square) expected 0, got " + detBad);
        }

        System.out.println();

        // ============================
        // SUMMARY
        // ============================

        System.out.println("==============================================");
        System.out.println("  RESULTS: " + passed + " passed, " + failed + " failed "
                + "out of " + (passed + failed) + " tests.");
        System.out.println("==============================================");

        // ============================
        // BONUS: Pretty-print demo
        // ============================

        System.out.println("\n--- Demo: Pretty Print ---\n");

        System.out.println("Matrix a (2x3):");
        printMatrix(a);

        System.out.println("\nMatrix c (3x2):");
        printMatrix(c);

        System.out.println("\na * c (2x2):");
        printMatrix(multiplyMatrices(a, c));

        System.out.println("\nMatrix sq3 (3x3):");
        printMatrix(sq3);
        System.out.println("Determinant = " + determinant(sq3));
    }
}