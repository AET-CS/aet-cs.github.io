---
title: "Fixed Points"
layout: single
classes:
  - wide
---

# Fixed Points

A **fixed point** of a function $f$ is a number $x^*$ where

$$
f(x^*) = x^*.
$$

In Challenge 5 of the RPN Worksheet, you iterated `1 cos cos cos cos ...` and watched the value settle down. That settling value is a fixed point of $\cos$: the number where $\cos(x) = x$.

## 1. Find the Fixed Points

Use a calculator (Desmos works well) to find the fixed point(s) of each function below. Graphing $y = f(x)$ and $y = x$ and looking for intersections is a good way to start.

1. $f(x) = \cos(x)$
2. $f(x) = \sqrt{1+x}$
3. $f(x) = x^2$
4. $f(x) = \sin(x)$
5. $f(x) = 3.2x(1-x)$

Note: problems 3 and 5 have more than one fixed point — find them all.

## 2. Attracting or Repelling?

Start near a fixed point and iterate $f$ over and over, the same way you did with `cos` in Challenge 5. Sometimes the values get pulled in closer to the fixed point every step — that's an **attracting** fixed point. Sometimes they get pushed farther away — that's a **repelling** fixed point.

For each function above, try a few different starting values near each fixed point and iterate. Does it move toward the fixed point or away from it? Experiment — try starting a little above, a little below, close in, and farther out. For the functions with more than one fixed point, test each one separately.

Once you've experimented, graph $y = f(x)$ and $y = x$ together and zoom in on each fixed point. See if you can find something in the picture — how the curve crosses the line — that explains why some fixed points attract and others repel.
