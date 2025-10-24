public class MergeSort {
    /**
     * Recursive mergeSort method Divides the array in half until base case (size 1
     * or 0) Then merges the sorted halves back together
     */
    public static int[] mergeSort(int[] arr) {
        // implement recursive mergesort here
        return arr;
    }

    /**
     * Merge method Combines two sorted arrays into one sorted array
     */
    public static int[] merge(int[] left, int[] right) {
        // Create result array to hold merged elements
        int[] result = new int[left.length + right.length];

        // merge two arrays into 'result' and return

        return result;
    }

    /**
     * Helper method to check if an array is sorted
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to print an array
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test the merge method directly with edge cases
        System.out.println("=== TESTING MERGE METHOD ===\n");

        // Test 1: Two normal sorted arrays
        int[] left1 = { 1, 3, 5, 7 };
        int[] right1 = { 2, 4, 6, 8 };
        System.out.println("Test 1 - Two normal sorted arrays:");
        System.out.print("Left: ");
        printArray(left1);
        System.out.print("Right: ");
        printArray(right1);
        int[] merged1 = merge(left1, right1);
        System.out.print("Merged: ");
        printArray(merged1);
        System.out.println("Is sorted? " + isSorted(merged1));
        System.out.println();

        // Test 2: Left array is empty
        int[] left2 = {};
        int[] right2 = { 1, 2, 3 };
        System.out.println("Test 2 - Left array is empty:");
        System.out.print("Left: ");
        printArray(left2);
        System.out.print("Right: ");
        printArray(right2);
        int[] merged2 = merge(left2, right2);
        System.out.print("Merged: ");
        printArray(merged2);
        System.out.println("Is sorted? " + isSorted(merged2));
        System.out.println();

        // Test 3: Right array is empty
        int[] left3 = { 5, 10, 15 };
        int[] right3 = {};
        System.out.println("Test 3 - Right array is empty:");
        System.out.print("Left: ");
        printArray(left3);
        System.out.print("Right: ");
        printArray(right3);
        int[] merged3 = merge(left3, right3);
        System.out.print("Merged: ");
        printArray(merged3);
        System.out.println("Is sorted? " + isSorted(merged3));
        System.out.println();

        // Test 4: Both arrays are empty
        int[] left4 = {};
        int[] right4 = {};
        System.out.println("Test 4 - Both arrays are empty:");
        System.out.print("Left: ");
        printArray(left4);
        System.out.print("Right: ");
        printArray(right4);
        int[] merged4 = merge(left4, right4);
        System.out.print("Merged: ");
        printArray(merged4);
        System.out.println("Is sorted? " + isSorted(merged4));
        System.out.println();

        // Test 5: All elements in left are smaller than right
        int[] left5 = { 1, 2, 3 };
        int[] right5 = { 10, 20, 30 };
        System.out.println("Test 5 - All left elements < all right elements:");
        System.out.print("Left: ");
        printArray(left5);
        System.out.print("Right: ");
        printArray(right5);
        int[] merged5 = merge(left5, right5);
        System.out.print("Merged: ");
        printArray(merged5);
        System.out.println("Is sorted? " + isSorted(merged5));
        System.out.println();

        // Test 6: Arrays of different sizes
        int[] left6 = { 1, 5 };
        int[] right6 = { 2, 3, 4, 6, 7, 8 };
        System.out.println("Test 6 - Arrays of different sizes:");
        System.out.print("Left: ");
        printArray(left6);
        System.out.print("Right: ");
        printArray(right6);
        int[] merged6 = merge(left6, right6);
        System.out.print("Merged: ");
        printArray(merged6);
        System.out.println("Is sorted? " + isSorted(merged6));
        System.out.println();

        // Test 7: Single element arrays
        int[] left7 = { 5 };
        int[] right7 = { 3 };
        System.out.println("Test 7 - Single element arrays:");
        System.out.print("Left: ");
        printArray(left7);
        System.out.print("Right: ");
        printArray(right7);
        int[] merged7 = merge(left7, right7);
        System.out.print("Merged: ");
        printArray(merged7);
        System.out.println("Is sorted? " + isSorted(merged7));
        System.out.println();

        // Now test the full mergeSort algorithm
        System.out.println("=== TESTING MERGESORT METHOD ===\n");

        // Create two random arrays to test
        int[] array1 = { 38, 27, 43, 3, 9, 82, 10 };
        int[] array2 = { 5, 2, 8, 1, 9, 3, 7, 6, 4 };

        System.out.println("Array 1 before sorting:");
        printArray(array1);

        int[] sorted1 = mergeSort(array1);

        System.out.println("Array 1 after sorting:");
        printArray(sorted1);
        System.out.println("Is sorted? " + isSorted(sorted1));
        System.out.println();

        System.out.println("Array 2 before sorting:");
        printArray(array2);

        int[] sorted2 = mergeSort(array2);

        System.out.println("Array 2 after sorting:");
        printArray(sorted2);
        System.out.println("Is sorted? " + isSorted(sorted2));
    }
}
