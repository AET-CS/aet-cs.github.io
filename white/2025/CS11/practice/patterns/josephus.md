# The Josephus Problem
## Advanced Loop Challenge

---

## The Setup

Imagine 7 people standing in a circle, numbered 1 through 7. Starting from person 1, you count around the circle and eliminate every 3rd person. After someone is eliminated, you continue counting from the next person.

**Visual representation:**
```
    1
 7     2
6       3
 5     4
```

---

## Step-by-Step Example

**Round 1:** Start at person 1, count 3 → eliminate person **3**
```
Remaining: 1, 2, 4, 5, 6, 7
Continue from person 4
```

**Round 2:** From person 4, count 3 → eliminate person **6**
```
Remaining: 1, 2, 4, 5, 7
Continue from person 7
```

**Round 3:** From person 7, count 3 → eliminate person **2**
```
Remaining: 1, 4, 5, 7
Continue from person 4
```

**Round 4:** From person 4, count 3 → eliminate person **7**
```
Remaining: 1, 4, 5
Continue from person 1
```

**Round 5:** From person 1, count 3 → eliminate person **5**
```
Remaining: 1, 4
Continue from person 1
```

**Round 6:** From person 1, count 3 → eliminate person **1** (wrapping around)
```
Remaining: 4
Person 4 survives!
```

---

## Your Challenge

Write a Java program that simulates the Josephus problem with **n people** and **eliminating every k-th person**.

**Expected Output for n=7, k=3:**
```
Starting with 7 people in positions: 1 2 3 4 5 6 7
Eliminated person 3
Remaining people: 1 2 4 5 6 7
Eliminated person 6
Remaining people: 1 2 4 5 7
Eliminated person 2
Remaining people: 1 4 5 7
Eliminated person 7
Remaining people: 1 4 5
Eliminated person 5
Remaining people: 1 4
Eliminated person 1
Remaining people: 4
Survivor: Person 4
```

---

- Research: There's actually a mathematical formula for this! Can you find it?

---

## Historical Note

This problem is named after Flavius Josephus, a Jewish historian who reportedly used this method to determine the order of elimination among soldiers. It's a classic problem in computer science and mathematics!
