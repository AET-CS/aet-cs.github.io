# ArrayList Warm-Up: What's in the List?

## Reminder: What the Methods Return

| Method | What it does | What it returns |
|--------|--------------|-----------------|
| `add(E obj)` | Adds `obj` to the end of the list | `boolean` (always `true`) |
| `add(int index, E obj)` | Inserts `obj` at `index`, shifting later elements right | nothing (`void`) |
| `get(int index)` | Looks at the element at `index`; the list is unchanged | the element at `index` |
| `set(int index, E obj)` | Replaces the element at `index` with `obj` | the **old** element that was at `index` |
| `remove(int index)` | Removes the element at `index`, shifting later elements left | the element that was removed |
| `size()` | Counts the elements; the list is unchanged | the number of elements |

For each problem, write down the contents of the ArrayList after the code runs, in the same format `System.out.println` would print it (for example, `[3, 5, 7]`). Some problems also ask for the value of a variable.

---

## Part 1: No Loops

### Problem 1

```java
ArrayList<Integer> nums = new ArrayList<Integer>();
nums.add(4);
nums.add(8);
nums.add(15);
nums.set(1, nums.get(0) + nums.get(2));
nums.add(1, nums.get(2) - 5);
nums.set(3, nums.get(1) * 2);
nums.add(nums.get(0));
```

What is in `nums`?

### Problem 2

```java
ArrayList<String> words = new ArrayList<String>();
words.add("red");
words.add("green");
words.add("blue");
String old = words.set(0, "yellow");
words.add(old);
words.set(3, words.set(2, "purple"));
words.add(1, words.get(3));
```

What is in `words`? What is the value of `old`?

### Problem 3

```java
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(5);
list.add(10);
list.add(15);
list.add(20);
list.add(25);
int a = list.remove(2);
list.add(1, a);
list.set(0, list.remove(3));
list.add(list.get(1) - list.get(2));
```

What is in `list`? What is the value of `a`?

### Problem 4

```java
ArrayList<String> items = new ArrayList<String>();
items.add("A");
items.add("B");
items.add("C");
items.add("D");
items.add("E");
String x = items.remove(1);
String y = items.set(2, x);
items.add(1, y);
items.set(0, items.remove(items.size() - 1));
```

What is in `items`? What are the values of `x` and `y`?

### Problem 5

```java
ArrayList<Integer> data = new ArrayList<Integer>();
data.add(2);
data.add(4);
data.add(6);
data.add(8);
data.add(10);
data.add(12);
data.set(1, data.remove(4));
data.add(2, data.get(0) + data.remove(3));
data.remove(data.get(0) - 1);
data.set(data.size() - 1, data.get(1) + data.get(2));
```

What is in `data`?

---

## Part 2: With Loops

### Problem 6

```java
ArrayList<Integer> multiples = new ArrayList<Integer>();
for (int i = 1; i <= 10; i++)
{
    if (i % 3 == 0)
    {
        multiples.add(i);
    }
}
```

What is in `multiples`?

### Problems 7-10

Each of the following problems starts with a fresh copy of this list:

```java
ArrayList<Integer> nums = new ArrayList<Integer>();
nums.add(2);
nums.add(4);
nums.add(6);
nums.add(8);
nums.add(10);
nums.add(12);
```

### Problem 7

```java
for (int i = 0; i < 3; i++)
{
    nums.add(0, i);
}
```

What is in `nums`?

### Problem 8

```java
for (int i = 0; i < 3; i++)
{
    int first = nums.remove(0);
    nums.add(first + 1);
}
```

What is in `nums`?

### Problem 9

```java
for (int i = nums.size() - 1; i >= 0; i--)
{
    if (nums.get(i) > 5)
    {
        nums.remove(i);
    }
}
```

What is in `nums`?

### Problem 10

```java
for (int i = 0; i < nums.size(); i++)
{
    if (nums.get(i) > 5)
    {
        nums.remove(i);
    }
}
```

What is in `nums`? Compare your answer to Problem 9. Why do the two differ?
