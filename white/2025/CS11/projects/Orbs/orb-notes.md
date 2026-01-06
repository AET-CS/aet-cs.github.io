# AP Computer Science – Lecture Notes
## Topic: **ArrayLists & Common Gotchas** (plus a quick look at the **Orb Project**)

---

## 1. Arrays vs. ArrayLists

| Feature           | **Array**                                                              | **ArrayList**                                                              |
| ----------------- | ---------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| **Size**          | Fixed when declared (`int[] a = new int[10];`). Cannot grow or shrink. | Dynamic – can grow or shrink at runtime.                                   |
| **Syntax**        | `a[0]`, `a[i]`                                                         | `list.get(i)`, `list.set(i, value)`                                        |
| **Allowed Types** | Primitive types (`int`, `char`, `double`, …) **and** objects.          | **Only objects** (wrapper classes for primitives).                         |
| **Typical Use**   | Low‑level, fixed‑size data (e.g., matrix of pixels).                   | Collections where the number of elements changes (e.g., a list of scores). |

**Key takeaway:** An `ArrayList` is a *fancier* version of an array, but it **must store objects**, not raw primitives.

---

## 2. Primitive Types ↔ Wrapper Objects

| Primitive | Wrapper Class | Example of autoboxing/unboxing                                                       |
| --------- | ------------- | ------------------------------------------------------------------------------------ |
| `int`     | `Integer`     | `Integer i = 5; // autoboxes int → Integer`<br>`int j = i; // unboxes Integer → int` |
| `double`  | `Double`      | `Double d = 3.14;`                                                                   |
| `char`    | `Character`   | `Character c = 'A';`                                                                 |
| `boolean` | `Boolean`     | `Boolean b = true;`                                                                  |

- **Why it matters:** `ArrayList<Integer>` stores `Integer` objects, not `int`s. Java automatically converts (`autoboxes`) when you add or retrieve values, but the conversion can lead to subtle bugs—especially with the overloaded `remove` method (see § 4).

---

## 3. Basic `ArrayList` Operations

```java
// 1. Declaration & initialization
ArrayList<Integer> list = new ArrayList<>();

// 2. Adding elements
list.add(1);
list.add(2);
list.add(3);

// 3. Accessing elements
int first = list.get(0);      // returns 1
int second = list.get(1);    // returns 2

// 4. Size of the list
int n = list.size();         // n == 3
```

### 3.1 Removing Elements

| Method              | What it does                                        | Example                                                                      |
| ------------------- | --------------------------------------------------- | ---------------------------------------------------------------------------- |
| `remove(int index)` | Removes the element **at the given index**.         | `list.remove(1); // removes the element originally at index 1 → the value 2` |
| `remove(Object o)`  | Removes the **first occurrence** of the object `o`. | `list.remove(Integer.valueOf(1)); // removes the value 1, not index 1`       |

> **Important:** With `ArrayList<Integer>`, `list.remove(1)` is interpreted as *remove by index*, **not** *remove the value 1*. To remove the value, you must supply an `Integer` object (see the example above).

### 3.2 Removing Strings (or any non‑numeric objects)

```java
ArrayList<String> words = new ArrayList<>();
words.add("cat");
words.add("fish");
words.add("dog");

// Remove by value (no index needed)
words.remove("fish");   // list now contains "cat" and "dog"
```

---

## 4. Common Gotchas & How to Avoid Them

### 4.1 Index vs. Object Removal (the “Integer vs int” confusion)

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.add(3);

