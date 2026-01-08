---
title: Orb Collisions
---

### Classes, ArrayLists, and Collision Detection with Orbs

---

## 1. Learning Goals

* **Physics** – Model gravity, elastic wall‑bounces and simple orb‑orb collisions.
* **Object‑Oriented Design** – Keep all data and behaviour for a ball inside a single `Orb` class.
* **Collections** – Use an `ArrayList<Orb>` to store a dynamic set of objects that can appear and disappear.
* **Algorithms** – Detect collisions efficiently and safely remove dead objects from the list.

---

## 2. Project Outline

| Class               | What it does                                                                                  |
| ------------------- | --------------------------------------------------------------------------------------------- |
| **`OrbSimulation`** | Sets up the drawing canvas, creates a bunch of `Orb`s, runs the animation loop.               |
| **`Orb`**           | Represents one ball: its geometry, physics (gravity, bounce), rendering, and collision logic. |


---

## 3. Physics refresher

### 3.1. Gravity (Euler integration)

* Acceleration is constant: `GRAVITY = 980` px / s² (downward).
* For a time step `Δt = 1/FPS` we update:

```
position   = position + velocity·Δt + ½·acceleration·Δt²
velocity   = velocity + acceleration·Δt
```

The `update(double deltaT)` method inside `Orb` does exactly this.

### 3.2. Elastic wall bounce

When a ball hits a wall:

* **Normal component** of velocity reverses (the component perpendicular to the wall).
* **Tangential component** stays unchanged.

Hence:

* Left/right walls → reverse `vx`.
* Top/bottom walls → reverse `vy`.

If you want a “lossy” bounce you would multiply the reversed component by a factor `< 1` (e.g., `vx = -vx * 0.9`). This could simulate gravity or more-realistic inelastic collisions.

---

## 4. Detecting a wall collision

The ball is a circle with centre `(x, y)` and radius `r`.
A wall must be compared with the **edge** of the circle, not the centre.

| Wall   | Collision test    |
| ------ | ----------------- |
| Left   | `x - r <= 0`      |
| Right  | `x + r >= WIDTH`  |
| Bottom | `y - r <= 0`      |
| Top    | `y + r >= HEIGHT` |

When any test is true we invert the appropriate velocity component (see `checkWallCollision()`).

---

## 5. Managing many orbs with an `ArrayList`

```java
private static ArrayList<Orb> orbs;
```

* The list starts with a number of randomly generated orbs (`createOrbs(int n)`).
* It can grow or shrink because orbs may be marked **dead** and removed later.

### Removing safely

When an orb is killed we do **not** remove it immediately while iterating forward—this would corrupt the indices.
Instead we perform a *second* pass **from the end of the list toward the front**:

```java
for (int i = orbs.size() - 1; i >= 0; i--) {
    if (orbs.get(i).isDead()) {
        orbs.remove(i);
    }
}
```

*Rule of thumb:* *Always delete from an `ArrayList` by walking backwards.*

---

## 6. Orb‑orb collision – design discussion

### 6.1. Where should the collision test live?

Because each orb knows its own position, radius, and alive status, the natural place for the test is **inside the `Orb` class**.
The method signature is:

```java
public void checkOrbCollision(Orb other)
```

Only **one** parameter is needed: the *other* orb we want to compare with.
The orb performing the check is implicitly `this`.

### 6.2. What does the method have to do?

Below is a detailed walk‑through of the algorithm (no actual source code, just the steps you must implement).

| Step                                       | Explanation                                                                                                                                                                                                   |
| ------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **1️⃣ Guard against self‑collision**         | If `other == this` we must do nothing – an object cannot collide with itself!                                                                                                                                 |
| **2️⃣ Compute the distance between centres** | <ul><li>`dx = this.x – other.x`</li><li>`dy = this.y – other.y`</li><li>`distance = sqrt(dx*dx + dy*dy)`</li></ul> This is the Euclidean distance.                                                            |
| **3️⃣ Compare with the sum of radii**        | The two circles intersect when `distance < (this.radius + other.radius)`. If the distance is exactly equal they are just touching; using `<` is fine for a simulation that works with floating‑point numbers. |
| **4️⃣ Resolve the collision**                | In the starter assignment the rule is “the **smaller** orb disappears”.<br>• If `this.radius > other.radius`, set `other.dead = true`.<br>• Otherwise set `this.dead = true`.                                 |
| **5️⃣ (Optional) Extend the rule**           | You could replace the “smaller dies” logic with a realistic elastic bounce, a “bullet kills any target”, or an energy‑transfer rule. This is a perfect place for creative extensions.                         |
| **6️⃣ Mark the result**                      | The method should **only** change the `dead` flag(s); actual removal from the `ArrayList` happens later in `OrbSimulation.run()`.                                                                             |

