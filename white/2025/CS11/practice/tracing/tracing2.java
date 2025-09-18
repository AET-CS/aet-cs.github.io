/**
 * AP Computer Science - Easy Starter Questions (Version 2)
 * Code Tracing Practice Problems
 *
 * This file contains all the trace methods from the worksheet.
 * Run the main method to see the output of each problem.
 */
public class tracing2 {

    // Question 1: Simple Variable Trace
    public static void trace1() {
        int a = 8;
        int b = 2;
        a = a - b;
        b = a + b;
        System.out.println(a + " " + b);
    }

    // Question 2: Basic Loop
    public static void trace2() {
        int sum = 0;
        for (int i = 2; i <= 8; i = i + 2) {
            sum = sum + i;
        }
        System.out.println(sum);
    }

    // Question 3: Simple Conditional
    public static void trace3(int x) {
        if (x < 5) {
            x = x * 3;
        } else {
            x = x - 2;
        }
        System.out.println(x);
    }

    // Question 4: Loop with Counter
    public static void trace4() {
        int n = 15;
        int count = 0;
        while (n > 1) {
            n = n / 2;
            count++;
        }
        System.out.println(count + " " + n);
    }

    // Question 5: Loop with Conditional
    public static void trace5(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                result = result + i;
            }
        }
        System.out.println(result);
    }

    // Question 6: While Loop with Update
    public static void trace6(int n) {
        int x = 1;
        int count = 0;
        while (x * 3 <= n) {
            x = x * 3;
            count++;
        }
        System.out.println(x + " " + count);
    }

    // Question 7: Nested Conditionals
    public static void trace7(int a, int b) {
        int result = 0;
        if (a > 10) {
            if (b > 10) {
                result = a + b;
            } else {
                result = a * 2;
            }
        } else {
            result = b - a;
        }
        System.out.println(result);
    }

    // Question 8: Loop with Multiple Updates
    public static void trace8() {
        int x = 2;
        int y = 20;
        while (x < y) {
            x = x * 2;
            y = y - 3;
        }
        System.out.println(x + " " + y);
    }

    // Question 9: Accumulator Pattern
    public static void trace9(int n) {
        int product = 1;
        int count = 0;
        while (product < n) {
            product = product * 2;
            count++;
        }
        System.out.println(count + " " + product);
    }

    // Question 10: Complex Loop Logic
    public static void trace10(int n) {
        int x = 1;
        int y = 1;
        for (int i = 0; i < n; i++) {
            int next = x + 2 * y;
            x = y;
            y = next;
        }
        System.out.println(x);
    }

    /**
     * Main method - calls each trace method with the specified test values
     */
    public static void main(String[] args) {
        System.out.println("AP CS Easy Starter Questions - Version 2");
        System.out.println("=========================================\n");

        System.out.println("Question 1 - Simple Variable Trace:");
        System.out.print("Output: ");
        trace1();

        System.out.println("Question 2 - Basic Loop:");
        System.out.print("Output: ");
        trace2();

        System.out.println("Question 3 - Simple Conditional (with x=4):");
        System.out.print("Output: ");
        trace3(4);

        System.out.println("Question 4 - Loop with Counter:");
        System.out.print("Output: ");
        trace4();

        System.out.println("Question 5 - Loop with Conditional (with n=10):");
        System.out.print("Output: ");
        trace5(10);

        System.out.println("Question 6 - While Loop with Update (with n=30):");
        System.out.print("Output: ");
        trace6(30);

        System.out.println("Question 7 - Nested Conditionals (with a=12, b=7):");
        System.out.print("Output: ");
        trace7(12, 7);

        System.out.println("Question 8 - Loop with Multiple Updates:");
        System.out.print("Output: ");
        trace8();

        System.out.println("Question 9 - Accumulator Pattern (with n=100):");
        System.out.print("Output: ");
        trace9(100);

        System.out.println("Question 10 - Complex Loop Logic (with n=4):");
        System.out.print("Output: ");
        trace10(4);

        System.out.println("=========================================");
        System.out.println("All tests complete!");
    }
}