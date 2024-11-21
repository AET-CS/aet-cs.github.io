# FRQ3 Key

```java
public void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        // Find index of minimum element in unsorted section
        int minIndex = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }

        // Swap minimum element with first unsorted position
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
}
```