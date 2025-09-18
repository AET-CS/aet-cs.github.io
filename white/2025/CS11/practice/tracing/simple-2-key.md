# AP Computer Science - Easy Starter Questions (Version 2)
## ANSWER KEY

## Question 1
**Answer:** `6 8`

**Explanation:**
* a = 8, b = 2
* a = 8 - 2 = 6
* b = 6 + 2 = 8

## Question 2
**Answer:** `20`

**Explanation:**
* Loop adds even numbers: 2, 4, 6, 8
* sum = 2 + 4 + 6 + 8 = 20

## Question 3
**Answer:** `12`

**Explanation:**
* 4 < 5 is true, so if branch executes
* x = 4 * 3 = 12

## Question 4
**Answer:** `3 1`

**Explanation:**
* Loop divides n by 2 (integer division):
* n: 15 → 7 → 3 → 1
* Loop runs 3 times, final n = 1

## Question 5
**Answer:** `18`

**Explanation:**
* Sum of multiples of 3 from 1 to 10
* Multiples of 3: 3, 6, 9
* sum = 3 + 6 + 9 = 18

## Question 6
**Answer:** `27 3`

**Explanation:**
* x triples: 1 → 3 → 9 → 27
* 27 * 3 = 81 > 30, so loop stops
* Loop runs 3 times (count = 3)

## Question 7
**Answer:** `24`

**Explanation:**
* 12 > 10 is true, so enters first if
* 7 > 10 is false, so else branch executes
* result = 12 * 2 = 24

## Question 8
**Answer:** `16 11`

**Explanation:**
* x doubles, y decreases by 3:
* x: 2 → 4 → 8 → 16
* y: 20 → 17 → 14 → 11
* Loop stops when x >= y (16 >= 11)

## Question 9
**Answer:** `7 128`

**Explanation:**
* product doubles: 1 → 2 → 4 → 8 → 16 → 32 → 64 → 128
* 128 >= 100, so loop stops
* Loop runs 7 times, final product = 128

## Question 10
**Answer:** `17`

**Explanation:**
* Pattern: next = x + 2*y, then shift values
* i=0: next = 1 + 2(1) = 3, x = 1, y = 3
* i=1: next = 1 + 2(3) = 7, x = 3, y = 7
* i=2: next = 3 + 2(7) = 17, x = 7, y = 17
* i=3: next = 7 + 2(17) = 41, x = 17, y = 41
* Final x = 17