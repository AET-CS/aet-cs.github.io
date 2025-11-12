public class AnswerKey {

    public static int findMax(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }

    public static int sumArray(int[] a) {
        int sum = 0;
        for (int num : a) {
            sum += num;
        }
        return sum;
    }

    public static double average(int[] arr) {
        return (double) sumArray(arr) / arr.length;
    }

    public static boolean isAllPositive(int[] a) {
        for (int num : a) {
            if (num <= 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] makeCopy(int[] original) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }
        return copy;
    }

    public static String repeat(String s, int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += s;
        }
        return result;
    }

    public static boolean hasValue(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static void fillArray(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] findMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int num : arr) {
            if (num < min)
                min = num;
            if (num > max)
                max = num;
        }
        return new int[] { min, max };
    }

    public static boolean arraysEqual(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] removeNegatives(int[] arr) {
        // Count non-negative values
        int count = 0;
        for (int num : arr) {
            if (num >= 0)
                count++;
        }
        // Create new array and fill it
        int[] result = new int[count];
        int index = 0;
        for (int num : arr) {
            if (num >= 0) {
                result[index++] = num;
            }
        }
        return result;
    }

    public static void printBackwards(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
    }

    public static int[] combineArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        return result;
    }

    public static void reverseInPlace(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            // shortcut calls swap from before!
            swap(arr, i, arr.length - 1 - i);
        }
    }

    public static void rotateLeft(int[] arr, int n) {
        n = n % arr.length; // Handle n > array length
        for (int i = 0; i < n; i++) {
            int first = arr[0];
            for (int j = 0; j < arr.length - 1; j++) {
                arr[j] = arr[j + 1];
            }
            arr[arr.length - 1] = first;
        }
    }
}