
Question 1. Consider the following Java code fragment. What is the value of the variable total after the loops finish executing?
int[][] matrix = {
    {3, 5, 7},
    {2, 4, 6},
    {1, 8, 9}
};
int total = 0;
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        if (i + j == 2) {
            total += matrix[i][j];
        }
    }
}
A)    45
B)    6
C)    16
D)    14
E)    12
Question 2. Consider the following code fragment that works with a two‑dimensional array:
int[][] mat = {
    {2, 4, 6},
    {1, 3, 5},
    {7, 8, 9}
};
int total = 0;
for (int j = 0; j < mat[1].length; j++) {
    total += mat[1][j];
}
System.out.println(total);
What value is printed to the console?
A)    9
B)    10
C)    0
D)    24
E)    15
Question 3. Examine the code that creates a ragged 2‑D array and then mutates one of its rows:
int[] row = {10, 20, 30};
int[][] data = new int[3][];
data[0] = row;
data[1] = new int[]{1, 2, 3};
data[2] = row;
row[1] = 99;
System.out.println(data[2][1]);
What is printed?
A)    2
B)    0
C)    99
D)    20
E)    10
Question 4. Given the following 2‑D array and nested loops, what value is printed?
int[][] grid = {
    {5, 1, 9},
    {2, 6, 3},
    {8, 4, 7}
};
for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] % 2 == 0) {
            grid[i][j] = grid[i][j] / 2;
        }
    }
}
System.out.println(grid[1][1]);
What is the output?
A)    0
B)    6
C)    12
D)    3
E)    5
Question 5. The code below uses enhanced for‑loops to count how many elements are greater than 5 in a ragged 2‑D array:
int[][] nums = {
    {3, 4},
    {5, 6, 7},
    {8}
};
int count = 0;
for (int[] inner : nums) {
    for (int val : inner) {
        if (val > 5) {
            count++;
        }
    }
}
System.out.println(count);
What number is printed?
A)    3
B)    4
C)    2
D)    0
E)    5
Question 6. Consider the following code segment. What is the final value of total after the loops complete?
int[][] grid = {
    {3, 5, 7},
    {2, 4, 6},
    {1, 8, 9}
};
int total = 0;
for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[i].length; j++) {
        if ((i + j) % 2 == 0) {
            total += grid[i][j];
        } else {
            total -= grid[i][j];
        }
    }
}
A)    4
B)    -3
C)    3
D)    21
E)    45
Question 7. Examine the following code that attempts to double every value in a 2‑D array using an enhanced for loop. What is the value of sum after the code executes?
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};
for (int[] row : matrix) {
    for (int val : row) {
        val = val * 2;   // attempt to modify the array
    }
}
int sum = 0;
for (int[] row : matrix) {
    for (int v : row) {
        sum += v;
    }
}
A)    15
B)    0
C)    27
D)    21
E)    42
Question 8. The code below transposes a 3 × 3 square matrix in place. After execution, what is the value stored in m[2][0]?
int[][] m = {
    {0, 1, 2},
    {3, 4, 5},
    {6, 7, 8}
};
for (int i = 0; i < m.length; i++) {
    for (int j = i + 1; j < m[i].length; j++) {
        int temp = m[i][j];
        m[i][j] = m[j][i];
        m[j][i] = temp;
    }
}
A)    0
B)    6
C)    8
D)    3
E)    2
Question 9. A jagged (ragged) 2‑D array is created and filled as shown. After the nested loops finish, what is the final value of count?
int[][] ragged = new int[3][];
ragged[0] = new int[2];   // length 2
ragged[1] = new int[4];   // length 4
ragged[2] = new int[3];   // length 3

int count = 0;
for (int i = 0; i < ragged.length; i++) {
    for (int j = 0; j < ragged[i].length; j++) {
        ragged[i][j] = i + j;
        if (ragged[i][j] % 2 == 0) {
            count++;
        }
    }
}
A)    4
B)    6
C)    0
D)    5
E)    9
Answers
1.    E
2.    A
3.    C
4.    D
5.    A
6.    C
7.    D
8.    E
9.    D

Show less
Feb 18
Let me work through each one carefully.

Q1 — Condition: i+j == 2

(0,2): 7, (1,1): 4, (2,0): 1 → total = 12 ✅ E
Q2 — Sums row 1: 1+3+5 = 9 ✅ A

Q3 — data[0] and data[2] both point to the same row array. After row[1]=99, data[2][1] = 99 ✅ C

Q4 — grid[1][1] = 6, which is even → 6/2 = 3 ✅ D

Q5 — Values > 5: 6, 7, 8 → count = 3 ✅ A

Q6 — Checkerboard pattern (i+j even = add, odd = subtract):

(0,0)=+3, (0,1)=-5, (0,2)=+7
(1,0)=-2, (1,1)=+4, (1,2)=-6
(2,0)=+1, (2,1)=-8, (2,2)=+9
3-5+7-2+4-6+1-8+9 = 3 ✅ C

Q7 — The val = val * 2 inside an enhanced for loop does NOT modify the array (val is a copy). So sum = 1+2+3+4+5+6 = 21 ✅ D

Q8 — Transpose swaps m[i][j] with m[j][i] for j > i. After transposing, m[2][0] gets the original m[0][2] = 2. ❌ The answer key says E (2), which is correct — but note the answer key labels it E. Let me recheck the options: A=0, B=6, C=8, D=3, E=2. Yes, E is correct. The answer key says E ✅

Q9 — Fill ragged[i][j] = i+j, count evens:

Row 0 (j=0,1): 0,1 → evens: {0} → 1
Row 1 (j=0..3): 1,2,3,4 → evens: {2,4} → 2
Row 2 (j=0..2): 2,3,4 → evens: {2,4} → 2
Total count = 5 ✅ D

All 9 answers in the key are correct! One flag though: Q8 — the answer key says E but lists the answer as E=2, which is right. Just make sure students know it's option E, not option D (which is 3).






Claude is AI and can make mistakes. Please double-check responses.
