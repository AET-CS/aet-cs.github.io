# Trace Tables for Loop Problems - Easy Starter Questions V2

## trace2() - Basic Loop
```java
for (int i = 2; i <= 8; i = i + 2) {
    sum = sum + i;
}
```

| Step | i   | i <= 8? | sum (before) | sum = sum + i | sum (after) |
| ---- | --- | ------- | ------------ | ------------: | ----------- |
| Init | --  | --      | 0            |            -- | 0           |
| 1    | 2   | true    | 0            |       0 + 2 = | 2           |
| 2    | 4   | true    | 2            |       2 + 4 = | 6           |
| 3    | 6   | true    | 6            |       6 + 6 = | 12          |
| 4    | 8   | true    | 12           |      12 + 8 = | 20          |
| 5    | 10  | false   | 20           |            -- | 20          |

**Output:** 20

## trace4() - Loop with Counter
```java
while (n > 1) {
    n = n / 2;
    count++;
}
```

| Step | n   | n > 1? | count (before) | n = n / 2 | count (after) |
| ---- | --- | ------ | -------------- | --------- | ------------- |
| Init | 15  | --     | 0              | --        | 0             |
| 1    | 15  | true   | 0              | 15/2 = 7  | 1             |
| 2    | 7   | true   | 1              | 7/2 = 3   | 2             |
| 3    | 3   | true   | 2              | 3/2 = 1   | 3             |
| 4    | 1   | false  | 3              | --        | 3             |

**Output:** 3 1

## trace5() - Loop with Conditional
```java
for (int i = 1; i <= n; i++) {
    if (i % 3 == 0) {
        result = result + i;
    }
}
```
(with n = 10)

| Step | i   | i <= 10? | i % 3 == 0? | result (before) | result + i | result (after) |
| ---- | --- | -------: | ----------- | --------------- | ---------- | -------------- |
| Init | --  |       -- | --          | 0               | --         | 0              |
| 1    | 1   |     true | false       | 0               | --         | 0              |
| 2    | 2   |     true | false       | 0               | --         | 0              |
| 3    | 3   |     true | true        | 0               | 0 + 3 =    | 3              |
| 4    | 4   |     true | false       | 3               | --         | 3              |
| 5    | 5   |     true | false       | 3               | --         | 3              |
| 6    | 6   |     true | true        | 3               | 3 + 6 =    | 9              |
| 7    | 7   |     true | false       | 9               | --         | 9              |
| 8    | 8   |     true | false       | 9               | --         | 9              |
| 9    | 9   |     true | true        | 9               | 9 + 9 =    | 18             |
| 10   | 10  |     true | false       | 18              | --         | 18             |
| 11   | 11  |    false | --          | 18              | --         | 18             |

**Output:** 18

## trace6() - While Loop with Update
```java
while (x * 3 <= n) {
    x = x * 3;
    count++;
}
```
(with n = 30)

| Step | x   | x * 3 | x * 3 <= 30? | count (before) | x = x * 3 | count (after) |
| ---- | --- | ----- | ------------ | -------------- | --------- | ------------- |
| Init | 1   | --    | --           | 0              | --        | 0             |
| 1    | 1   | 3     | true         | 0              | 3         | 1             |
| 2    | 3   | 9     | true         | 1              | 9         | 2             |
| 3    | 9   | 27    | true         | 2              | 27        | 3             |
| 4    | 27  | 81    | false        | 3              | --        | 3             |

**Output:** 27 3

## trace8() - Loop with Multiple Updates
```java
while (x < y) {
    x = x * 2;
    y = y - 3;
}
```

| Step | x   |    y | x < y? | x = x * 2 | y = y - 3 |
| ---- | --- | ---: | ------ | --------- | --------- |
| Init | 2   |   20 | --     | --        | --        |
| 1    | 2   |   20 | true   | 4         | 17        |
| 2    | 4   |   17 | true   | 8         | 14        |
| 3    | 8   |   14 | true   | 16        | 11        |
| 4    | 16  |   11 | false  | --        | --        |

**Output:** 16 11

## trace9() - Accumulator Pattern
```java
while (product < n) {
    product = product * 2;
    count++;
}
```
(with n = 100)

| Step | product | product < 100? | count (before) | product = product * 2 | count (after) |
| ---- | ------- | -------------- | -------------- | --------------------- | ------------- |
| Init | 1       | --             | 0              | --                    | 0             |
| 1    | 1       | true           | 0              | 2                     | 1             |
| 2    | 2       | true           | 1              | 4                     | 2             |
| 3    | 4       | true           | 2              | 8                     | 3             |
| 4    | 8       | true           | 3              | 16                    | 4             |
| 5    | 16      | true           | 4              | 32                    | 5             |
| 6    | 32      | true           | 5              | 64                    | 6             |
| 7    | 64      | true           | 6              | 128                   | 7             |
| 8    | 128     | false          | 7              | --                    | 7             |

**Output:** 7 128

## trace10() - Complex Loop Logic
```java
for (int i = 0; i < n; i++) {
    int next = x + 2 * y;
    x = y;
    y = next;
}
```
(with n = 4)

| Step | i   | i < 4? | x (before) | y (before) | next = x + 2*y | x (after) | y (after) |
| ---- | --- | ------ | ---------- | ---------- | -------------- | --------- | --------- |
| Init | --  | --     | 1          | 1          | --             | 1         | 1         |
| 1    | 0   | true   | 1          | 1          | 1 + 2*1 = 3    | 1         | 3         |
| 2    | 1   | true   | 1          | 3          | 1 + 2*3 = 7    | 3         | 7         |
| 3    | 2   | true   | 3          | 7          | 3 + 2*7 = 17   | 7         | 17        |
| 4    | 3   | true   | 7          | 17         | 7 + 2*17 = 41  | 17        | 41        |
| 5    | 4   | false  | 17         | 41         | --             | 17        | 41        |

**Output:** 17