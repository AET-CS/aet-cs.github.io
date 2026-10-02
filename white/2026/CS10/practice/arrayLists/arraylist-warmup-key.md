# Warm-Up Answer Key: What's in the List?

**How to use this key:** Try each problem on your own first. Then check your answer and, if it's different, find the first line where your list and the table disagree. That is almost always where the mistake is.

**Three habits that make these problems easy:**
1. **Work one statement at a time** and write down the whole list after each one.
2. **Evaluate the inside first.** When one call is inside another, like `list.set(0, list.remove(3))`, the inner call runs completely (and changes the list) before the outer call starts.
3. **Remember what each method returns.** `set` returns the *old* value, `remove` returns the *removed* value, and `get` returns a value without changing anything. `add(index, value)` returns nothing.

---

## Part 1: No Loops

### Problem 1

**Answer: `[4, 10, 19, 20, 4]`**

| Statement | What happens | List after |
|---|---|---|
| three `add` calls | 4, 8, 15 are appended | `[4, 8, 15]` |
| `nums.set(1, nums.get(0) + nums.get(2))` | `get(0)` is 4, `get(2)` is 15, sum is 19. Replace the 8 with 19. | `[4, 19, 15]` |
| `nums.add(1, nums.get(2) - 5)` | `get(2)` is 15, so insert 10 at index 1. The 19 and 15 slide right. | `[4, 10, 19, 15]` |
| `nums.set(3, nums.get(1) * 2)` | `get(1)` is 10, so replace the 15 at index 3 with 20. | `[4, 10, 19, 20]` |
| `nums.add(nums.get(0))` | `get(0)` is 4. Append another 4. | `[4, 10, 19, 20, 4]` |

**The idea:** every `get` looks at the list *as it is at that moment*. For instance, in the third statement `get(2)` is 15 because the insert hasn't happened yet.

### Problem 2

**Answer: `words` is `[yellow, blue, green, purple, blue]`, and `old` is `"red"`.**

| Statement | What happens | List after |
|---|---|---|
| three `add` calls | | `[red, green, blue]` |
| `String old = words.set(0, "yellow");` | `set` replaces "red" and **returns** "red", so `old` is "red". | `[yellow, green, blue]` |
| `words.add(old);` | Append "red". | `[yellow, green, blue, red]` |
| `words.set(3, words.set(2, "purple"));` | **Inner first:** `set(2, "purple")` replaces "blue" and returns "blue". **Then outer:** `set(3, "blue")` replaces "red" with "blue". | after inner: `[yellow, green, purple, red]`; after outer: `[yellow, green, purple, blue]` |
| `words.add(1, words.get(3));` | `get(3)` is "blue". Insert it at index 1. | `[yellow, blue, green, purple, blue]` |

**The idea:** the inner `set` did two jobs. It changed the list *and* handed its old value ("blue") to the outer `set`. The outer `set` also returned a value ("red"), but nobody saved it, so it's gone.

### Problem 3

**Answer: `list` is `[20, 15, 10, 25, 5]`, and `a` is `15`.**

| Statement | What happens | List after |
|---|---|---|
| five `add` calls | | `[5, 10, 15, 20, 25]` |
| `int a = list.remove(2);` | Remove the 15 at index 2. `remove` returns 15, so `a` is 15. Everything after it slides left. | `[5, 10, 20, 25]` |
| `list.add(1, a);` | Insert 15 at index 1. | `[5, 15, 10, 20, 25]` |
| `list.set(0, list.remove(3));` | **Inner first:** `remove(3)` removes the 20 and returns it. **Then outer:** `set(0, 20)` replaces the 5. | after inner: `[5, 15, 10, 25]`; after outer: `[20, 15, 10, 25]` |
| `list.add(list.get(1) - list.get(2));` | `get(1)` is 15 and `get(2)` is 10, so append 5. | `[20, 15, 10, 25, 5]` |

**The idea:** `remove` gives you the value back, so you can store it (`int a = ...`) or pass it straight to another method. The 5 that `set` replaced was thrown away.

### Problem 4

**Answer: `items` is `[E, D, C, B]`, `x` is `"B"`, and `y` is `"D"`.**

| Statement | What happens | List after |
|---|---|---|
| five `add` calls | | `[A, B, C, D, E]` |
| `String x = items.remove(1);` | Remove "B" and save it in `x`. | `[A, C, D, E]` |
| `String y = items.set(2, x);` | Index 2 holds "D". Replace it with "B". `set` returns the old value, so `y` is "D". | `[A, C, B, E]` |
| `items.add(1, y);` | Insert "D" at index 1. | `[A, D, C, B, E]` |
| `items.set(0, items.remove(items.size() - 1));` | **Inner first:** the size is 5, so this is `remove(4)`. It removes "E" and returns it. **Then outer:** `set(0, "E")` replaces "A". | after inner: `[A, D, C, B]`; after outer: `[E, D, C, B]` |

**The idea:** "A" is gone. `set` returned it, but the return value wasn't stored anywhere. If you need an old value later, save it in a variable like we did with `x` and `y`.

### Problem 5

**Answer: `[2, 10, 6, 16]`**

