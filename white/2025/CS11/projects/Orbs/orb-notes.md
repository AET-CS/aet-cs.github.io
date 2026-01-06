---
title: "Deleting from Array Lists - Lecture Notes"
layout: single
---

## Review: Arrays vs. ArrayLists

### Arrays
- **Fixed size** - you declare the size at the beginning of the program
- Cannot grow or shrink after creation
- Can contain **primitive types** (int, double, char, etc.)

### ArrayLists
- **Dynamic size** - can grow and shrink as needed
- Can insert, delete, and remove elements
- Can **only contain objects** (not primitive types)
- Integers, Doubles, Characters must be wrapper classes (capitalized: `Integer`, `Double`, `Character`)

**Good news:** Java automatically converts between primitives and wrapper classes (autoboxing), so you usually don't have to worry about the difference.

---

## The Integer ArrayList Gotcha

Let's look at a common source of confusion. Consider this code:

```java
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(1);
list.add(2);
list.add(3);
```

**Question:** What happens if we call `list.remove(1)`?

**Answer:** The list becomes `[1, 3]`

Why? Because `remove()` removes the element **at index 1** (which is the value 2).

### Two Versions of remove()

ArrayList has two different `remove()` methods:
- `remove(int index)` - removes element at that index
- `remove(Object o)` - removes the first occurrence of that object

### Removing by Value (Not Index)

What if you actually want to remove the **integer 1** (not the element at index 1)?

```java
list.remove((Integer) 1);
```

**What's happening here:**
- `(Integer) 1` is **type casting** - converting the primitive `int` to the wrapper class `Integer`
- This creates an object, so Java calls `remove(Object o)` instead of `remove(int index)`
- Now it removes the **value** 1, not the element at index 1

### Why This Matters

This issue **only comes up with ArrayLists of integers**.

For other types (like Strings), there's no ambiguity:
```java
ArrayList<String> words = new ArrayList<String>();
words.add("fish");
words.remove("fish");  // Removes the String "fish" - no confusion!
```

**Key Takeaway:** Java isn't breaking any rules here. `remove(int)` always means remove by index. But with Integer ArrayLists, you need to be careful about which version you're calling.

**Note:** If an object appears multiple times in the list, `remove(Object o)` removes the **first occurrence**.

---

## The Dangerous Loop: Removing While Iterating

Here's a classic gotcha. Consider this code:

```java
ArrayList<Integer> list = new ArrayList<Integer>();
// Add some values: 1, 5, 7, 10, 9, 12, 14
list.add(1);
list.add(5);
list.add(7);
list.add(10);
list.add(9);
list.add(12);
list.add(14);

// Try to remove all even numbers
for (int i = 0; i < list.size(); i++) {
    if (list.get(i) % 2 == 0) {
        list.remove(i);
    }
}
```

**Question:** What does the list equal afterwards?

**Answer:** `[1, 5, 7, 12]` - Wait, 12 is even! This code has a bug!

### Why This Fails

The problem is **index shifting**:
- When you delete element at index 3 (value 10), then 9 shifts down to become index 3
- But `i` increments to 4 on the next iteration
- You skip over 9 and check element 4 (which is now 12)
- Then when you delete 12, value 14 shifts down to index 4
- But `i` increments to 5, so you skip 14 entirely!

**Note:** `list.size()` is recalculated every time through the loop, so the loop eventually terminates. But because of index shifting, some elements get skipped.

**Bottom Line:** Don't modify an ArrayList using indices while looping forward through it. The code runs without errors but produces incorrect results!

### What About Enhanced For Loops?

You might think using an enhanced for loop would help:

```java
// This throws ConcurrentModificationException!
for (Integer num : list) {
    if (num % 2 == 0) {
        list.remove((Integer) num);
    }
}
```

**This throws a `ConcurrentModificationException` at runtime!**

Enhanced for loops use an Iterator internally, and the Iterator detects when you've modified the ArrayList directly (not through the Iterator itself). This is actually better than the silent failure above - at least you know something went wrong!

---

## The Solution: Loop Backwards

**Big Rule:** When removing elements from an ArrayList, always work **backwards** from the end.

```java
for (int i = list.size() - 1; i >= 0; i--) {
    if (list.get(i) % 2 == 0) {
        list.remove(i);
    }
}
```

### Why This Works

- When you delete something from the end of the list, it doesn't affect the indices of elements earlier in the list
- Elements shifting down don't matter because you're moving backwards
- You never skip any elements

**Example trace:**
- Check index 6 (value 14) - it's even, delete it ✓
- Check index 5 (value 12) - it's even, delete it ✓
- Check index 4 (value 9) - it's odd, keep it ✓
- Check index 3 (value 10) - it's even, delete it ✓
- Check index 2 (value 7) - it's odd, keep it ✓
- Check index 1 (value 5) - it's odd, keep it ✓
- Check index 0 (value 1) - it's odd, keep it ✓

The code works perfectly! Result: `[1, 5, 7, 9]`

### Note on Regular Arrays

You don't have to worry about this with regular arrays because you can't delete elements from arrays. You can loop forward or backward - it doesn't matter when you're just reading or changing values.

---

## Summary

**Key Points to Remember:**

1. **ArrayLists vs Arrays:** ArrayLists can grow/shrink, arrays are fixed size
2. **Integer ArrayList:** Use `(Integer) value` to remove by value instead of by index
3. **Removing elements:** Always loop backwards when removing from an ArrayList

```java
// DON'T DO THIS - skips elements!
for (int i = 0; i < list.size(); i++) {
    list.remove(i);
}

// DON'T DO THIS - throws ConcurrentModificationException!
for (Integer item : list) {
    list.remove(item);
}

// DO THIS INSTEAD - works correctly!
for (int i = list.size() - 1; i >= 0; i--) {
    list.remove(i);
}
```