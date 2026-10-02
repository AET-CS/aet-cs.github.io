# Trace Handout Answer Key: Trace These

**How to use this key:** Fill in your own trace table first, then compare. If your final answer is wrong, look for the first row where your table and this one disagree. The narrative under each problem explains what is going on.

**A pattern to watch for:** Problems 7, 8 and 9 all share the same bug. After a `remove`, the next element slides into the index you just looked at, but the loop moves on anyway, so that element is never checked. Once you spot it in one problem, you can spot it in the others.

---

## Problem 4

**Output:**
```
Alex Bob Carl 
Alex Alex Alex 
```

| `k` | `students` before | returned by `set` | `students` after |
|---|---|---|---|
| 0 | `[Alex, Bob, Carl]` | Alex | `[Alex, Bob, Carl]` |
| 1 | `[Alex, Bob, Carl]` | Bob | `[Alex, Alex, Carl]` |
| 2 | `[Alex, Alex, Carl]` | Carl | `[Alex, Alex, Alex]` |

**What's going on:** `set` replaces an element **and returns the old one**. In the first loop, the thing being printed is the *return value* of `set`, which is the old name. So the loop prints the original names (Alex Bob Carl) while overwriting each one with "Alex" behind the scenes. By the second loop, every element is "Alex", which is what the for-each loop prints. Nothing throws an exception, since `k` always stays within the list.

---

## Problem 7

**Result:** `numList` ends as `[2, 20, 16]`, and the method returns `[5, 10]`. The intended results were `[2, 16]` and `[5, 10, 20]`.

| `i` | size | `num` | divisible by 5? | `numList` after | `returnList` after |
|---|---|---|---|---|---|
| 0 | 5 | 5 | yes | `[2, 10, 20, 16]` | `[5]` |
| 1 | 4 | 10 | yes | `[2, 20, 16]` | `[5, 10]` |
| 2 | 3 | 16 | no | `[2, 20, 16]` | `[5, 10]` |

After this row `i` becomes 3, which is not less than 3, so the loop ends.

**What's going on:** look at `i = 1`. The 10 was removed, so the 20 slid down into index 1. But the loop then did `i++`, moved to index 2, and never looked at the 20. That's the bug: **removing an element shifts the rest left, and the loop skips over whatever slid into the spot you just cleared.**

You may notice the same thing happened at `i = 0`: the 2 slid into index 0 and was skipped. It didn't matter, because 2 isn't divisible by 5. The method only fails when two matching values sit next to each other. That's why the problem says it "does not *always* work as intended."

---

## Problem 8

**Result:** `values` ends as `[0, 4, 2, 5, 3]`.

| `k` | size | `nums.get(k)` | removed? | `nums` after |
|---|---|---|---|---|
| 0 | 8 | 0 | yes | `[0, 4, 2, 5, 0, 3, 0]` |
| 1 | 7 | 4 | no | `[0, 4, 2, 5, 0, 3, 0]` |
| 2 | 7 | 2 | no | `[0, 4, 2, 5, 0, 3, 0]` |
| 3 | 7 | 5 | no | `[0, 4, 2, 5, 0, 3, 0]` |
| 4 | 7 | 0 | yes | `[0, 4, 2, 5, 3, 0]` |
| 5 | 6 | 0 | yes | `[0, 4, 2, 5, 3]` |

After this row `k` becomes 6, which is not less than 5, so the loop ends.

**What's going on:** the list started with four zeros, and the method removed three of them. The one that survived is the one at the front. At `k = 0` the first 0 was removed, and the *second* 0 slid down into index 0. Then `k++` moved on to index 1, so that second 0 was never examined. The same skipping shows up again at `k = 4`, though that time the element that slid down was a 3, which is not zero, so nothing was lost.

Two results to take away: the method does not do what a person reading it would expect, and no exception is thrown. The loop re-checks `nums.size()` every pass, so `k` never goes out of bounds.

---

## Problem 9

**Result:** `myData` ends as `[3, 4, 4, 8, 7, 7]`, not the intended `[3, 4, 8, 7]`.

| `k` | `get(k)` | `get(k - 1)` | equal? | `myData` after |
|---|---|---|---|---|
| 1 | 3 | 3 | yes | `[3, 4, 4, 4, 8, 7, 7, 7]` |
| 2 | 4 | 4 | yes | `[3, 4, 4, 8, 7, 7, 7]` |
| 3 | 8 | 4 | no | `[3, 4, 4, 8, 7, 7, 7]` |
| 4 | 7 | 8 | no | `[3, 4, 4, 8, 7, 7, 7]` |
| 5 | 7 | 7 | yes | `[3, 4, 4, 8, 7, 7]` |

After this row `k` becomes 6, which is not less than 6, so the loop ends.

**What's going on:** the same skipping bug again. After `remove(k)`, the next element slides into index `k`, but `k++` moves past it, so it is never compared to its neighbor. The run of three 4s shows this clearly: the first duplicate 4 was removed at `k = 2`, but then the loop jumped to `k = 3` and never compared the remaining 4 against the one before it.

**The fix:** put an `else` before `k++`. Then `k` only moves forward when nothing was removed, so the element that slid into place gets its turn. Here is the fixed version run on the same list:

| `k` | comparison | result | `myData` after |
|---|---|---|---|
| 1 | 3 vs 3, equal | remove, `k` stays 1 | `[3, 4, 4, 4, 8, 7, 7, 7]` |
| 1 | 4 vs 3, not equal | `k` becomes 2 | same |
| 2 | 4 vs 4, equal | remove, `k` stays 2 | `[3, 4, 4, 8, 7, 7, 7]` |
| 2 | 4 vs 4, equal | remove, `k` stays 2 | `[3, 4, 8, 7, 7, 7]` |
| 2 | 8 vs 4, not equal | `k` becomes 3 | same |
| 3 | 7 vs 8, not equal | `k` becomes 4 | same |
| 4 | 7 vs 7, equal | remove, `k` stays 4 | `[3, 4, 8, 7, 7]` |
| 4 | 7 vs 7, equal | remove, `k` stays 4 | `[3, 4, 8, 7]` |

Now `k` is 4 and the size is 4, so the loop ends with `[3, 4, 8, 7]`, which is exactly what we wanted.

---

## Problem 11

**Output:** `[200, 400]`. The code compiles and runs. (You may see a warning about raw types, but it isn't an error.)

| After this statement | `oldList` | `newList` |
|---|---|---|
| `oldList.add(400)` | `[100, 200, 300, 400]` | `[]` |
| `newList.add(oldList.remove(1))` | `[100, 300, 400]` | `[200]` |
| `newList.add(oldList.get(2))` | `[100, 300, 400]` | `[200, 400]` |

**What's going on:** the trick is the `remove(1)` inside the `add`. It does two things: it takes the 200 out of `oldList` and **returns it**, so that same 200 is then added to `newList`. Because `remove` shifts the rest of `oldList` left, the list is now `[100, 300, 400]`, and index 2 holds **400**, not 300. That's the most common wrong answer: people use the original list when they should use the list as it is after the removal.
