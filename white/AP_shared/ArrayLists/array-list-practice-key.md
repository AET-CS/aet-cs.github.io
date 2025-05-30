---
title: "ArrayList Practice Worksheet"
---

-------------------
## Solutions

### Question 1
Step by step:
1. `["cat"]`
2. `["cat", "dog"]`
3. `["cat", "bird", "dog"]`
4. `["bird", "dog"]`
5. `["bird", "dog", "fish"]`
6. `["dog", "fish"]`

Final answer: `["dog", "fish"]`

### Question 2
Step by step:
1. `[5, 10, 12, 15, 20]`
2. `[5, 12, 15, 20]`
3. `[3, 5, 12, 15, 20]`
4. `[3, 5, 12, 15]`
5. `[3, 5, 12, 15, 5]`

Final answer: `[3, 5, 12, 15, 5]`

### Question 3
Step by step:
1. `["red", "blue", "green", "yellow"]`
2. `["red", "purple", "green", "yellow"]`
3. `["red", "purple", "yellow"]`
4. `["red", "orange", "purple", "yellow"]`
5. `["orange", "purple", "yellow"]`
6. `["orange", "purple", "yellow", "orange"]`

Final answer: `["orange", "purple", "yellow", "orange"]`

### Question 4
Step by step:
1. `[2, 4, 6, 8, 10, 12, 14, 3]`
2. After removal loop (removes numbers divisible by 4):
   - When i=1, removes 4
   - When i=3, removes 8
   - When i=5, removes 12
   - Result: `[2, 6, 10, 14, 3]`
3. `[1, 2, 6, 10, 14, 3]`
4. After doubling loop:
   - Doubles each number
   - Result: `[2, 4, 12, 20, 28, 6]`

Final answer: `[2, 4, 12, 20, 28, 6]`

Note for Question 4: This question demonstrates why removing elements while iterating forward through a list can be tricky. After removing 4, the list shifts left, so the next element checked is 10 (not 6). This is why not all multiples of 4 are removed.
