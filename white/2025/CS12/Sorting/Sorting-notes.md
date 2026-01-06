---
title: Sorting
---
# Elementary Sorting Algorithms

## Keeping a cursor

When thinking about sorting algorithms, it's helpful to imagine a **cursor** that divides the array into left and right regions with different guarantees. Each algorithm maintains specific **invariants**—properties that remain true throughout execution—and these invariants determine how the algorithm progresses toward a fully sorted array.

## Selection Sort: Building a Sorted Prefix

**The Big Picture:** Selection sort maintains a clear boundary between "done" and "not done yet." Everything before the cursor is sorted and in its final position; everything after the cursor is still waiting to be processed.

**The Invariant:** At each step, the array consists of two regions:
- **Left of cursor:** A sorted sequence containing the smallest elements seen so far, each in its final position
- **Right of cursor:** An unsorted collection of remaining elements

**The Strategy:** On each iteration, selection sort scans the entire unsorted region to find the minimum element, then swaps it into the next position of the sorted region. The cursor advances, growing the sorted prefix by one element.

**Key Insight:** Selection sort is *selective* about what comes next—it always chooses the globally smallest remaining element. This makes it stable in terms of comparisons (always performs the same number regardless of input), but it doesn't take advantage of any existing order in the data.

## Insertion Sort: Maintaining a Sorted Prefix

**The Big Picture:** Like selection sort, insertion sort grows a sorted region from left to right. However, the elements in the sorted region may not be in their final positions—they're simply sorted *relative to each other*.

**The Invariant:** At each step:
- **Left of cursor:** A sorted sequence (but elements may shift further left later)
- **Right of cursor:** Elements not yet examined

**The Strategy:** Insertion sort takes the next unexamined element and "inserts" it into the correct position within the sorted region by shifting elements rightward as needed. Think of it like sorting playing cards in your hand—you pick up each new card and slide it backward into its proper place.

**Key Insight:** Insertion sort is adaptive—it performs minimal work on nearly-sorted data. If the array is already sorted, the algorithm makes only one comparison per element. The "bubbling backward" motion only continues until the element finds its proper place among those already examined.

## Bubble Sort: Bubbling Extremes to the Edges

**The Big Picture:** Bubble sort doesn't maintain an explicit cursor position. Instead, it repeatedly passes through the entire array, letting large values "bubble" toward the right end.

**The Invariant:** After each complete pass:
- The largest unsorted element has moved to its final position
- The rightmost portion of the array contains the largest elements in sorted order

**The Strategy:** Each pass performs adjacent comparisons across the array, swapping elements that are out of order. Large values gradually migrate rightward through successive swaps, like bubbles rising to the surface. The algorithm terminates when a complete pass occurs with no swaps, indicating the array is sorted.

**Key Insight:** Bubble sort is the most "local" in its comparisons—it only ever compares adjacent elements. While this makes it conceptually simple and also adaptive (efficient on nearly-sorted data), it can require many passes to move elements across large distances.

## Comparing the Three

**Selection Sort** is methodical: it always finds the right element for the next position, making progress guaranteed but inflexible.

**Insertion Sort** is accommodating: it maintains a sorted region and efficiently incorporates each new element, working especially well when data arrives in nearly-sorted order.

**Bubble Sort** is gradual: elements drift toward their final positions through local exchanges, with extreme values reaching the boundaries first.

All three algorithms share quadratic time complexity in the average case, but their different invariants and movement patterns make them suitable for different contexts and reveal different aspects of what it means to "sort" a collection.

## Examples

### Selection Sort

Let's trace through selection sort on the array `[64, 25, 12, 22, 11]`.

We'll use a visual representation where the cursor position is marked with `^` and we'll highlight the minimum element found in each pass.

---

**Initial array:**
```
[64, 25, 12, 22, 11]
 ^
```
The cursor starts at position 0. Everything left of the cursor (nothing yet) is sorted and final.

---

**Pass 1:** Find the minimum in the unsorted region (indices 0-4)
```
[64, 25, 12, 22, 11]
 ^
```
- Scan from cursor to end: 64, 25, 12, 22, 11
- Minimum found: **11** at index 4
- Swap with cursor position: swap 64 ↔ 11

**After swap:**
```
[11, 25, 12, 22, 64]
 ^
```
The cursor advances. Now **11** is in its final position.