// Intended: remove the *value* 1
list.remove(Integer.valueOf(1));   // correct: removes the Integer object 1
// Equivalent (explicit cast):
list.remove((Integer) 1);          // also works
```

- **Wrong way:** `list.remove(1);` → removes the element at index 1 (the value 2) because the compiler picks the `remove(int)` overload.

### 4.2 Removing While Iterating (Forward Loop)

```java
// BAD: forward loop + remove
for (int i = 0; i < list.size(); i++) {
    if (list.get(i) % 2 == 0) { // remove even numbers
        list.remove(i);
    }
}
```

**Why this is problematic:**
1. **Size changes** – after a removal, `list.size()` shrinks, but the loop’s termination condition was based on the original size.
2. **Index shift** – elements to the right of the removed element shift left. The next `i++` makes you skip the element that moved into the current slot.

*Result:* You may skip elements, get an `IndexOutOfBoundsException`, or produce an unpredictable list.

### 4.3 Safe Removal Strategies

#### A. Iterate **backwards** (most straightforward for beginners)

```java
for (int i = list.size() - 1; i >= 0; i--) {
    if (list.get(i) % 2 == 0) { // remove even numbers
        list.remove(i);
    }
}
```

- **Why it works:** Removing an element at index `i` does not affect the indices of elements **before** `i`. The loop’s `i--` moves safely to the next element to inspect.

#### B. Use an **Iterator** (more advanced but idiomatic)

```java
Iterator<Integer> itr = list.iterator();
while (itr.hasNext()) {
    if (itr.next() % 2 == 0) {
        itr.remove();        // safe removal via iterator
    }
}
```

#### C. Java 8+ **removeIf** (concise, but verify your AP CS A version)

```java
list.removeIf(num -> num % 2 == 0); // removes all even numbers
```

### 4.4 What Happens with Duplicates?

```java
ArrayList<Integer> dup = new ArrayList<>();
dup.add(5);
dup.add(7);
dup.add(5);
dup.add(9);

// Remove the *object* 5 (first occurrence only)
dup.remove(Integer.valueOf(5)); // list now: [7, 5, 9]
```

- `remove(Object o)` deletes **the first matching element**. If you need to delete *all* occurrences, use a loop or `removeIf`.

---

## 5. Quick Reference Cheat Sheet

| Operation              | Syntax                                                                                      | Notes                                               |
| ---------------------- | ------------------------------------------------------------------------------------------- | --------------------------------------------------- |
| **Create**             | `ArrayList<Type> name = new ArrayList<>();`                                                 | `Type` must be a class (e.g., `Integer`, `String`). |
| **Add**                | `list.add(value);`                                                                          | Adds to the end; list grows automatically.          |
| **Get**                | `list.get(index);`                                                                          | Returns the element at `index`.                     |
| **Set**                | `list.set(index, newValue);`                                                                | Replaces element at `index`.                        |
| **Size**               | `list.size();`                                                                              | Number of elements currently stored.                |
| **Remove by index**    | `list.remove(index);`                                                                       | `index` is an `int`.                                |
| **Remove by value**    | `list.remove(Object);`                                                                      | Pass an object (`Integer.valueOf(x)` for ints).     |
| **Loop backwards**     | `for (int i = list.size() - 1; i >= 0; i--) { … }`                                          | Safe when deleting.                                 |
| **Iterator removal**   | `Iterator<Type> it = list.iterator(); while (it.hasNext()) { if (condition) it.remove(); }` | Avoids ConcurrentModificationException.             |
| **removeIf (Java 8+)** | `list.removeIf(e -> condition);`                                                            | One‑line bulk removal.                              |

---

## 6. Application: The **Orb** Project (Intro to Object‑Oriented Design)

> **Goal:** Refactor the “bouncing ball” code into an `Orb` class that manages its own state and behavior. An `ArrayList<Orb>` will hold all orbs in the simulation.

### 6.1 Core `Orb` Fields

```java
public class Orb {
    // Position
    private double x, y;               // center coordinates

    // Motion
    private double vx, vy;             // velocity components
    private double ax, ay;             // acceleration (e.g., gravity)

    // Appearance
    private double radius;
    private Color  color;              // java.awt.Color

