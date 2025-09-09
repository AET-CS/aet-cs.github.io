public class PowerOfTwoFinder {

    // This shows the complete decimal-to-binary conversion
    public static void findAllPowersOf2(int n) {
        System.out.println("Powers of 2 that sum to " + n + ":");

        while (n > 0) {
            // Find the largest power of 2 less than or equal to n
            int largestPower = largestPowerOf2LessThanOrEqual(n);
            System.out.println(largestPower);

            // Subtract it from n
            n = n - largestPower;
        }
    }

    // Helper method for the complete conversion
    private static int largestPowerOf2LessThanOrEqual(int n) {
        int powerOf2 = 1;
        while (powerOf2 * 2 <= n) {
            powerOf2 = powerOf2 * 2;
        }
        return powerOf2;
    }

    // Test the methods
    public static void main(String[] args) {
        int number = 13;

        System.out.println();
        findAllPowersOf2(number);

        // Try with other numbers
        System.out.println("\n--- More examples ---");
        findAllPowersOf2(25);
        System.out.println();
        findAllPowersOf2(7);
    }
}