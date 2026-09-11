# Explorations: The Logistic Map

Select `r x (1 - x)` from the Function menu. Everything below is done by clicking a starting
point, changing `r`, and looking.

**Before you start, three habits that make this much easier:**

- Set the **Orbit menu** to 500 or more. A 50-step orbit is still settling down; it cannot show
  you what the long-run behavior is.
- Use the **arrow keys**, not the slider, once you are close. Each press moves `r` by exactly
  0.001. The slider is for getting into the neighborhood.
- Watch the **console line**, not just the picture. It prints the smallest and largest value the
  orbit reached. Two boxes in the picture can be hard to count; two numbers are not.
- Click a starting point somewhere in the middle, around `x = 0.5`. Starting very close to 0 or
  1 wastes most of your orbit on the journey in.

---

## Part A — Everyone does these

Each of these has a definite answer. Write down the `r` value you found and what the picture
looked like.

**A1.** Set `r = 2.8` and click. Describe the picture. How many values does the orbit end up
visiting?

**A2.** Raise `r` slowly. Find the value where the single fixed point stops attracting and the
orbit starts bouncing between **two** values. Get it to two decimal places. Describe what the
picture looks like just below and just above that value.

**A3.** Keep going. Find where the two values become **four**. Two decimal places again.

**A4.** Keep going. Find where four becomes **eight**. This one is harder to see — say what made
it hard, and how you convinced yourself.

**A5.** Set `r = 3.9`. Describe the picture. Does the orbit ever settle down? Try a few
different starting points at this `r` and compare.

**A6.** Somewhere near `r = 3.83` there is a **3-cycle** — the orbit visits exactly three
values. Find it. This one is worth the hunt: it is surprising that a clean short cycle appears
in the middle of all that mess.

**A7.** Once you have the 3-cycle, nudge `r` up by a few thousandths. The three values become
**six**. Find where.

---

## Part B — Harder, but still with right answers

**B1.** Go back and measure your four doubling points (1→2, 2→4, 4→8, and 8→16 if you can stand
it) as precisely as you can. Then compute the gaps between consecutive ones, and then the ratio
of each gap to the next. What do you notice about those ratios? Say what you would predict the
next one to be.

**B2.** Using B1, estimate the `r` value where the doubling stops keeping up — where the cycles
have doubled so many times in such a small interval that the pattern runs out. What happens to
the picture just past there?

**B3.** There is a **5-cycle** somewhere between 3.73 and 3.75. Find it. It is narrow; you will
need the arrow keys.

**B4.** There is another **6-cycle** near 3.63, well below the 3-cycle. So six shows up twice,
in two different ways. Look at both and describe how they differ — not just the `r` value, but
what the orbit does.

**B5.** Set `r = 3.9` and click two starting points as close together as you can manage. Run
both for 500 steps. Do they stay close? Now do the same at `r = 3.2`. Explain the difference.

**B6.** The console prints the orbit's smallest and largest value over the *whole* orbit,
including the settling-in at the start. For a long orbit at a cycle, that is fine. For a short
one it is misleading. Modify your code to ignore the first half of the orbit before computing
min and max, and explain why that gives a better answer.

**B7.** Write a method that takes an orbit and returns how many distinct values it ends on —
the cycle length — by comparing later entries to each other within a small tolerance. Use it to
check your answers to A2 through A7 without counting boxes by eye.

---

## Part C — Open-ended

No single right answer. Pick one or two.

**C1.** Make a table of `r` against cycle length, stepping `r` by 0.005 from 2.5 to 4.0, using
your method from B7. Print it. Where are the cycles, and where is the chaos? Does the table
match what you saw by hand?

**C2.** From C1, draw the picture: for each `r`, plot the values the orbit settles onto. This is
the bifurcation diagram, and it is one of the more famous pictures in mathematics. You have
already written everything you need to make it.

**C3.** Sort the cycle lengths you found by the `r` at which they appear. Cycles of length 2, 4,
8, 3, 5, 6, 7... is there an order to which ones show up when? (This one has a real and
startling answer, discovered in 1964. Do not look it up before you have made a guess.)

**C4.** Do the same sweep for `r - x^2`. Does it behave like the logistic map? If it does, say
in what sense — the numbers are not the same, so what is?

**C5.** Pick a function of your own with a parameter, and find out whether it has a cascade too.
Report what you tried, including anything that turned out to be boring.

**C6.** At `r = 4` exactly, the orbit fills the whole interval. Almost. Find a starting point
whose orbit does *not* wander everywhere, and explain why it is special.

---

## Notes for checking your work

Your measured values should be close to these. If you are off by more than about 0.01, look
again.

| What | Where |
|------|-------|
| fixed point stops attracting | 3.000 |
| 2-cycle becomes 4 | 3.449 |
| 4-cycle becomes 8 | 3.544 |
| 8-cycle becomes 16 | 3.564 |
| doubling runs out | 3.570 |
| 6-cycle window | 3.627 to 3.631 |
| 5-cycle window | 3.739 to 3.742 |
| 3-cycle window | 3.829 to 3.842 |
| 3-cycle doubles to 6 | 3.842 to 3.848 |
