## FRQ2 solution

```java
public int[] maxElements(int[] a, int[] b) {
    int[] result = new int[a.length];

    for (int i = 0; i < a.length; i++) {
        result[i] = Math.max(a[i], b[i]);
    }

    return result;
}
```


```java
public int[] maxElements(int[] a, int[] b) {
    int[] result = new int[a.length];

    for (int i = 0; i < a.length; i++) {
        if (a[i] > b[i]) {
            result[i] = a[i];
        } else {
            result[i] = b[i];
        }
    }

    return result;
}
```