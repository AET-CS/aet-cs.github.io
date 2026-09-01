---
title: "Assignment: Finding and Classifying Fixed Points"
layout: single
classes:
  - wide
---

## Overview

A **fixed point** of a function `f` is a value `x` where `f(x) = x` — a point the function
leaves alone. In class we first found fixed points by iterating: start somewhere, apply `f` over and
over, and see where you land. Then we tried applying binary search to solve the equation $f(x)=x$. That
is the approach we will employ in this lab.

In this assignment you'll find fixed points with **binary search** and then
determine whether each fixed point is **attracting** (nearby points
drift toward it under iteration) or **repelling** (nearby points drift away).

The key idea connecting the two topics: `x` is a fixed point of `f` exactly when
`f(x) = x`. Given a search range between `lo` and `hi`, you will compute `mid` and
then update the search range if `f(mid) > mid` or `f(mid) < mid`. This is the same halving
logic you used to search a sorted array, applied to a
continuous interval instead of an index range.

You will write everything in a single file, `FixedPoint.java`.

---

## Functions to write

Write these four methods. Nothing here needs to be longer than about a dozen lines.

```java
static double f(double x)
```
The function you're studying. Start with `Math.cos(x)`. You will change this method (and only
this method) as you work through Part 3.

```java
static double findFixedPoint(double lo, double hi)
```
Binary search the interval `[lo, hi]` for a fixed point of `f` and returns it. Each pass,
compute the midpoint `mid`. Then you compare `mid` to `f(mid)` and update either `lo` or
`hi`. (Note you don't add `+1` or `-1` here because we're not searching an array). Use `TOLERANCE` to determine when the search should stop.

```java
static void testAttracting(double x)
```
Given a fixed point `x`, decides whether it is attracting or repelling and prints the result.
Described in Part 2.

```java
static void main(String[] args)
```
Calls `findFixedPoint`, prints the fixed point it found along with `f` of that value (so you
can see they match), then calls `testAttracting` on the result.

You'll also want a constant near the top of the class file. You can adjust its value:

```java
static final double TOLERANCE = 0.00001;
```
---

## Part 1 — Binary search for the fixed point

Write `findFixedPoint`. Loop while `Math.abs(f(guess) - guess) > TOLERANCE`, halving the
interval each time as described above.

Test it with `f(x) = Math.cos(x)` on the interval `[0, 1]`. Your output should show the guesses
converging, and the final answer should satisfy `f(x) = x` to nine decimal places.

---

## Part 2 — The attraction test

Add `testAttracting`. The logic is direct:

- Set `epsilon` to `0.01` and start from `nearby = x + epsilon` — a point just off the fixed
  point.
- Iterate: `nearby = f(nearby)`, ten times.
- Measure `Math.abs(nearby - x)`, the final distance from the fixed point.
- If that distance is **smaller** than `epsilon`, the point was pulled in — print that the
  fixed point is attracting. Otherwise print that it is repelling.

Run it on your `cos` fixed point. Then answer:

4. Ten iterations is a guess on my part. Is it enough? Try 3, and try 40. Does the verdict ever
   change? For which kinds of functions would you expect a small iteration count to give the
   wrong answer?
5. The test starts at `x + epsilon` only. Should it also check `x - epsilon`? Construct a
   function where it would matter, or argue convincingly that it can't.

---

## Part 3 — A catalog of fixed points

Each function below has exactly one fixed point in the interval given. For each one: change
`f`, run your program with that interval, and record the fixed point and its classification.

| #   | `f(x)`         | Java                | Interval `[lo, hi]` |
| --- | -------------- | ------------------- | ------------------- |
| 1   | cos x          | `Math.cos(x)`       | `[0, 1]`            |
| 2   | e<sup>−x</sup> | `Math.exp(-x)`      | `[0, 1]`            |
| 3   | 1 + 1/x        | `1 + 1/x`           | `[1, 2]`            |
| 4   | (x + 2/x) / 2  | `(x + 2/x) / 2`     | `[1, 2]`            |
| 5   | 2 sin x        | `2 * Math.sin(x)`   | `[1, 3]`            |
| 6   | 3.2x(1 − x)    | `3.2 * x * (1 - x)` | `[0.4, 1]`          |
| 7   | 4 − x²         | `4 - x * x`         | `[1, 2.5]`          |

Create a table with three columns: the function, the fixed point to nine decimal places, and
attracting or repelling.

1. Roughly how many steps does binary search take to converge? What exactly determines the number of steps needed?
2. What happens if you hand it an interval where `f(lo)` and `f(hi)` are *both*
   positive or *both* negative? Try it on one of the functions. Explain what the code does and why. (There is an optional extension to fix it in this case.)
3. Can you construct a function where binary search quickly finds the exact fixed point? Describe the behavior of the algorithm in this case.

4. At least one of these fixed points is a number you already know by another name. Which one(s)?

5. Sort your seven functions into the attracting group and the repelling group. Using a graphing tool, graph each
   function near its fixed point, together with the line `y = x`. What do the attracting cases
   have in common that the repelling cases don't? State your conjecture as precisely as you
   can.

---

## Extension — Cycles

A point of **orbit length 2** returns to itself after two applications of `f`, but not after
one. So it is a fixed point of `f(f(x))` that is *not* a fixed point of `f`. Same idea for
length 3, 4, and beyond.

Write

    static double fIterated(double x, int n)

which applies `f` to `x` a total of `n` times, and rework `findFixedPoint` to search
`fIterated(x, n)` instead of `f`. Then:

6. With `f(x) = 3.2x(1 - x)`, find both points of the 2-cycle. Verify they map to each other.
7. Change to `f(x) = 3.83x(1 - x)` and find a 3-cycle. There is more than one. Find them all.
8.  Some intervals that clearly contain a cycle point cause your search to fail or to return
    the wrong root. Work out exactly what property of the interval decides this, and add a
    check to `findFixedPoint` that reports the problem instead of returning nonsense.
9.  Extend `testAttracting` to classify a whole cycle. What does attracting mean for a cycle?
10.  Every fixed point of `f` is also a fixed point of `f(f(x))`. So how does a program that
    searches `f(f(x))` know it has found a genuine 2-cycle and not just an old fixed point
    wearing a disguise? Handle this.
11. Find your own function with an interesting cycle: a 4-cycle, a cycle that some points reach
    and others don't, or a function whose cycles change character as you nudge a constant in
    it. Report what you found and how you found it.
