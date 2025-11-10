# Pascal Helper

You can use this code as a template to organize your Pascal Triangle program.

```java
import java.util.Scanner;

public class PascalTriangle {

    // Returns an array of size n
    public static int[] createArrayOfSize(int n) {
        // YOUR CODE HERE
    }

    // Prints array on one line with spaces
    public static void printArray(int[] s) {
        // use a for loop to print each integer in s on
        // one line
        // YOUR CODE HERE
    }

    // Given a row, create and return the next row
    public static int[] createNextRow(int[] r) {
        // make a new row by calling createArrayOfSize
        // fill it with all ones (for version 1)
        // or the correct pascal values for the final version
        // then return the new row
        // YOUR CODE HERE

    }

    // Create and print Pascal's triangle with numRows rows
    public static void createPascalTriangle(int numRows) {
        // create a row called currentRow that
        // is the first row of pascal's triangle
        // YOUR CODE HERE

        // this loop prints the rest of the rows
        // notice how it updates currentRow

        for (int i = 1; i < numRows; i++) {
            currentRow = createNextRow(currentRow);
            printArray(currentRow);
        }
    }

    // Main method
    public static void main(String[] args) {
        // create a scanner and call createPascalTriangle
        // YOUR CODE HERE
    }
}
```