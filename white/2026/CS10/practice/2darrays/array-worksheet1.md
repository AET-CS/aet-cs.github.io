# 2D Array Tracing Worksheet

**AP Computer Science A**

Use the following 2D array for all questions:

```java
int[][] arr = {
    {3, 1, 4, 1},
    {5, 9, 2, 6},
    {5, 3, 5, 8},
    {9, 7, 9, 3}
};
```


## Question 1:

```java
int sum = 0;
for (int r = 0; r < arr.length; r++) {
    for (int c = 0; c < arr[0].length; c++) {
        sum += arr[r][c];
    }
}
System.out.println(sum);
```

**Output:** _______________


## Question 2:

```java
int sum = 0;
for (int r = 0; r < arr.length; r++) {
    for (int c = 0; c < arr[0].length; c++) {
        if (arr[r][c] % 2 == 0) {
            sum += arr[r][c];
        }
    }
}
System.out.println(sum);
```

**Output:** _______________


## Question 3:
```java
int sum = 0;
for (int r = 0; r < arr.length; r++) {
    for (int c = 0; c < arr[0].length; c++) {
        if (r % 2 == 0 || c % 2 == 0) {
            sum += arr[r][c];
        }
    }
}
System.out.println(sum);
```

**Output:** _______________


## Question 4:

```java
int count = 0;
for (int i = 0; i < arr.length; i++) {
    if (arr[i][i] % 2 == 0) {
        count++;
    }
}
System.out.println(count);
```

**Output:** _______________


## Question 5:
```java
int sum = 0;
for (int r = 0; r < arr.length; r++) {
    for (int c = r; c < arr[0].length; c++) {
        sum += arr[r][c];
    }
}
System.out.println(sum);
```

**Output:** _______________


## Question 6:

```java
int sum = 0;
for (int i = 0; i < arr.length; i++) {
    sum += arr[0][i];
    sum += arr[3][i];
    if (i > 0 && i < 3) {
        sum += arr[i][0];
        sum += arr[i][3];
    }
}
System.out.println(sum);
```

**Output:** _______________


## Question 7:

```java
int count = 0;
for (int r = 0; r < arr.length; r++) {
    for (int c = 1; c < arr[0].length; c++) {
        if (arr[r][c] > arr[r][c - 1]) {
            count++;
        }
    }
}
System.out.println(count);
```

**Output:** _______________


## Question 8:

```java
for (int r = 1; r < arr.length; r++) {
    for (int c = 0; c < arr[0].length; c++) {
        arr[r][c] = arr[r][c] * arr[r - 1][c];
    }
}
System.out.println(arr[3][2]);
```

**Output:** _______________
