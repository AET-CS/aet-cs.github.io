---
title: "Magic Square Lab"
---


## Overview
A magic square is a square array of numbers where the sum of each row, column, and diagonal is the same. Your task is to write a program that determines if a given 2D array is a magic square.

For example, this is a magic square (sum = 15):
```
8 1 6
3 5 7
4 9 2
```

This is not a magic square:
```
1 2 3
4 5 6
7 8 9
```

## Assignment Requirements

Write the following methods to help determine if a square array is a magic square:

### 1. Row Sum
```java
/**
 * Returns the sum of a specified row in the array
 * @param square the 2D array to process
 * @param row the row index to sum
 * @return the sum of all elements in the specified row
 */
public static int rowSum(int[][] square, int row)
```

### 2. Column Sum
```java
/**
 * Returns the sum of a specified column in the array
 * @param square the 2D array to process
 * @param col the column index to sum
 * @return the sum of all elements in the specified column
 */
public static int colSum(int[][] square, int col)
```

### 3. Diagonal Sums
```java
/**
 * Returns the sum of the main diagonal (top-left to bottom-right)
 * @param square the 2D array to process
 * @return the sum of elements along the main diagonal
 */
public static int mainDiagSum(int[][] square)

/**
 * Returns the sum of the other diagonal (top-right to bottom-left)
 * @param square the 2D array to process
 * @return the sum of elements along the other diagonal
 */
public static int otherDiagSum(int[][] square)
```

### 4. Magic Square Check
```java
/**
 * Determines if the given square array is a magic square.
 * A magic square must:
 * 1. Be square (same number of rows and columns)
 * 2. Have all row sums equal to each other
 * 3. Have all column sums equal to each other
 * 4. Have both diagonal sums equal to the row/column sums
 *
 * @param square the 2D array to check
 * @return true if the array is a magic square, false otherwise
 */
public static boolean isMagicSquare(int[][] square)
```

## Testing
Test your methods with at least these cases:

1. A valid 3x3 magic square:
```java
int[][] valid = {
    {8, 1, 6},
    {3, 5, 7},
    {4, 9, 2}
};
```

2. An invalid 3x3 square:
```java
int[][] invalid = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

3. A non-square array:
```java
int[][] notSquare = {
    {1, 2, 3},
    {4, 5, 6}
};
```

## Implementation Tips
1. Start by writing and testing each helper method individually
2. For `isMagicSquare`, first check if the array is square
3. Then check if any row sum equals any column sum
4. Finally check if both diagonal sums equal the row/column sums
5. Use a constant variable to store the "magic sum" once you find it
6. Remember to handle edge cases (null arrays, empty arrays, non-square arrays)
7. Your solution should work on *any* square array not just 3x3.

## Bonus Challenge
Extend your program to:
1. Verify that each number 1 through n² appears exactly once
2. Find the magic constant based on array size (it should be n(n²+1)/2 where n is the size)
3. Create a magic square of a given size $n$

## Example Output
Your program should provide clear output, for example:
```
Testing magic square:
8 1 6
3 5 7
4 9 2

Row sums: 15, 15, 15
Column sums: 15, 15, 15
Diagonal sums: 15, 15

Result: This is a magic square!
```
