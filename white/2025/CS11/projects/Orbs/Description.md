---
title: "Lab Assignment: Orb Bouncer"
layout: single
---

## Overview
In this lab, you will create an `Orb` class to simulate bouncing circular objects with realistic physics. You previously worked with a single bouncing ball—now you'll refactor that code using object-oriented programming principles to manage multiple orbs simultaneously.

## Learning Objectives
- Practice class design with instance variables and methods
- Implement constructor overloading
- Use ArrayLists to manage collections of objects
- Apply encapsulation principles (private variables, public methods)

## Project Layout

Create a new project with two new files "Orb.java" and "OrbSimulation.java". You will create Orb.java yourself, described below. You will download [OrbSimulation.java](./OrbSimulation.java) and modify it as needed.

## The Orb Class

This describes the content of "Orb.java"

### Instance Variables (all private)
- `x`, `y` - position (in pixels)
- `vx`, `vy` - velocity (in pixels/second)
- `ax`, `ay` - acceleration (in pixels/second²)
- `radius` - size of the orb (in pixels)
- `color` - Color object for drawing (of type `Color`)

*Note* you should `import java.awt.*`, which defines the `Color` type. StdLib include constants like `StdDraw.BLUE`. You can find all the colors within the source code [here](https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html)

### Class Constants (public static final)
- `WIDTH = 640` - canvas width (you can change this)
- `HEIGHT = 480` - canvas height (you can change this)
- `GRAVITY = 980.0` - gravitational acceleration in cm/s

(in Java, `final` means the value cannot be changed)

### Constructors
You must implement **three constructors**:

1. **Default constructor** `Orb()`
   - Randomizes position within valid bounds (considering radius)
   - Randomizes velocity between -200 and 200 pixels/second in both directions
   - Sets `ax = 0`, `ay = -GRAVITY`
   - Sets `radius = 20` and `color = StdDraw.BLUE`

2. **Position constructor** `Orb(double x, double y)`
   - Uses the provided x and y coordinates
   - Randomizes velocity (same as default)
   - Uses default values for acceleration, radius, and color
   - remember to use `this` to distinguish fields like `x` from the parameter `x`.

3. **Full constructor** `Orb(double x, double y, double vx, double vy, double ax, double ay, double radius, Color color)`
   - Initializes all instance variables with parameters passed in the method call

### StdLib

We will use **StdLib** again for this. Look for your stdlib.jar file, copy it to the 'src/' folder and right click  -- 'Add as library...' It can also be found [here](https://introcs.cs.princeton.edu/java/stdlib/).


### Methods

#### `public void update(double deltaT)`
Updates the orb's position and velocity using Euler integration. **This method is provided for you:**

```java
public void update(double deltaT) {
    // Update position
    double deltaX = vx * deltaT + 0.5 * ax * deltaT * deltaT;
    double deltaY = vy * deltaT + 0.5 * ay * deltaT * deltaT;
    x += deltaX;
    y += deltaY;

    // Update velocity
    vx += ax * deltaT;
    vy += ay * deltaT;

    // Check for wall collisions
    checkWallCollision();
}
```

#### `private void checkWallCollision()`
Detects when the orb hits a wall and reverses the appropriate velocity component. An orb hits a wall when its edge (center ± radius) reaches a boundary.

**Physics of wall collision:** When an orb bounces off a wall, the velocity component perpendicular to that wall reverses. For example, hitting the top or bottom wall reverses `vy`, while hitting the left or right wall reverses `vx`.

#### `public void draw()`
Draws the orb on the canvas using `StdDraw.filledCircle()`. Set the pen color before drawing.

#### Getter methods
Provide public getter methods for: `x`, `y`, `vx`, `vy`, and `radius`

## The Main Simulation Class

You will be provided with most of the main simulation class (see starter code). The key structure is:

```java
private ArrayList<Orb> orbs;

public static void createOrbs(int n) {
    // Create n orbs and add them to the ArrayList
}

public static void run() {
    // Animation loop:
    // 1. Clear the canvas
    // 2. Update all orbs
    // 3. Draw all orbs
    // 4. Show the frame
}
```

**createOrbs** -- you need to write this method! Add orbs to the orb list.

## Testing Your Code
Start with 5-10 orbs. You should see them bounce around the canvas with gravity pulling them downward. Orbs should stay within bounds and bounce realistically off walls.

## Tips
- Make sure orbs initialize within valid bounds: `x` should be between `radius` and `WIDTH - radius`
- Use `Math.random()` to generate random values. For example `Math.random() * 100 + 50` will produce a number in the range `[50, 150]`.
- Remember that `ay` should be negative (gravity pulls downward)
- Test each constructor individually to ensure they work correctly

## Next steps (optional)

* Make the orbs different sizes and colors when you create them
* Add sounds or visual effects
* Make something happen when the orb hits the wall or floor (speed up, slow down, change size?)
* Next we will add code to check collisions and then you can build this into a fun project.

## Submission
This will be submitted as part of a larger project.