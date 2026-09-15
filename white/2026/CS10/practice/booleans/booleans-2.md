# Boolean Algebra Worksheet

## Instructions
Use Java boolean notation: `&&` (AND), `||` (OR), `!` (NOT)
Variables: A, B, C, D represent boolean values (true or false)

---

## Part 1: Apply DeMorgan's Laws
Simplify the following expressions using DeMorgan's laws:

1. `!(A && B)`

2. `!(A || B || C)`

3. `!(!A && B)`

4. `!(A && !B)`

5. `!((A || B) && C)`

6. `!(A && B && !C)`

---

## Part 2: Truth Tables
Create complete truth tables for each of the following expressions:

1. `A && (B || C)`

2. `!A || (B && C)`

3. `(A || B) && (!A || C)`

4. `A && B || !A && C`

---

## Part 3: Which Identities Are True?
For each statement, write **TRUE** or **FALSE**:

1. `A && true = A`

2. `A || false = false`

3. `A && !A = false`

4. `A || (A && B) = A`

5. `A && A = A`

6. `!(A && B) = !A || !B`

---

## Part 4: Java Code Analysis
Given the following Java code:

```java
boolean x = true;
boolean y = false;
boolean z = true;

if (x && y) {
    System.out.println("Line A");
} else if (!x || z) {
    System.out.println("Line B");
} else {
    System.out.println("Line C");
}

if (x || (!y && z)) {
    System.out.println("Line D");
}

if (!(x && !z)) {
    System.out.println("Line E");
} else if (y || z) {
    System.out.println("Line F");
} else {
    System.out.println("Line G");
}
```

**Questions:**
1. What does the first if-else block print?
2. Does "Line D" get printed?
3. What does the second if-else block print?
4. If we changed `z = false`, what would be the complete output?

---

## Answer Key

### Part 1: DeMorgan's Laws
1. `!A || !B`
2. `!A && !B && !C`
3. `A || !B`
4. `!A || B`
5. `(!A && !B) || !C`
6. `!A || !B || C`

### Part 2: Truth Tables

**1. `A && (B || C)`**

| A   | B   | C   | B\|\|C | A&&(B\|\|C) |
| --- | --- | --- | ------ | ----------- |
| T   | T   | T   | T      | T           |
| T   | T   | F   | T      | T           |
| T   | F   | T   | T      | T           |
| T   | F   | F   | F      | F           |
| F   | T   | T   | T      | F           |
| F   | T   | F   | T      | F           |
| F   | F   | T   | T      | F           |
| F   | F   | F   | F      | F           |

**2. `!A || (B && C)`**

| A   | B   | C   | !A  | B&&C | !A\|\|(B&&C) |
| --- | --- | --- | --- | ---- | ------------ |
| T   | T   | T   | F   | T    | T            |
| T   | T   | F   | F   | F    | F            |
| T   | F   | T   | F   | F    | F            |
| T   | F   | F   | F   | F    | F            |
| F   | T   | T   | T   | T    | T            |
| F   | T   | F   | T   | F    | T            |
| F   | F   | T   | T   | F    | T            |
| F   | F   | F   | T   | F    | T            |

**3. `(A || B) && (!A || C)`**

| A   | B   | C   | A\|\|B | !A  | !A\|\|C | (A\|\|B)&&(!A\|\|C) |
| --- | --- | --- | ------ | --- | ------- | ------------------- |
| T   | T   | T   | T      | F   | T       | T                   |
| T   | T   | F   | T      | F   | F       | F                   |
| T   | F   | T   | T      | F   | T       | T                   |
| T   | F   | F   | T      | F   | F       | F                   |
| F   | T   | T   | T      | T   | T       | T                   |
| F   | T   | F   | T      | T   | T       | T                   |
| F   | F   | T   | F      | T   | T       | F                   |
| F   | F   | F   | F      | T   | T       | F                   |

**4. `A && B || !A && C`**

| A   | B   | C   | A&&B | !A  | !A&&C | (A&&B)\|\|(!A&&C) |
| --- | --- | --- | ---- | --- | ----- | ----------------- |
| T   | T   | T   | T    | F   | F     | T                 |
| T   | T   | F   | T    | F   | F     | T                 |
| T   | F   | T   | F    | F   | F     | F                 |
| T   | F   | F   | F    | F   | F     | F                 |
| F   | T   | T   | F    | T   | T     | T                 |
| F   | T   | F   | F    | T   | F     | F                 |
| F   | F   | T   | F    | T   | T     | T                 |
| F   | F   | F   | F    | T   | F     | F                 |

### Part 3: Which Identities Are True?
1. **TRUE** - Identity Law
2. **FALSE** - Should be `A || false = A`
3. **TRUE** - Complement Law
4. **TRUE** - Absorption Law
5. **TRUE** - Idempotent Law
6. **TRUE** - DeMorgan's Law

### Part 4: Java Code Analysis
1. **Line B** - First condition `x && y` is false, so checks `!x || z` which is `false || true = true`
2. **Yes** - `x || (!y && z)` = `true || (true && true)` = `true`
3. **Line E** - `!(x && !z)` = `!(true && false)` = `!false` = `true`
4. **Complete output if z = false**: Line C, Line D, Line G
   - First block: `!x || z` = `false || false = false`, so Line C
   - Middle: `x || (!y && z)` = `true || false = true`, so Line D
   - Last block: `!(x && !z)` = `!(true && true)` = `false`, so check `y || z` = `false || false = false`, so Line G

---
header-includes:
  - \usepackage{fullpage}
---
