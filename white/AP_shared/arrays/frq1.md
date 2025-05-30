---
title: "AP Computer Science A Free Response Question"
---


## Question: Image Filter (9 points)

Consider a grayscale image represented by a 2D array of integers where each element represents the brightness of a pixel from 0 to 255. The `ImageProcessor` class contains a method that filters pixels based on minimum and maximum thresholds.

```java
public class ImageProcessor {
    /** Modifies pixel values based on minimum and maximum thresholds:
     *  - Values below minThreshold become 0
     *  - Values above maxThreshold become 255
     *  - Values between thresholds remain unchanged
     *  @param image the original image array
     *  @param minThreshold the minimum cutoff value
     *  @param maxThreshold the maximum cutoff value
     *  @return the processed image array
     *  Precondition: 0 ≤ minThreshold ≤ maxThreshold ≤ 255
     */
    public int[][] thresholdRange(int[][] image, int minThreshold, int maxThreshold)
    {
        /* to be implemented in part (a) */
    }
}
```

### Part A (5 points)

Write the `thresholdRange` method. Your implementation must:
- Create a new 2D array with the same dimensions as the input image
- Set values less than minThreshold to 0
- Set values greater than maxThreshold to 255
- Keep values between thresholds (inclusive) unchanged
- Return the new array

Sample input/output:
```java
Original array:          After thresholdRange(image, 100, 150):
120 135 180             120 135 255
80  125 145             0   125 145
95  115 140             0   115 140
```

### Scoring Guidelines

Part A (5 points):
- +1: Creates new array with correct dimensions
- +1: Correctly iterates through all elements
- +1: Properly checks against both thresholds
- +1: Correctly assigns 0, 255, or unchanged values
- +1: Returns new array instead of modifying original