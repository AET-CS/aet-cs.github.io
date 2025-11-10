```java
public class ArrayTracing {

    public static int[] makeNumbers(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] makeEvens(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (i + 1) * 2;
        }
        return arr;
    }

    public static int[] addArrays(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    public static int countEvens(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void printEvens(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = makeNumbers(10);
        int[] evens = makeEvens(10);

        int count1 = countEvens(numbers);
        int count2 = countEvens(evens);

        System.out.println(count1);
        System.out.println(count2);

        int[] sum = addArrays(numbers, evens);

        int count3 = countEvens(sum);
        System.out.println(count3);

        printEvens(sum);
    }
}
```