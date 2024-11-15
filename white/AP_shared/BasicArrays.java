public class BasicArrays {

    // Returns the maximum value in the given array
    public static double findMax(double[] a) {
    }

    // Returns the minimum value in the given array
    public static double findMin(double[] a) {
    }

    // Returns the index of the maximum value in the given array
    public static int argMax(double[] a) {
    }

    // Returns the index of the minimum value in the given array
    public static int argMin(double[] a) {
    }

    // Counts the number of occurrences of the given target value in the array
    public static int count(double[] a, double target) {
    }

    // Returns the index of the first occurrence of the given target value in the array, or -1 if not found
    public static int findFirst(double[] a, double target) {
    }

    // Returns the second largest value in the given array
    public static double findSecondLargest(double[] a) {
    }

    // Returns the element in the array that is closest to the target value
    public static double findClosest(double[] a, double target) {
    }

    // Returns the values of the two elements in the array that are closest in value
    public static double[] findClosestPair(double[] a) {
    }

    // Swaps the elements at the given indices in the array
    public static void swap(double[] a, int i, int j) {
    }

    // returns a string contatining each element in the array
    public static String arrayToString(double[] a) {
    }

    public static void main(String[] args) {
        double[] nums = {5.2, -3.1, 8.7, -1.9, 6.4, 4.3, 5.2, -2.0};

        System.out.println("findMax: " + findMax(nums)); // Output: 8.7
        System.out.println("findMin: " + findMin(nums)); // Output: -3.1
        System.out.println("argMax: " + argMax(nums)); // Output: 2
        System.out.println("argMin: " + argMin(nums)); // Output: 1
        System.out.println("count(5.2): " + count(nums, 5.2)); // Output: 2
        System.out.println("count(-2.0): " + count(nums, -2.0)); // Output: 1
        System.out.println("findFirst(6.4): " + findFirst(nums, 6.4)); // Output: 4
        System.out.println("findFirst(-2.0): " + findFirst(nums, -2.0)); // Output: 7
        System.out.println("findSecondLargest: " + findSecondLargest(nums)); // Output: 6.4
        System.out.println("findClosest(3.0): " + findClosest(nums, 3.0)); // Output: 4.3
        System.out.println("findClosestPair: " + arrayToString(findClosestPair(nums))); // Output:
        swap(nums, 0, 6);
        System.out.println("After swap: " + arrayToString(nums));
    }
}
