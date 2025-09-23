# Make That Pattern!
## A Step-by-Step Guide to Creating Patterns with Loops

---

## Getting Started: The Rectangle

(These lessons were created with the assistance of Claude AI, who tends to be VERY EXCITABLE!!!!!!)

Let's begin with something simple - a solid rectangle made of stars. Here's the code and what it produces:

**Code:**
```java
public class PatternPractice {
    public static void rectangle1() {
        // Make a 4x6 rectangle
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                System.out.print("*");
            }
            System.out.println(); // Move to next line
        }
    }

    public static void main(String[] args) {
        rectangle1();
    }
}
```

**Output:**
```
******
******
******
******
```

**How it works:**
- The **outer loop** runs 4 times (one for each row)
- The **inner loop** runs 6 times (one for each star in that row)
- `System.out.println()` moves us to the next line after each row

---

## Your Turn: Practice Problems

### Problem 1: Type and Run
1. Type up the rectangle code above exactly as shown
2. Compile and run it
3. Make sure you get the exact output shown

### Problem 2: Change the Size
Make a new method `rectangle2`. Copy the `rectangle1` code and then modify it.
Modify your code to make a **3x8 rectangle** (3 rows, 8 columns). Make sure to
call `rectangle2` from `main`. (If you call both methods you should add some blank lines between the two outputs.)

**Expected output:**
```
********
********
********
```

**Hint:** You only need to change two numbers in your code!

---

## Problem 3: Your First Triangle

For each of these problems add a new method to your class and call it from main. Do not delete any previous problems!

Now let's make a **left-aligned triangle** that grows by one star each row:

**Target output:**
```
*
**
***
****
*****
```

**Your mission:** Write the code to create this pattern.

**Hints:**
- You still need two loops (outer for rows, inner for stars)
- On row 1, print 1 star. On row 2, print 2 stars. See the pattern?
- What should the inner loop condition be?

---

## Stepping It Up: Intermediate Challenges

### Problem 4: Right-Aligned Triangle with Spaces

Make this pattern:
```
    *
   **
  ***
 ****
*****
```

**Hints:**
- You need **three** things on each row: spaces, then stars, then a newline
- Row 1: 4 spaces + 1 star
- Row 2: 3 spaces + 2 stars
- Row 3: 2 spaces + 3 stars
- etc

**Extension:**
Define a integer parameter `num_rows` in your code that
determines the number of rows in the triangle. Modify your loops to use `num_rows`.

### Problem 5: The Odd Numbers Challenge

Create this pattern using **only odd numbers** of stars:
```
*
***
*****
*******
*********
```

### Problem 6: The Centered Pyramid

Combine your skills to make a **centered pyramid**:
```
    *
   ***
  *****
 *******
*********
```

**Breaking it down:**
- This combines the "spaces" logic from Problem 4
- With the "odd numbers" logic from Problem 5
- For 5 rows, you need the right number of leading spaces to center each row

---

## The Ultimate Challenge: The Hourglass

Ready for the big one? Create this hourglass pattern:

```
*********
 *******
  *****
   ***
    *
   ***
  *****
 *******
*********
```

**Strategy suggestions:**
- Think of it as two pyramids: one going down, one going up
- Or... think of it as one pyramid, then the same pyramid "backwards"
- You might want to write **two separate loops**
- The top half decreases (like an upside-down pyramid)
- The bottom half increases (like a normal pyramid)

**Advanced approach:** Can you do it with just one loop? Think about what happens at the "middle" point.

---

## Reflection Questions

1. Which problem was the hardest? Why?
2. What pattern did you notice about the relationship between row number and:
   - Number of stars?
   - Number of spaces?
3. How would you explain nested loops to a friend?
4. What other patterns could you create with these techniques?

---

*Good luck, and remember: every expert was once a beginner. Take your time, experiment, and don't be afraid to ask for help!*