---

**Pass 2:** Find the minimum in the unsorted region (indices 1-4)
```
[11, 25, 12, 22, 64]
     ^
```
- Scan from cursor to end: 25, 12, 22, 64
- Minimum found: **12** at index 2
- Swap with cursor position: swap 25 ↔ 12

**After swap:**
```
[11, 12, 25, 22, 64]
     ^
```
Now **11, 12** are in their final positions.

---

**Pass 3:** Find the minimum in the unsorted region (indices 2-4)
```
[11, 12, 25, 22, 64]
         ^
```
- Scan from cursor to end: 25, 22, 64
- Minimum found: **22** at index 3
- Swap with cursor position: swap 25 ↔ 22

**After swap:**
```
[11, 12, 22, 25, 64]
         ^
```
Now **11, 12, 22** are in their final positions.

---

**Pass 4:** Find the minimum in the unsorted region (indices 3-4)
```
[11, 12, 22, 25, 64]
             ^
```
- Scan from cursor to end: 25, 64
- Minimum found: **25** at index 3 (already at cursor!)
- No swap needed (or swap 25 ↔ 25)

**After "swap":**
```
[11, 12, 22, 25, 64]
             ^
```
Now **11, 12, 22, 25** are in their final positions.

---

**Pass 5:** Only one element remains
```
[11, 12, 22, 25, 64]
                 ^
```
When the cursor reaches the last element, the array must be sorted. **Done!**

---

#### Key Observations

1. **Exactly n-1 passes:** With 5 elements, we made 4 passes (the 5th element is automatically in place)

2. **Sorted prefix grows by one each time:** After pass k, the first k elements are sorted and will never move again

3. **Always n-1 comparisons per pass:** Even when the minimum is already at the cursor position (like in Pass 4), selection sort still scans the entire remaining unsorted region

4. **Predictable performance:** Selection sort performs the same number of comparisons regardless of the initial order of the array—it doesn't benefit from partially sorted data


## Insertion Sort Example

Let's trace through insertion sort on the same array `[64, 25, 12, 22, 11]`.

We'll use `^` to mark the cursor (the element being inserted) and show the "bubbling backward" process.

---

**Initial array:**
```
[64, 25, 12, 22, 11]
 ^
```
The cursor starts at position 0. The first element forms a trivially sorted region of size 1.

---

**Pass 1:** Insert 25 into the sorted region
```
[64, 25, 12, 22, 11]
     ^
```
- Take 25 and compare with elements to its left
- Is 64 > 25? Yes → swap them

**After bubbling:**
```
[25, 64, 12, 22, 11]
```
Now **25, 64** form a sorted region (relative to each other).

---

**Pass 2:** Insert 12 into the sorted region
```
[25, 64, 12, 22, 11]
         ^
```
- Take 12 and compare backward
- Is 64 > 12? Yes → swap them

```
[25, 12, 64, 22, 11]
     ^
```
- Is 25 > 12? Yes → swap them

**After bubbling:**
```
[12, 25, 64, 22, 11]
```
Now **12, 25, 64** form a sorted region.

---

**Pass 3:** Insert 22 into the sorted region
```
[12, 25, 64, 22, 11]
             ^
```
- Take 22 and compare backward
- Is 64 > 22? Yes → swap them

```
[12, 25, 22, 64, 11]
         ^
```
- Is 25 > 22? Yes → swap them

```
[12, 22, 25, 64, 11]
     ^
```
- Is 12 > 22? No → **stop bubbling**

**After bubbling:**
```
[12, 22, 25, 64, 11]
```
Now **12, 22, 25, 64** form a sorted region.

---

**Pass 4:** Insert 11 into the sorted region
```
[12, 22, 25, 64, 11]
                 ^
```
- Take 11 and compare backward
- Is 64 > 11? Yes → swap them

```
[12, 22, 25, 11, 64]
             ^
```
- Is 25 > 11? Yes → swap them

```
[12, 22, 11, 25, 64]
         ^
```
- Is 22 > 11? Yes → swap them

```
[12, 11, 22, 25, 64]
     ^
```
- Is 12 > 11? Yes → swap them

**After bubbling:**
```
[11, 12, 22, 25, 64]
```
The entire array is now sorted. **Done!**

---

### Key Observations