| Statement | What happens | List after |
|---|---|---|
| six `add` calls | | `[2, 4, 6, 8, 10, 12]` |
| `data.set(1, data.remove(4));` | **Inner:** `remove(4)` removes 10 and returns it. **Outer:** `set(1, 10)` replaces the 4. | after inner: `[2, 4, 6, 8, 12]`; after outer: `[2, 10, 6, 8, 12]` |
| `data.add(2, data.get(0) + data.remove(3));` | Java works **left to right**. `get(0)` is 2. Then `remove(3)` removes 8 and returns it, giving `[2, 10, 6, 12]`. The sum is 2 + 8 = 10. Insert 10 at index 2. | `[2, 10, 10, 6, 12]` |
| `data.remove(data.get(0) - 1);` | `get(0)` is 2, and 2 - 1 is 1. This is `remove(1)`, which removes the first 10. | `[2, 10, 6, 12]` |
| `data.set(data.size() - 1, data.get(1) + data.get(2));` | The size is 4, so the index is 3. `get(1) + get(2)` is 10 + 6 = 16. Replace the 12 with 16. | `[2, 10, 6, 16]` |

**The idea:** two things to watch. (1) In the second statement, the list changes in the middle of the expression, so the order of evaluation matters. (2) In the third statement, `data.get(0) - 1` is an `int` (an integer calculation), so Java treats it as an *index*, and `remove(1)` removes the element at position 1.

---

## Part 2: With Loops

### Problem 6

**Answer: `[3, 6, 9]`**

| `i` | `i % 3 == 0`? | `multiples` after |
|---|---|---|
| 1 | no | `[]` |
| 2 | no | `[]` |
| 3 | yes | `[3]` |
| 4 | no | `[3]` |
| 5 | no | `[3]` |
| 6 | yes | `[3, 6]` |
| 7 | no | `[3, 6]` |
| 8 | no | `[3, 6]` |
| 9 | yes | `[3, 6, 9]` |
| 10 | no | `[3, 6, 9]` |

**The idea:** the `if` decides which values get added. Only the multiples of 3 make it into the list.

### Problems 7-10

Each problem starts with `nums` = `[2, 4, 6, 8, 10, 12]`.

### Problem 7

**Answer: `[2, 1, 0, 2, 4, 6, 8, 10, 12]`**

| `i` | Statement | `nums` after |
|---|---|---|
| (start) | | `[2, 4, 6, 8, 10, 12]` |
| 0 | `nums.add(0, 0)` | `[0, 2, 4, 6, 8, 10, 12]` |
| 1 | `nums.add(0, 1)` | `[1, 0, 2, 4, 6, 8, 10, 12]` |
| 2 | `nums.add(0, 2)` | `[2, 1, 0, 2, 4, 6, 8, 10, 12]` |

**The idea:** each value is inserted at the *front*, so the newest one always ends up first. That is why 0, 1, 2 appear in reverse. The loop condition is `i < 3`, a fixed number, so the list growing doesn't affect how many times the loop runs.

### Problem 8

**Answer: `[8, 10, 12, 3, 5, 7]`**

| `i` | `first = nums.remove(0)` | list after the remove | `nums.add(first + 1)` | `nums` after |
|---|---|---|---|---|
| 0 | 2 | `[4, 6, 8, 10, 12]` | add 3 | `[4, 6, 8, 10, 12, 3]` |
| 1 | 4 | `[6, 8, 10, 12, 3]` | add 5 | `[6, 8, 10, 12, 3, 5]` |
| 2 | 6 | `[8, 10, 12, 3, 5]` | add 7 | `[8, 10, 12, 3, 5, 7]` |

**The idea:** each pass takes the front element off, adds one to it, and puts it on the back. It's like rotating the list, except every element that moves gets bumped up by one. The size stays at 6 the whole time, because one value comes out and one goes in.

### Problem 9

**Answer: `[2, 4]`**

This loop goes **backward**, from the last index down to 0.

| `i` | `nums.get(i)` | greater than 5? | `nums` after |
|---|---|---|---|
| 5 | 12 | yes, `remove(5)` | `[2, 4, 6, 8, 10]` |
| 4 | 10 | yes, `remove(4)` | `[2, 4, 6, 8]` |
| 3 | 8 | yes, `remove(3)` | `[2, 4, 6]` |
| 2 | 6 | yes, `remove(2)` | `[2, 4]` |
| 1 | 4 | no | `[2, 4]` |
| 0 | 2 | no | `[2, 4]` |

**The idea:** when you remove an element, everything *after* it slides left. Going backward, the elements after it are ones you've already checked. So nothing gets skipped.

### Problem 10

**Answer: `[2, 4, 8, 12]`**

This is the same condition, but the loop goes **forward**.

| `i` | `nums.size()` | `nums.get(i)` | greater than 5? | `nums` after |
|---|---|---|---|---|
| 0 | 6 | 2 | no | `[2, 4, 6, 8, 10, 12]` |
| 1 | 6 | 4 | no | `[2, 4, 6, 8, 10, 12]` |
| 2 | 6 | 6 | yes, `remove(2)` | `[2, 4, 8, 10, 12]` |
| 3 | 5 | 10 | yes, `remove(3)` | `[2, 4, 8, 12]` |
| 4 | 4 | | `4 < 4` is false, so the loop stops | `[2, 4, 8, 12]` |

**The idea:** look at what happened at `i = 2`. After the 6 was removed, the 8 slid left into index 2. Then `i++` moved on to index 3, so **the 8 was never checked**. The same thing happened to the 12 after the 10 was removed. Both values are bigger than 5, and both survived.

**Compare to Problem 9:** the same code gave `[2, 4]` when it went backward and `[2, 4, 8, 12]` when it went forward. When you remove inside a forward loop, you have to deal with the shifting. There are two common fixes: loop backward, or only add 1 to `i` when you did *not* remove something.
