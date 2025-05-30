import java.lang.*;

public class ArrayPractice {
    /**
     * Returns a new array containing only the elements at even indices from the input array.
     *
     * @param arr the input array
     * @return array containing elements at indices 0, 2, 4, etc. from input
     */
    public static int[] getEvenIndexElements(int[] arr) {
        int new_length = (arr.length + 1) / 2;
        int[] result = new int[new_length];

        for (int i = 0; i < arr.length; i += 2) {
            result[i / 2] = arr[i];
        }
        return result;
    }

    /**
     * Finds the first pair of adjacent elements that sum to the target value.
     *
     * @param arr    the input array
     * @param target the target sum to find
     * @return array containing indices of the pair, or [-1,-1] if no such pair exists
     */
    public static int[] findAdjacentSum(int[] arr, int target) {
        int[] result = {-1, -1};
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] + arr[i - 1] == target) {
                result[0] = i - 1;
                result[1] = i;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the length of the longest streak of consecutive equal values.
     *
     * @param arr the input array
     * @return length of longest streak of equal values, including ties
     */
    public static int longestStreak(int[] arr) {
        int max_streak = 1;
        int current_streak = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                current_streak++;
                max_streak = Math.max(max_streak, current_streak);
            } else {
                current_streak = 1;
            }
        }
        return max_streak;
    }

    /**
     * Finds index where sum of numbers to left ( including index)
     * equals sum of numbers to right of the index
     *
     * @param arr the input array
     * @return index of balance point, or -1 if none exists
     */
    public static int findBalancePoint(int[] arr) {
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftSum += arr[i];
            if (leftSum == sum / 2) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if array alternates between positive and negative numbers, starting with positive
     *
     * @param arr the input array
     * @return true if signs alternate and no zeros present, false otherwise
     */
    public static boolean hasAlternatingSigns(int[] arr) {
        if (arr.length==0 || arr[0] <= 0) {
            return false;
        }
        int i = 1;
        while(i<arr.length && arr[i]*arr[i-1] < 0) {
            i++;
        }
        return (i == arr.length);
    }

    /**
     * Finds largest sum of k consecutive elements in the array.
     *
     * @param arr the input array of non-negative integers
     * @param k   the window size
     * @return largest sum of k consecutive elements, or -1 if k > array length
     */
    public static int maxSlidingWindow(int[] arr, int k) {
        int maxSum = -1;

        for (int i = 0; i <= arr.length - k; i++) {
            int currSum = 0;
            for (int j = 0; j <= k - 1; j++) {
                currSum += arr[i+j];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
    /**
     * Determines if array contains a mountain sequence ( increases then decreases).
     * Sequence must be at least 3 numbers long.
     * (This problem is very ambigous. Solution below will test if the whole sequence is
     * one mountain (not necessarily symmetric)
     * @param arr the input array
     * @return true if array contains a mountain sequence, false otherwise
     */
    public static boolean isMountain(int[] arr) {
        if (arr.length < 3) return false;

        // find the end of the "up" slope
        int i = 0;
        while (i + 1 < arr.length && arr[i+1] >= arr[i] ) {
            i++;
        }
        // i is the index of the last integer in the "up" slope
        // find the "down" slope
        int j = i;
        while (j + 1 < arr.length && arr[j+1] < arr[j]) {
            j++;
        }
        // j is the index of the last integer in the "down" slope
        System.out.println(i+","+j);
        return (i>0 && j>i && j + 1 == arr.length);
    }

    /**
     * Returns most frequently occurring element. If tie, returns first occurrence.
     *
     * @param arr the input array
     * @return most frequent element in the array
     */

    // This inefficient O(n^2) solution is the best we can do without HashMaps
    // or other data types to help keep count

    public static int findMode(int[] arr) {
        int current = arr[0];
        int maxCount = 1;
        int mode = arr[0];
        for (int j = 0; j < arr.length; j++) {
            current = arr[j];
            int currentCount = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == current) currentCount++;
            }
            if (currentCount > maxCount) {
                mode = current;
                maxCount = currentCount;
            }
        }
        return mode;
    }

    public static String printArray(int[] arr) {
        String result = "";
        for (int e: arr) {
            result += " " + e;
        }
        return result;
    }

    public static void main(String[] args) {
        // Test getEvenIndexElements
        assert java.util.Arrays.equals(
                getEvenIndexElements(new int[]{1,2,3,4,5}),
                new int[]{1,3,5}
        ) : "getEvenIndexElements failed test 1: " + printArray(getEvenIndexElements(new int[]{1,2,3,4,5}));
        assert java.util.Arrays.equals(
                getEvenIndexElements(new int[]{1}),
                new int[]{1}
        ) : "getEvenIndexElements failed test 2";

        // Test findAdjacentSum
        assert java.util.Arrays.equals(
                findAdjacentSum(new int[]{1,2,3,4}, 5),
                new int[]{1,2}
        ) : "findAdjacentSum failed test 1";
        assert java.util.Arrays.equals(
                findAdjacentSum(new int[]{1,2,3,4}, 10),
                new int[]{-1,-1}
        ) : "findAdjacentSum failed test 2";

        // Test longestStreak
        assert longestStreak(new int[]{1,1,1,2,2,3,3,3,3}) == 4 : "longestStreak failed test 1";
        assert longestStreak(new int[]{1,2,3,4}) == 1 : "longestStreak failed test 2";
        assert longestStreak(new int[]{1}) == 1 : "longestStreak failed test 3";

        // Test findBalancePoint
        assert findBalancePoint(new int[]{1,2,3,3}) == -1 : "findBalancePoint failed test 1";
        assert findBalancePoint(new int[]{4,5,2,3,9,12,7,4}) == 4 : "findBalancePoint failed test 2";
        assert findBalancePoint(new int[]{1,4,0,5,10}) == 3 : "findBalancePoint failed test 3";

        // Test hasAlternatingSigns
        assert hasAlternatingSigns(new int[]{1,-2,3,-4}) : "hasAlternatingSigns failed test 1";
        assert !hasAlternatingSigns(new int[]{1,-2,3,4}) : "hasAlternatingSigns failed test 2";
        assert !hasAlternatingSigns(new int[]{}) : "hasAlternatingSigns failed test 3";
        assert !hasAlternatingSigns(new int[]{-1,2,-3}) : "hasAlternatingSigns failed test 4";

        // Test maxSlidingWindow
        assert maxSlidingWindow(new int[]{1,2,3,4,5}, 3) == 12 : "maxSlidingWindow failed test 1";
        assert maxSlidingWindow(new int[]{1,2}, 3) == -1 : "maxSlidingWindow failed test 2";
        assert maxSlidingWindow(new int[]{5,5,5,5}, 2) == 10 : "maxSlidingWindow failed test 3";

        // Test isMountain
        assert isMountain(new int[]{1,3,5,4,2}) : "isMountain failed test 1";
        assert !isMountain(new int[]{1,2,3}) : "isMountain failed test 2";
        assert !isMountain(new int[]{5,4,3,2,1}) : "isMountain failed test 3";
        assert !isMountain(new int[]{1,2}) : "isMountain failed test 4";

        // Test findMode
        assert findMode(new int[]{1,2,2,3}) == 2 : "findMode failed test 1";
        assert findMode(new int[]{1,1,2,2}) == 1 : "findMode failed test 2";
        assert findMode(new int[]{5}) == 5 : "findMode failed test 3";

        System.out.println("All tests passed!");
    }
}