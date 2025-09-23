# Pattern Challenge Examples
## Advanced Loop Practice - No Hints!

**Your mission:** Study each pattern carefully and write Java code to create it exactly as shown. In each case, you should define parameters for the number of rows, or colums, etc, so the puzzle can be easily resized. Good luck!

---

## Example 1: Alternating Characters
```
*
$$
***
$$$$
*****
$$$$$$
*******
```

**Notes:** Rows alternate between `*` and `$` characters. Each row has one more character than its row number.

---

## Example 2: Hollow Square
```
*******
*     *
*     *
*     *
*     *
*     *
*******
```

**Notes:** The border is made of `*` characters, but the interior is spaces.

---

## Example 3: Three Diamonds
```
   *       *       *
  ***     ***     ***
 *****   *****   *****
******* ******* *******
 *****   *****   *****
  ***     ***     ***
   *       *       *
```
**Notes:** A classic diamond shape, but repeated

---

## Example 4: Hollow Diamond
```
   *
  * *
 *   *
*     *
 *   *
  * *
   *
```

**Notes:** Like the diamond, but only the outline is drawn.

---

## Example 5: Arrow Pattern
```
    *
   ***
  *****
 *******
*********
    *
    *
    *
    *
```

**Notes:** An arrowhead pointing up, followed by a straight shaft. You should have parameters: `arrow_size`, `tail_size` and optionally `tail_width`

---

## Example 6: Cross Pattern
```
   ***
   ***
   ***
*********
*********
*********
   ***
   ***
   ***
```

**Notes:** A plus sign or cross shape with thick lines.

---

## Bonus Challenge: Checkerboard
```
* * * *
 * * *
* * * *
 * * *
* * * *
 * * *
* * * *
```

**Notes:** Alternating pattern like a checkerboard. Even rows start with `*`, odd rows start with space.

---

## Getting Stuck?

**For hollow shapes:** Think about when to print a `*` vs. when to print a space:
- Usually you print `*` on the edges (first/last row, first/last column)
- You print spaces everywhere else

**For alternating patterns:** Consider using the modulo operator (`%`) to check if a row number is even or odd.

**For diamonds:** Break them down into two parts - the top half and the bottom half often have similar but slightly different logic.
