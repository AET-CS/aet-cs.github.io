---
title: "Pascal's Triangle - Java Programming Assignment"
---

## Overview
Create a program that generates and displays Pascal's Triangle using basic integer arrays. Each row in the triangle is computed from the previous row. You should create a new project and a new class named `PascalTriangle` in a file `PascalTriangle.java`

## Methods to Implement

### 1. `createArrayOfSize(int n)`
**Objective:** Create and return an integer array of length n.

### 2. `printArray(int[] s)`
**Objective:** Print the contents of array s on one line with spaces between elements.

### 3. `createNextRow(int[] r)`
**Objective:** Given an array r representing one row of Pascal's triangle, create and return a new array representing the next row.

**Version 1 (for testing):** Create a new array that is one element longer than r, and fill it with all 1s. This allows you to test your array creation and printing logic.

**Version 2 (final):** Modify to create the actual Pascal's triangle row:
- The first element of the new row should be 1
- The last element of the new row should be 1
- Each middle element is the sum of two adjacent elements from the previous row

### 4. `createPascalTriangle(int numRows)`
**Objective:** Generate and print Pascal's triangle with the specified number of rows.
- Start with the first row: [1]
- Use the methods above to create each subsequent row
- Print each row as it is created

### 5. `main`
**Objective:** Ask the user for the number of rows and generate the triangle.
- Use a Scanner to get input from the user
- Prompt the user to enter the number of rows
- Call createPascalTriangle with the user's input

**Example:**
```java
Scanner scanner = new Scanner(System.in);
System.out.print("Enter number of rows: ");
int numRows = scanner.nextInt();
```

---