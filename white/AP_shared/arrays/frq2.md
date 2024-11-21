# AP Computer Science A Free Response Question

## Question: Array Maximum (9 points)

A software application needs to compare corresponding elements from two different data sources. Complete the `ArrayProcessor` class below.

```java
public class ArrayProcessor {
    /** Returns a new array where each element is the maximum of the
     *  corresponding elements from arrays a and b
     *  @param a the first array of integers
     *  @param b the second array of integers
     *  @return a new array where element i is the maximum of a[i] and b[i]
     *  Precondition: a and b have the same length
     */
    public int[] maxElements(int[] a, int[] b)
    {
        /* to be implemented in part (a) */
    }
}
```

### Part A (5 points)

Write the `maxElements` method. Your implementation must:
- Create a new array with the same length as the input arrays
- For each index i, set element i to the larger of a[i] and b[i]
- Return the new array

Sample input/output:
```java
a = {4, 7, 2, 8, 1}
b = {3, 9, 2, 5, 4}
maxElements returns: {4, 9, 2, 8, 4}
```

### Scoring Guidelines

Part A (5 points):
- +1: Creates new array with correct length
- +1: Correctly iterates through all elements
- +1: Properly compares corresponding elements
- +1: Assigns maximum value to new array
- +1: Returns new array