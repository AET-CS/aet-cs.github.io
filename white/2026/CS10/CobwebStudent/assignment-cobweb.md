# Assignment: Cobweb Diagrams

## Overview

You already found fixed points numerically. Now you'll graph them. A **cobweb diagram** shows
what iteration does geometrically: from a starting point on the line `y = x`, draw a vertical
segment out to the curve `y = f(x)`, then a horizontal segment back to the line `y = x`, and
repeat. Each pair of segments is one application of `f`. Attracting fixed points show up as a
spiral or staircase winding inward; repelling ones wind outward.

You will not write any drawing code, and you will not write any interface code. A class called
`Cobweb` owns the window, the menu, the slider, the panning and the zooming. It draws only what
you hand it, and what you hand it is **arrays**. The picture is a rendering of one array — your
orbit — so if the picture is wrong, the array is wrong.

Three files:

- `Cobweb.java` — provided. Do not modify it.
- `Function.java` — provided. The Function class; you will extend it.
- `Explorer.java` — your code goes here.

---

## Using the program

The **Function menu** picks which function to study. The **r slider** at the bottom changes a
function's parameter, live, and is greyed out for functions that don't have one. **Click
anywhere on the picture to start an orbit from that x** — only the x-coordinate is used, and a
dot appears on the diagonal at `(x0, x0)`. **Drag to pan**, **`+` / `-` to zoom**, **`R` to
reset the view.** Nothing can take you outside `[-5, 5]`, and `R` always brings you home.

The **Orbit menu** sets how many steps each orbit runs for. Fifty is plenty to watch a spiral
settle; you will want several hundred to see what a long-running orbit really does.

For functions with a parameter, the slider is the quick way to explore and the **left and right
arrow keys** are the precise way: each press moves `r` by exactly 0.001. You can also type an
exact value into the box beside the slider and press Enter. Every window opens on `[-2, 2]`, so
expect to zoom for functions whose interesting behavior is packed into a narrow strip.

## Program Outline

`Cobweb` never calls your function. Instead your `main` is a loop that waits for the user to do
something and then hands over fresh arrays:

```java
while (true) {
    String what = Cobweb.nextEvent();
    ...build arrays...
    Cobweb.drawCurve(xs, ys);
}
```
Your job is to write the array methods needed (and discussed already in class). You will also create a library of functions to graph.

## Overview of code to write

**The catalog.** Each function is a small class extending `Function`:

```java
    public static class Cos extends Function {
        public Cos() {
            super("cos x");
        }

        public double apply(double x, double r) {
            return Math.cos(x);
        }
    }
```

The constructor takes a display name and then you define the `apply` method based on the function you want to compute

Some functions can take parameters

```java
    public static class Logistic extends Function {
        public Logistic() {
            super("r x (1 - x)", 0.5, 4.0, 3.2);
        }

        public double apply(double x, double r) {
            return r * x * (1 - x);
        }
    }
```

You should add all the functions from the class worksheet to your program. Feel free to add others you want to explore, once your code
is working.

You will also write the following functions (you will find them started in Explorer.java)

**`sampleX(lo, hi, n)`** — return a double array of `n` evenly spaced x-values, `lo` first and `hi` last.
**`applyF(xs, f, r)`** — create and return a new array `f.apply(x, r)` for every entry, leaving `xs` alone.
**`orbit(x0, steps, f, r)`** — `{x0, f(x0), f(f(x0)), ...}` with `steps + 1` entries. The entire cobweb is drawn from this array and nothing else.
**`min(a)`, `max(a)`** — return smallest and largest entries in each array. Assume the array is non-empty.
`main` uses these to print the range an orbit stays inside, which is often more revealing than the picture:
an orbit that has settled onto a 2-cycle reports the two cycle values as its minimum and maximum.

---

## To Do

1. Insert Location 1 -- define at least 3 additional functions, using Cos and Logistic as guides. One of your functions should be 'Logistic'
2. Insert Location 2 -- add the new functions to this array, after Cos
3. Insert Location 3 -- write the five array methods described above
4. Test your code thoroughly with each of the functions from class
5. Using your code, explore the logistic equation. Look for $r$ values that have interesting behavior.

The file compiles before you have written anything, so you can run it at any point to see how
far you have got. Until `sampleX` and `applyF` return real arrays you will get an empty pair of
axes and a note in the console saying so; that is expected, not a crash.