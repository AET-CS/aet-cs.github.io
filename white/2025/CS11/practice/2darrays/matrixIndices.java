public class matrixIndices {

    public static void f1() {
        int[][] m = new int[4][4];

        int i = 1;
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[r].length; c++) {
                m[r][c] = i;
                i++;
            }
        }
        print_matrix(m);
    }

    public static void print_matrix(int[][] m) {
        if (m == null) {
            System.out.println("null");
            return;
        }

        int maxWidth = 1;
        for (int r = 0; r < m.length; r++) {
            if (m[r] == null) {
                maxWidth = Math.max(maxWidth, 4);
                continue;
            }
            for (int c = 0; c < m[r].length; c++) {
                int width = String.valueOf(m[r][c]).length();
                if (width > maxWidth) {
                    maxWidth = width;
                }
            }
        }

        String format = "%" + maxWidth + "d ";
        for (int r = 0; r < m.length; r++) {
            if (m[r] == null) {
                System.out.println("null");
                continue;
            }
            for (int c = 0; c < m[r].length; c++) {
                System.out.printf(format, m[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        f1();
    }
}
