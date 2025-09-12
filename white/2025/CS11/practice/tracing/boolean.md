---
title: CS 11 - Tracing Boolean Operators
---

**Name: ________________    Date: ________________**


## Question 1: Simple AND
```java
public static void bool1(int x, int y) {
    if (x > 5 && y < 10) {
        System.out.println("A");
    } else {
        System.out.println("B");
    }
}
```
**What is printed when bool1(7, 8) is called?** ________________

**What is printed when bool1(3, 8) is called?** ________________


## Question 2: Simple OR
```java
public static void bool2(int n) {
    if (n < 0 || n > 100) {
        System.out.println("Out of range");
    } else {
        System.out.println("Valid");
    }
}
```
**What is printed when bool2(50) is called?** ________________

**What is printed when bool2(-5) is called?** ________________


## Question 3: NOT Operator
Note: In this question `flag` is a `boolean` variable which is Java's way of saying
it can only have 2 values: `true` or `false`. For example if `flag = true` then
`!flag` would be `false` and `flag || false` would be `true`.

```java
public static void bool3(boolean flag, int x) {
    if (!flag && x > 10) {
        System.out.println("Yes");
    } else {
        System.out.println("No");
    }
}
```
**What is printed when bool3(false, 15) is called?** ________________

**What is printed when bool3(true, 15) is called?** ________________


## Question 4: Complex AND/OR
```java
public static void bool4(int a, int b, int c) {
    if ((a > b && b > c) || (a < b && b < c)) {
        System.out.println("Ordered");
    } else {
        System.out.println("Not ordered");
    }
}
```
**What is printed when bool4(5, 3, 1) is called?** ________________

**What is printed when bool4(2, 8, 6) is called?** ________________


## Question 5: Multiple Conditions
```java
public static void bool5(int x, int y) {
    int result = 0;
    if (x > 0 && y > 0 && x + y > 10) {
        result = 1;
    } else if (x < 0 || y < 0) {
        result = -1;
    } else {
        result = 0;
    }
    System.out.println(result);
}
```
**What is printed when bool5(6, 7) is called?** ________________

**What is printed when bool5(3, 4) is called?** ________________

**What is printed when bool5(-2, 5) is called?** ________________


## Question 6: Short-Circuit Evaluation
Note: Short-circuit evaluation means if Java can determine the boolean value of an expression by just looking at the first value, it
does not evaluate the second one. Example: `5>3 && 3/0=10000` evaluates to `true` because the first bit is true; the second bit doesn't matter.

```java
public static void bool6(int x, int y) {
    if (x != 0 && y / x > 2) {
        System.out.println("True");
    } else {
        System.out.println("False");
    }
}
```
**What is printed when bool6(3, 7) is called?** ________________

**What is printed when bool6(0, 10) is called?** ________________


## Question 7: Complex Nested Logic
```java
public static void bool7(int a, int b, int c) {
    boolean result = false;
    if ((a > 10 || b > 10) && !(a > 10 && b > 10)) {
        if (c % 2 == 0) {
            result = true;
        }
    }
    System.out.println(result);
}
```
**What is printed when bool7(15, 5, 4) is called?** ________________

**What is printed when bool7(15, 12, 4) is called?** ________________

**What is printed when bool7(5, 8, 3) is called?** ________________


## Question 8: Multiple Boolean Expressions
```java
public static void bool8(int n) {
    if ((n > 0 && n < 10) || (n > 20 && n < 30) || (n > 40 && n < 50)) {
        System.out.println("In range");
    } else if (n == 10 || n == 20 || n == 30 || n == 40 || n == 50) {
        System.out.println("Boundary");
    } else {
        System.out.println("Out of range");
    }
}
```
**What is printed when bool8(25) is called?** ________________

**What is printed when bool8(30) is called?** ________________

**What is printed when bool8(35) is called?** ________________


## Question 9: Complex Boolean with Counter
```java
public static void bool9(int x, int y, int z) {
    int count = 0;
    if (x > 5 && y > 5 || z > 5) {
        count++;
    }
    if (!(x <= 5 || y <= 5) && z > 0) {
        count++;
    }
    if (x + y > 10 && x + z > 10 && y + z > 10) {
        count++;
    }
    System.out.println(count);
}
```
**What is printed when bool9(6, 7, 2) is called?** ________________

**What is printed when bool9(3, 4, 8) is called?** ________________


## Question 10: Extreme Boolean Logic
```java
public static void bool10(boolean p, boolean q, boolean r) {
    if ((p && !q) || (!p && q)) {  // XOR logic
        if (r) {
            System.out.println("A");
        } else {
            System.out.println("B");
        }
    } else if ((p || q) && r) {
        System.out.println("C");
    } else {
        System.out.println("D");
    }
}
```
**What is printed when bool10(true, false, true) is called?** ________________

**What is printed when bool10(true, true, true) is called?** ________________

**What is printed when bool10(false, false, false) is called?** ________________