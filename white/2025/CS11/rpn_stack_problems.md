# RPN Stack Evaluation Problems

## Problem 1: **3 5 + 2 ***

| Step | Token | Operation | Stack After |
|------|-------|-----------|-------------|
| 1    | 3     | Push 3    | [3]         |
| 2    | 5     | Push 5    | [3, 5]      |
| 3    | +     | Pop 5,3 → Push 8 | [8] |
| 4    | 2     | Push 2    | [8, 2]      |
| 5    | *     | Pop 2,8 → Push 16 | [16] |

**Final Answer: 16**

---

## Problem 2: **8 2 / 3 + 5 -**

| Step | Token | Operation | Stack After |
|------|-------|-----------|-------------|
| 1    | 8     | Push 8    | [8]         |
| 2    | 2     | Push 2    | [8, 2]      |
| 3    | /     | Pop 2,8 → Push 4 | [4] |
| 4    | 3     | Push 3    | [4, 3]      |
| 5    | +     | Pop 3,4 → Push 7 | [7] |
| 6    | 5     | Push 5    | [7, 5]      |
| 7    | -     | Pop 5,7 → Push 2 | [2] |

**Final Answer: 2**

---

## Problem 3: **7 3 - 2 * 6 +**

| Step | Token | Operation | Stack After |
|------|-------|-----------|-------------|
| 1    | 7     | Push 7    | [7]         |
| 2    | 3     | Push 3    | [7, 3]      |
| 3    | -     | Pop 3,7 → Push 4 | [4] |
| 4    | 2     | Push 2    | [4, 2]      |
| 5    | *     | Pop 2,4 → Push 8 | [8] |
| 6    | 6     | Push 6    | [8, 6]      |
| 7    | +     | Pop 6,8 → Push 14 | [14] |

**Final Answer: 14**

---

## Problem 4: **4 5 * 3 2 + / 6 -**

| Step | Token | Operation | Stack After |
|------|-------|-----------|-------------|
| 1    | 4     | Push 4    | [4]         |
| 2    | 5     | Push 5    | [4, 5]      |
| 3    | *     | Pop 5,4 → Push 20 | [20] |
| 4    | 3     | Push 3    | [20, 3]     |
| 5    | 2     | Push 2    | [20, 3, 2]  |
| 6    | +     | Pop 2,3 → Push 5 | [20, 5] |
| 7    | /     | Pop 5,20 → Push 4 | [4] |
| 8    | 6     | Push 6    | [4, 6]      |
| 9    | -     | Pop 6,4 → Push -2 | [-2] |

**Final Answer: -2**

---

## Problem 5: **12 3 / 2 + 5 * 1 -**

| Step | Token | Operation | Stack After |
|------|-------|-----------|-------------|
| 1    | 12    | Push 12   | [12]        |
| 2    | 3     | Push 3    | [12, 3]     |
| 3    | /     | Pop 3,12 → Push 4 | [4] |
| 4    | 2     | Push 2    | [4, 2]      |
| 5    | +     | Pop 2,4 → Push 6 | [6] |
| 6    | 5     | Push 5    | [6, 5]      |
| 7    | *     | Pop 5,6 → Push 30 | [30] |
| 8    | 1     | Push 1    | [30, 1]     |
| 9    | -     | Pop 1,30 → Push 29 | [29] |

**Final Answer: 29**

---

## Key Points:
- **Stack grows from left to right** (rightmost element is top of stack)
- **Operators pop two values**: second-to-top is left operand, top is right operand
- **For subtraction/division**: be careful about operand order (a b - means a - b)
- **Final stack should contain exactly one value** - that's your answer!