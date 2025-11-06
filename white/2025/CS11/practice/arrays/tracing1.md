## Array Tracing Problems 1

**Problem 1**
```java
public static int mystery1(int[] arr) {
    int result = 0;
    for (int i = 0; i < arr.length; i++) {
        result = result + arr[i];
    }
    return result;
}
```
What does mystery1(new int[]{2, 5, 3, 8}) return?


**Problem 2**
```java
public static void mystery2(int[] arr) {
    for (int i = 0; i < arr.length; i += 2) {
        System.out.print(arr[i] + " ");
    }
}
```
What does mystery2(new int[]{10, 20, 30, 40, 50}) print?


**Problem 3**
```java
public static int mystery3(int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] > 5) {
            count++;
        }
    }
    return count;
}
```
What does mystery3(new int[]{3, 7, 2, 9, 5, 8}) return?


**Problem 4**
```java
public static void mystery4(int[] arr) {
    for (int i = arr.length - 1; i >= 0; i--) {
        System.out.print(arr[i] + " ");
    }
}
```
What does mystery4(new int[]{1, 2, 3, 4}) print?


**Problem 5: String Accumulation**
```java
public static String mystery5(String[] arr) {
    String result = "";
    for (int i = 0; i < arr.length; i++) {
        result = result + arr[i];
    }
    return result;
}
```
What does mystery5(new String[]{"cat", "dog", "pig"}) return?


**Problem 6**
```java
public static int mystery6(int[] arr) {
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max;
}
```
What does mystery6(new int[]{4, 9, 2, 11, 5}) return?


**Problem 7**
```java
public static void mystery7(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] % 2 == 0) {
            arr[i] = arr[i] * 2;
        }
    }
    System.out.print(arr[0] + " " + arr[1] + " " + arr[2]);
}
```
What does mystery7(new int[]{3, 4, 5}) print?


**Problem 8**
```java
public static int mystery8(int[] arr) {
    int result = 1;
    for (int value : arr) {
        result = result * value;
    }
    return result;
}
```
What does mystery8(new int[]{2, 3, 4}) return?


**Problem 9**
```java
public static boolean mystery9(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1]) {
            return false;
        }
    }
    return true;
}
```
What does mystery9(new int[]{1, 3, 5, 4, 7}) return?


**Problem 10**
```java
public static int mystery10(int[] arr) {
    int result = 0;
    for (int i = 0; i < arr.length; i++) {
        if (i % 2 == 0) {
            result = result + arr[i];
        } else {
            result = result - arr[i];
        }
    }
    return result;
}
```
What does mystery10(new int[]{10, 3, 8, 5, 6}) return?

---
geometry: margin=0.5in, letterpaper
header-includes:
  - \usepackage{fullpage}
---
