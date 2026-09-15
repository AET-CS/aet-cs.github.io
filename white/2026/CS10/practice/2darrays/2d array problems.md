# AP CS Questions

**Question 1.** Consider the following Java code fragment. What value is printed to the console?

```java
int[][] matrix = {
    {3, 5, 7},
    {2, 4, 6},
    {1, 8, 9}
};

int sum = 0;
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        if (i == j) {
            sum += matrix[i][j];
        } else if (i + j == matrix.length - 1) {
            sum += matrix[i][j] * 2;
        }
    }
}
System.out.println(sum);
```

A. `40`
B. `32`
C. `24`
D. `9`
E. `21`

**Question 2.** Consider the following code fragment:

```java
int[][] matrix = {
    {2, 4, 6},
    {8, 10, 12},
    {14, 16, 18}
};
int x = matrix[1][2];
System.out.println(x);
```
What value is printed to the console?

A. `2`
B. `10`
C. `18`
D. `12`
E. `6`

**Question 3.** Examine the code below:

```java
int[][] scores = {
    {5, 7, 9},
    {6, 8, 10}
};
int total = 0;
for (int r = 0; r < scores.length; r++) {
    total += scores[r][1];
}
System.out.println(total);
```
What value is printed after the loop finishes?

A. `11`
B. `19`
C. `15`
D. `0`
E. `45`

**Question 4.** What is the output of the following program?

```java
int[][] grid = new int[2][3];   // 2 rows, 3 columns, all elements default to 0
grid[0][1] = 5;                // assign 5 to the element in row 0, column 1
System.out.println(grid[1][2]);
```
Select the value printed to the console.

A. `0`
B. `null`
C. `5`
D. `1`
E. `-5`

**Question 5.** Consider the following Java code. What is the exact 2‑dimensional array returned by the call `mystery(matrix)`?

```java
public class Mystery {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = mystery(matrix);
        // result is printed for inspection
    }

    public static int[][] mystery(int[][] m) {
        int rows = m.length;        
        int cols = m[0].length;      
        int[][] t = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                t[j][i] = m[i][j];   
            }
        }
        return t;
    }
}
```

A. `{ {3, 2, 1}, {6, 5, 4}, {9, 8, 7} }`
B. `{ {2, 6, 10}, {6, 10, 14}, {10, 14, 18} }`
C. `{ {1, 4, 7}, {2, 5, 8}, {3, 6, 9} }`
D. `{ {1, 2, 3}, {4, 5, 6}, {7, 8, 9} }`
E. `{ {7, 8, 9}, {4, 5, 6}, {1, 2, 3} }`

**Question 6.** What will be the contents of the 2‑D array `M` after the following code executes?

```java
int[][] M = {
    {5, 2, 8},
    {1, 4, 7},
    {9, 0, 3},
    {6, 6, 6}
};

int temp;
for (int c = 0; c < M[0].length; c++) {
    temp = M[1][c];
    M[1][c] = M[3][c];
    M[3][c] = temp;
}
```

Select the correct representation of `M` (rows shown in order, each row in brackets).

A. `[[5,2,8],[6,6,6],[9,0,3],[1,4,7]]`
B. `[[5,2,8],[6,6,6],[9,0,3],[6,6,6]]`
C. `[[5,2,8],[6,6,7],[9,0,3],[1,4,6]]`
D. `[[9,0,3],[1,4,7],[5,2,8],[6,6,6]]`
E. `[[8,2,5],[7,4,1],[3,0,9],[6,6,6]]`

## Answers

1. B
2. D
3. C
4. A
5. C
6. A
