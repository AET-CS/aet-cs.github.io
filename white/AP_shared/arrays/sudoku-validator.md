# Sudoku Validator Assignment

## Overview
A valid Sudoku puzzle consists of a 9x9 grid where each row, column, and 3x3 sub-square contains the digits 1-9 exactly once. Your task is to write a program that determines if a completed 9x9 grid is a valid Sudoku solution.

Example of a valid Sudoku:
```
5 3 4 | 6 7 8 | 9 1 2
6 7 2 | 1 9 5 | 3 4 8
1 9 8 | 3 4 2 | 5 6 7
--------------------
8 5 9 | 7 6 1 | 4 2 3
4 2 6 | 8 5 3 | 7 9 1
7 1 3 | 9 2 4 | 8 5 6
--------------------
9 6 1 | 5 3 7 | 2 8 4
2 8 7 | 4 1 9 | 6 3 5
3 4 5 | 2 8 6 | 1 7 9
```

## Required Methods

### 1. Row Validator
```java
/**
 * Checks if a given row contains all digits 1-9 exactly once
 * @param grid the 9x9 Sudoku grid
 * @param row the row index to check (0-8)
 * @return true if the row contains all digits 1-9, false otherwise
 */
public static boolean isValidRow(int[][] grid, int row)
```

### 2. Column Validator
```java
/**
 * Checks if a given column contains all digits 1-9 exactly once
 * @param grid the 9x9 Sudoku grid
 * @param col the column index to check (0-8)
 * @return true if the column contains all digits 1-9, false otherwise
 */
public static boolean isValidCol(int[][] grid, int col)
```

### 3. 3x3 Square Validator
```java
/**
 * Checks if a 3x3 square contains all digits 1-9 exactly once
 * @param grid the 9x9 Sudoku grid
 * @param startRow the starting row of the 3x3 square (0, 3, or 6)
 * @param startCol the starting column of the 3x3 square (0, 3, or 6)
 * @return true if the 3x3 square contains all digits 1-9, false otherwise
 */
public static boolean isValidSquare(int[][] grid, int startRow, int startCol)
```

### 4. Helper Method
```java
/**
 * Checks if an array of 9 numbers contains all digits 1-9
 * This helper method can be used by row, column, and square validators
 * @param numbers array of 9 integers to check
 * @return true if array contains all digits 1-9, false otherwise
 */
public static boolean containsAllDigits(int[] numbers)
```

### 5. Full Sudoku Validator
```java
/**
 * Determines if the given 9x9 grid is a valid Sudoku solution.
 * A valid solution must:
 * 1. Have all rows contain digits 1-9
 * 2. Have all columns contain digits 1-9
 * 3. Have all 3x3 squares contain digits 1-9
 *
 * @param grid the 9x9 Sudoku grid to verify
 * @return true if the grid is a valid solution, false otherwise
 */
public static boolean isValidSudoku(int[][] grid)
```

## Test Cases

### 1. Valid Sudoku Solution
```java
int[][] valid = {
    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,7,2,8,4},
    {2,8,7,4,1,9,6,3,5},
    {3,4,5,2,8,6,1,7,9}
};
```

### 2. Invalid Row
```java
int[][] invalidRow = {
    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,7,2,8,4},
    {2,8,7,4,1,9,6,3,5},
    {3,4,5,2,8,6,1,7,5}  // last row has two 5s
};
```

### 3. Invalid Square
```java
int[][] invalidSquare = {
    {1,2,3,4,5,6,7,8,9},
    {4,5,6,7,8,9,1,2,3},
    {7,8,9,1,2,3,4,5,6},
    {2,3,1,5,6,4,8,9,7},
    {5,6,4,8,9,7,2,3,1},
    {8,9,7,2,3,1,5,6,4},
    {3,1,2,6,4,5,9,7,8},
    {6,4,5,9,7,8,3,1,2},
    {9,7,8,3,1,2,6,4,5}
};
```

## Implementation Tips
1. Start by implementing and testing the helper method `containsAllDigits`
2. Use the helper method in your row, column, and square validators
3. For the square validator, remember you only need to check squares starting at (0,0), (0,3), (0,6), (3,0), etc.
4. Consider using a boolean array or HashSet to track which digits have been seen
5. Remember to handle invalid input (null arrays, wrong size arrays)
6. For efficiency, return false as soon as any invalid condition is found

## Bonus Challenges
1. Implement a method to check if a partially filled Sudoku grid (with zeros for empty cells) follows Sudoku rules
2. Create a method that identifies which specific rule is violated (row, column, or square)
3. Implement a method that returns all positions where a specific digit appears in the grid

## Example Output
```
Checking Sudoku solution:
5 3 4 | 6 7 8 | 9 1 2
6 7 2 | 1 9 5 | 3 4 8
1 9 8 | 3 4 2 | 5 6 7
---------------------
8 5 9 | 7 6 1 | 4 2 3
4 2 6 | 8 5 3 | 7 9 1
7 1 3 | 9 2 4 | 8 5 6
---------------------
9 6 1 | 5 3 7 | 2 8 4
2 8 7 | 4 1 9 | 6 3 5
3 4 5 | 2 8 6 | 1 7 9

Checking rows... OK
Checking columns... OK
Checking 3x3 squares... OK

Result: Valid Sudoku solution!
```
