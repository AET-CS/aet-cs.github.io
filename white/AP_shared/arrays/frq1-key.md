## FRQ1 Solution

```java
public int[][] thresholdRange(int[][] image, int minThreshold, int maxThreshold) {
    int[][] result = new int[image.length][image[0].length];

    for (int row = 0; row < image.length; row++) {
        for (int col = 0; col < image[0].length; col++) {
            if (image[row][col] < minThreshold) {
                result[row][col] = 0;
            } else if (image[row][col] > maxThreshold) {
                result[row][col] = 255;
            } else {
                result[row][col] = image[row][col];
            }
        }
    }

    return result;
}
```