# AP Computer Science A - Code Tracing Questions

**Name: ________________    Date: ________________**

---

## Question 1: Loop and Conditional
```java
public static void mystery1(int n) {
    int x = 1;
    int y = 1;
    while (2 * y <= n) {
        y = y * 2;
        x++;
    }
    System.out.println(x + " " + y);
}
```
**What is printed when mystery1(40) is called?** ________________

---

## Question 2: Array Manipulation
```java
public static void mystery2(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1]) {
            arr[i] = arr[i] + arr[i + 1];
            arr[i + 1] = arr[i] - arr[i + 1];
            arr[i] = arr[i] - arr[i + 1];
        }
    }
}
```
**Given:** `int[] nums = {7, 3, 8, 4, 1, 9};`

**After calling mystery2(nums), what are the contents of nums?** ________________

---

## Question 3: 2D Array Traversal
```java
public static int mystery3(int[][] mat) {
    int sum = 0;
    for (int r = 0; r < mat.length; r++) {
        for (int c = 0; c < mat[0].length; c++) {
            if (r == c) {
                sum += mat[r][c];
            }
        }
    }
    return sum;
}
```
**Given the 2D array:**
```java
int[][] grid = {{2, 5, 1},
                {3, 4, 6},
                {7, 8, 9}};
```
**What value is returned by mystery3(grid)?** ________________

---

## Question 4: Recursion
```java
public static int mystery4(int n) {
    if (n <= 1) {
        return n;
    }
    return mystery4(n - 1) + mystery4(n - 3);
}
```
**What value is returned by mystery4(5)?** ________________

**Show your work (trace through the recursive calls):**

---

## Question 5: String Manipulation
```java
public static String mystery5(String str) {
    String result = "";
    for (int i = 0; i < str.length(); i++) {
        if (i % 2 == 0) {
            result = str.substring(i, i + 1) + result;
        } else {
            result = result + str.substring(i, i + 1);
        }
    }
    return result;
}
```
**What is returned by mystery5("COMPUTER")?** ________________

---

## Question 6: ArrayList Operations
```java
import java.util.ArrayList;

public static void mystery6(ArrayList<Integer> list) {
    for (int i = list.size() - 1; i >= 0; i--) {
        if (list.get(i) % 2 == 0) {
            list.add(i, list.get(i) / 2);
        }
    }
}
```
**Given:** `ArrayList<Integer> nums = new ArrayList<>();`
**nums contains:** `[5, 8, 3, 12, 7]`

**After calling mystery6(nums), what does nums contain?** ________________