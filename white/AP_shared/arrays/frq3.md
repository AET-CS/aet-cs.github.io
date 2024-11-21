# AP Computer Science A Free Response Question

## Question: Selection Sort Implementation (9 points)

Selection sort works by repeatedly finding the smallest remaining unsorted element and placing it at the front of the array. The algorithm maintains two sections: a sorted section at the front and an unsorted section after it.

For each iteration i:
1. Find the smallest element in the unsorted section (from index i to end)
2. Swap this element with the first unsorted element (at index i)
3. The sorted section grows by one element

```java
public class ArraySorter {
    /** Sorts elements of arr in ascending order using selection sort
     *  Modifies the array in-place rather than creating a new array
     *  @param arr the array to be sorted
     */
    public void selectionSort(int[] arr)
    {
        /* to be implemented in part (a) */
    }
}
```

### Part A (5 points)

Write the `selectionSort` method implementing the selection sort algorithm described above.

Sample input/output:
```java
Original array: {64, 25, 12, 22, 11}
After method call: {11, 12, 22, 25, 64}
```

### Scoring Guidelines

Part A (5 points):
- +1: Correct outer loop structure
- +1: Finds minimum element correctly
- +1: Properly tracks minimum element's index
- +1: Correctly performs swap operation
- +1: Modifies array in-place without creating new arrays

Common errors (-1 each):
- Creating new arrays instead of in-place modification
- Incorrect loop bounds
- Missing or incorrect swap logic
- Not properly tracking minimum index