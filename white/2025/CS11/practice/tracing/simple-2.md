# AP Computer Science - Easy Starter Questions (Version 2)

**Name: ________________    Date: ________________**

## Question 1: Simple Variable Trace
```java
public static void trace1() {
    int a = 8;
    int b = 2;
    a = a - b;
    b = a + b;
    System.out.println(a + " " + b);
}
```
**What is printed when trace1() is called?** ________________

## Question 2: Basic Loop
```java
public static void trace2() {
    int sum = 0;
    for (int i = 2; i <= 8; i = i + 2) {
        sum = sum + i;
    }
    System.out.println(sum);
}
```
**What is printed when trace2() is called?** ________________

## Question 3: Simple Conditional
```java
public static void trace3(int x) {
    if (x < 5) {
        x = x * 3;
    } else {
        x = x - 2;
    }
    System.out.println(x);
}
```
**What is printed when trace3(4) is called?** ________________

## Question 4: Loop with Counter
```java
public static void trace4() {
    int n = 15;
    int count = 0;
    while (n > 1) {
        n = n / 2;
        count++;
    }
    System.out.println(count + " " + n);
}
```
**What is printed when trace4() is called?** ________________

## Question 5: Loop with Conditional
```java
public static void trace5(int n) {
    int result = 0;
    for (int i = 1; i <= n; i++) {
        if (i % 3 == 0) {
            result = result + i;
        }
    }
    System.out.println(result);
}
```
**What is printed when trace5(10) is called?** ________________

## Question 6: While Loop with Update
```java
public static void trace6(int n) {
    int x = 1;
    int count = 0;
    while (x * 3 <= n) {
        x = x * 3;
        count++;
    }
    System.out.println(x + " " + count);
}
```
**What is printed when trace6(30) is called?** ________________

## Question 7: Nested Conditionals
```java
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
```
**What is printed when trace7(12, 7) is called?** ________________

## Question 8: Loop with Multiple Updates
```java
public static void trace8() {
    int x = 2;
    int y = 20;
    while (x < y) {
        x = x * 2;
        y = y - 3;
    }
    System.out.println(x + " " + y);
}
```
**What is printed when trace8() is called?** ________________

## Question 9: Accumulator Pattern
```java
public static void trace9(int n) {
    int product = 1;
    int count = 0;
    while (product < n) {
        product = product * 2;
        count++;
    }
    System.out.println(count + " " + product);
}
```
**What is printed when trace9(100) is called?** ________________

## Question 10: Complex Loop Logic
```java
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
```
**What is printed when trace10(4) is called?** ________________