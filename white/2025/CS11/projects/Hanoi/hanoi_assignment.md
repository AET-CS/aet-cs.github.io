---
title: Towers of Hanoi
---

## Overview

Write a program that solves the Tower of Hanoi puzzle. Your program will take user input and print each move required to transfer a stack of disks from one peg to another.

## Input

Your program should prompt the user for:
- Starting peg (1, 2, or 3)
- Ending peg (1, 2, or 3)
- Number of disks (1–26)

## Output

Print each move in the format:
```
Move A from peg 1 to peg 3
Move B from peg 1 to peg 2
Move A from peg 3 to peg 2
...
```

Disk `A` is the smallest (top), `B` is next, and so on.

## Requirements

1. Create a class called `Hanoi`

2. Use this array to convert disk numbers to letters:
   ```java
   String[] letters = {"", "A", "B", "C", "D", "E", ...};
   ```

3. Write a recursive method with this signature:
   ```java
   public void hanoi(int n, int from, int to, int temp)
   ```
   - `n` – the number of disks to move
   - `from` – the source peg
   - `to` – the destination peg
   - `temp` – the temporary (spare) peg

4. The recursive logic:
   - **Base case:** If `n == 0`, do nothing
   - **Recursive case:** (fill in the steps from class notes here)

## Example Run

```
Enter starting peg: 1
Enter ending peg: 3
Enter number of disks: 3

Move A from peg 1 to peg 3
Move B from peg 1 to peg 2
Move A from peg 3 to peg 2
Move C from peg 1 to peg 3
Move A from peg 2 to peg 1
Move B from peg 2 to peg 3
Move A from peg 1 to peg 3
```

## Submission

Submit your `Hanoi.java` file. You should solve "Hanoi 5 disks from 3 to 2" in your submission and print the results.