    // Constructors, getters, setters omitted for brevity
}
```

### 6.2 Required Methods

| Method                                                        | Purpose                                                                                                 |
| ------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `public void draw(Graphics g)`                                | Draw the orb as a filled circle on the screen.                                                          |
| `public void update(double dt)`                               | **Physics step** – apply acceleration → update velocity → update position (`x += vx*dt`, `y += vy*dt`). |
| `public void checkWallCollision(double width, double height)` | Detect collision with window bounds; reverse velocity component(s) to simulate a bounce.                |
| *(Optional)* `public boolean collidesWith(Orb other)`         | Detect collision with another orb (later stage of the project).                                         |

**Skeleton example:**

```java
public void update(double dt) {
    // 1. Apply acceleration
    vx += ax * dt;
    vy += ay * dt;

    // 2. Update position
    x += vx * dt;
    y += vy * dt;
}
```

**Wall‑collision logic (simple version):**

```java
public void checkWallCollision(double width, double height) {
    // Left or right wall
    if (x - radius < 0) {          // hit left wall
        x = radius;               // reposition inside the frame
        vx = -vx;                  // reverse x‑velocity
    } else if (x + radius > width) { // hit right wall
        x = width - radius;
        vx = -vx;
    }

    // Top or bottom wall
    if (y - radius < 0) {          // hit top wall
        y = radius;
        vy = -vy;
    } else if (y + radius > height) { // hit bottom wall
        y = height - radius;
        vy = -vy;
    }
}
```

### 6.3 Managing Multiple Orbs

```java
ArrayList<Orb> orbs = new ArrayList<>();

// Populate the list (example: 10 random orbs)
for (int i = 0; i < 10; i++) {
    Orb o = new Orb(/* initialize with random position, velocity, etc. */);
    orbs.add(o);
}

// Main loop (pseudo‑code)
while (running) {
    double dt = computeDeltaTime();   // time since last frame

    // Update each orb
    for (Orb o : orbs) {
        o.update(dt);
        o.checkWallCollision(screenWidth, screenHeight);
    }

    // Render
    Graphics g = getGraphicsContext();
    for (Orb o : orbs) {
        o.draw(g);
    }

    // (Later) handle orb‑vs‑orb collisions, removal, etc.
}
```

- **Note:** **Never** modify `orbs` (e.g., `orbs.remove(i)`) while iterating with a **for‑each** loop. Use a backward index loop or an `Iterator` if you need to delete during traversal (the same rule we discussed for `ArrayList` removal).

### 6.4 Next Steps (future extensions)

1. **Orb‑vs‑Orb collision detection** – implement a method to check overlapping circles (`distance < r1 + r2`).
2. **Lifetime handling** – when two orbs collide, “the bigger lives, the smaller dies” (remove the smaller from the list).
3. **Scoring / Game logic** – track points, add user interaction, etc.

---

## 7. Summary – The “Big Rules” for ArrayLists

1. **Dynamic size:** Use `ArrayList` when you need to add/remove elements.
2. **Objects only:** Primitives must be wrapped (`int` → `Integer`, …). Autoboxing helps, but be aware of overloads.
3. **Removal overloads:**
   - `remove(int index)` → by **position**.
   - `remove(Object o)` → by **value** (first occurrence).
   - For `ArrayList<Integer>`, **always** use `Integer.valueOf(x)` or a cast to remove a numeric value.
4. **Never remove while iterating forward** unless you adjust the index or use an `Iterator`.
5. **Safer patterns:**
   - Iterate **backwards** when deleting by index.
   - Use an `Iterator` (`itr.remove()`).
   - In Java 8+, use `removeIf`.
6. **Duplicates:** `remove(Object)` deletes the **first** matching element only.
7. **Apply the same caution** in any collection of objects (e.g., the `orbs` list in the project).

---

### 🎓 Ready for the Exam?

- Be able to **write** and **interpret** the two `remove` forms.
- Know why **autoboxing** can cause bugs with `ArrayList<Integer>`.
- Explain, with a diagram or words, why removing while iterating forward leads to skipped elements or runtime errors.
- Sketch a small class (like `Orb`) that encapsulates state and behavior, and show how an `ArrayList` of these objects is used in a simulation loop.

> **Practice:** Write a short program that creates an `ArrayList<Integer>`, fills it with the numbers 1–10, then removes all even numbers **without** causing an `IndexOutOfBoundsException`. Use the backward‑loop technique. Verify the final list contains only odd numbers.

Good luck, and happy coding! 🚀