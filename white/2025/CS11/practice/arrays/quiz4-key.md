---
title: Quiz 4 Key
---

```java
public class APCSProblems {

    /**
     * Problem 1: Check if array is sorted in increasing order
     * Returns true ONLY IF the array is sorted in increasing order.
     * Example: isSorted([1,3,5,7,9]) returns true
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Problem 2: Sum array excluding numbers >= 20
     * Returns the sum of all numbers in the array, but does NOT count
     * numbers greater than or equal to 20.
     * Example: sum([1,4,20,40,5]) returns 10 (1+4+5)
     */
    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            if (num < 20) {
                total += num;
            }
        }
        return total;
    }


    /**
     * Problem 3: Remove all 'a' and 'A' from string
     * Returns a new String with all occurrences of 'a' or 'A' removed.
     * Example: removeA("ababABB") returns "bbBB"
     */
    public static String removeA(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != 'a' && c != 'A') {
                result += c;
            }
        }
        return result;
    }

}
```