1. **Variable work per pass:** Pass 1 required 1 comparison, Pass 2 required 2, Pass 3 required 2 (stopped early!), Pass 4 required 4

2. **Early termination:** In Pass 3, we stopped bubbling 22 backward when we found it was greater than 12. We didn't need to check further left

3. **Best case advantage:** If the array were already sorted, each pass would make exactly 1 comparison and 0 swaps—total of only n-1 comparisons

4. **The sorted region grows:** After processing element at index k, everything from index 0 to k is sorted relative to each other (though elements might still shift in future passes)

5. **Comparison with selection sort:** Notice that in Pass 3, insertion sort did less work than selection sort would have—it stopped as soon as 22 found its place, rather than scanning to the end of the array


### Bubble Sort: A Worked Example

Let's trace through bubble sort on the same array `[64, 25, 12, 22, 11]`.

Bubble sort makes complete passes through the array, comparing adjacent elements and swapping them if they're out of order.

---

**Initial array:**
```
[64, 25, 12, 22, 11]
```

---

**Pass 1:** Scan through the entire array, comparing adjacent pairs

Compare positions 0-1: Is 64 > 25? Yes → swap
```
[25, 64, 12, 22, 11]
```

Compare positions 1-2: Is 64 > 12? Yes → swap
```
[25, 12, 64, 22, 11]
```

Compare positions 2-3: Is 64 > 22? Yes → swap
```
[25, 12, 22, 64, 11]
```

Compare positions 3-4: Is 64 > 11? Yes → swap
```
[25, 12, 22, 11, 64]
                 ^^
```

**After Pass 1:** The largest element (64) has "bubbled" to the end. At least one swap occurred, so we continue.

---

**Pass 2:** Scan through again (the last position is already final)

Compare positions 0-1: Is 25 > 12? Yes → swap
```
[12, 25, 22, 11, 64]
```

Compare positions 1-2: Is 25 > 22? Yes → swap
```
[12, 22, 25, 11, 64]
```

Compare positions 2-3: Is 25 > 11? Yes → swap
```
[12, 22, 11, 25, 64]
             ^^
```

**After Pass 2:** The second-largest element (25) has bubbled to its position. Swaps occurred, so we continue.

---

**Pass 3:** Scan through again

Compare positions 0-1: Is 12 > 22? No → no swap
```
[12, 22, 11, 25, 64]
```

Compare positions 1-2: Is 22 > 11? Yes → swap
```
[12, 11, 22, 25, 64]
         ^^
```

**After Pass 3:** The element 22 is now in position. Swaps occurred, so we continue.

---

**Pass 4:** Scan through again

Compare positions 0-1: Is 12 > 11? Yes → swap
```
[11, 12, 22, 25, 64]
 ^^
```

**After Pass 4:** The element 12 bubbled into position. Swaps occurred, so we continue.

---

**Pass 5:** Scan through again

Compare positions 0-1: Is 11 > 12? No → no swap
```
[11, 12, 22, 25, 64]
```

Compare positions 1-2: Is 12 > 22? No → no swap
```
[11, 12, 22, 25, 64]
```

Compare positions 2-3: Is 22 > 25? No → no swap
```
[11, 12, 22, 25, 64]
```

**After Pass 5:** No swaps occurred on this complete pass. The array is sorted. **Done!**

---

#### Key Observations

1. **Multiple complete passes:** Bubble sort required 5 passes (including the final verification pass with no swaps)

2. **Largest elements sink to the right first:** After each pass, the largest unsorted element has reached its final position (64 after pass 1, 25 after pass 2, etc.)

3. **Only adjacent comparisons:** Unlike selection sort (which scans ahead) and insertion sort (which can bubble backward multiple positions), bubble sort only ever compares elements right next to each other

4. **Early termination on sorted data:** If the array were already sorted, bubble sort would make one complete pass with no swaps and terminate—this makes it adaptive like insertion sort

5. **The "bubbling" motion:** Watch how 11 gradually moved from the right end to the left: position 4 → 3 → 2 → 1 → 0, taking multiple passes to traverse the array

6. **Comparison with the others:**
   - **Selection sort** found the minimum and placed it directly (1 move per element)
   - **Insertion sort** bubbled each element backward until it found its place (variable moves)
   - **Bubble sort** made many small adjacent swaps across multiple passes (most total moves)

---

These notes were written with the assistance of an LLM! Especially the long examples