#### Pseudocode

```
if (other == this) return;

distance = distance between centers

if (distance < this.radius + other.radius) {
    o = the smaller orb
    o.dead = true
}
```

### 6.3. How does `OrbSimulation` use this method?

Inside the main animation loop we have two nested loops:

1. **Outer loop** – iterates over each orb, calls `update` and `draw`.
2. **Inner loop** – for the **same** orb, iterates over *all* other orbs and invokes `checkOrbCollision(other)`.

Note: Because each orb checks itself against every other orb, *every* possible pair is examined twice (once from each side). That is acceptable for the modest `n` (≈ 50) used here. If you ever need to scale up, you would change the inner loop to only consider later indices (`j = i+1 …`) to avoid duplicate checks.

---

## 7. Putting it all together – the animation loop (high‑level)

```
while (true) {
    clear canvas
    for each orb:
        update physics (gravity, wall bounce)
        draw orb
        for each other orb:
            orb.checkOrbCollision(other)
    end inner loop
    end outer loop

    // Remove any orbs that were marked dead
    iterate list backwards and remove when orb.isDead()

    show frame, pause for next tick
}
```

*Key points to remember while you implement this loop:*

* **Order matters** – all updates and collision checks happen **before** any removals.
* **Double buffering** (`StdDraw.enableDoubleBuffering()`) prevents flickering.
* **Consistent time step** – `DELTA_T = 1/FPS` keeps the simulation deterministic.

---

## 8. Common mistakes & how to avoid them

| Symptom                                             | Likely cause                                           | Remedy                                                                           |
| --------------------------------------------------- | ------------------------------------------------------ | -------------------------------------------------------------------------------- |
| Orbs sink into the wall before bouncing             | Wall test uses centre instead of edge (`x ± radius`)   | Compare the edge (`x + radius > WIDTH`, etc.).                                   |
| `ConcurrentModificationException` or missing checks | Removing from `ArrayList` while iterating forward      | Remove only in the separate *backwards* pass.                                    |
| “Cannot find symbol” for `other.x` / `this.x`       | Forgetting the `this` qualifier inside the class       | Use `this.x` for the current orb, `other.x` for the argument.                    |
| Orbs never disappear after collision                | No `dead` flag or it isn’t checked later               | Add a `boolean dead` field; after each frame remove orbs where `dead` is `true`. |
| Orbs bounce forever without losing energy           | Wall collision inverts velocity *exactly* (`vx = -vx`) | Multiply by a damping factor (< 1) if you want a “sticky” bounce.                |

---

## 9. Extensions & Challenge Ideas

* **Energy loss on bounce** – multiply the reversed component by something less than one.
* **Sticky walls** – set the corresponding velocity component to `0` instead of negating.
* **Different collision rules** – e.g., exchange momentum (elastic collision), grow the larger orb, or have a “bullet” type that always survives.
* **Display stats** – show current number of orbs, collisions per second, or total kinetic energy on the canvas.

---

## 10. Your Assignment

**Implement** the `checkOrbCollision(Orb other)` method in `Orb.java`.

* Use the step‑by‑step algorithm described in § 6.2.
* After you finish, run `OrbSimulation` and verify that the smallest orb of any colliding pair disappears, leaving the larger one to continue moving.

When you’re comfortable with the “smaller dies” rule, feel free to experiment with the extensions in § 9.

---

**Good luck!** Write the collision method, run the simulation, and watch the larger orbs survive while the smaller ones disappear. Feel free to experiment with the extensions once the basic assignment works.