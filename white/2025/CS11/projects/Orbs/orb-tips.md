---
title: Orb Tips
---

## Reducing Glitchiness

If your code is glitchy/laggy/stuttery there may be a few ways to help

* Reduce the FPS to 10,15,20. Even try really low 4,5 in severe cases
* Don't draw too much to the screen. 1000 orbs is probably too many!
* If you are loading images, make sure the original .png file is already the right size that you want to show on screen (resize it in another app)

## Intensive remove glitchiness

Replace
```java
// Animation loop
while (true) {
    // Clear the canvas
    StdDraw.clear(StdDraw.WHITE);

    // Update and draw all orbs
    for (Orb orb : orbs) {
        orb.update(DELTA_T);
        orb.draw();
    }

    // Show the frame
    StdDraw.show();
    StdDraw.pause(1000 / FPS);
}
```

with
```java
// Animation loop
while (true) {
    // Clear the canvas
    StdDraw.clear(StdDraw.WHITE);

    currentTime = System.nanoTime();
    deltaT = currentTime - lastTime; // time since last frame
    deltaT /= 1_000_000_000.0; // convert to seconds
    lastTime = currentTime; // reset lastTime to now
    if (deltaT <= 0) { // in case it's 0, set it to 30FPS
      deltaT = 1.0 / 30.0;
    }
    double frameRate = 1.0 / deltaT;

    // Update and draw all orbs
    for (Orb orb : orbs) {
        orb.update(deltaT); // use the actual deltaT
        orb.draw();
    }

    // Show the frame
    StdDraw.show();
    // Don't pause!!
}
```

## Real 2D Collisions

I made some code to calculate orb-orb collision with bouncing if you're interested! Based on equations found [here](https://en.wikipedia.org/wiki/Elastic_collision).

```java
public void bounceOrbs2(Orb other) {
    // Position vectors
    double x1 = this.x;
    double y1 = this.y;
    double x2 = other.x;
    double y2 = other.y;

    // Velocity vectors
    double v1x = this.vx;
    double v1y = this.vy;
    double v2x = other.vx;
    double v2y = other.vy;

    // Delta velocity: v1 - v2
    double delta_vx = v1x - v2x;
    double delta_vy = v1y - v2y;

    // Delta position: x1 - x2
    double delta_xx = x1 - x2;
    double delta_xy = y1 - y2;

    // Normalize delta_x
    double delta_x_magnitude = Math.sqrt(delta_xx * delta_xx + delta_xy * delta_xy);
    double delta_x_norm_x = delta_xx / delta_x_magnitude;
    double delta_x_norm_y = delta_xy / delta_x_magnitude;

    // Scale normalized vector by (radius1 + radius2)
    double combined_radius = this.radius + other.radius;
    double delta_x_scaled_x = delta_x_norm_x * combined_radius;
    double delta_x_scaled_y = delta_x_norm_y * combined_radius;

    // New position for this orb: x1 = x2 + delta_x_scaled
    x1 = x2 + delta_x_scaled_x;
    y1 = y2 + delta_x_scaled_y;

    // Masses (using radius as mass, BUT YOU tweak)
    double m1 = this.radius;
    double m2 = other.radius;

    // Calculate dot products
    double delta_v_dot_delta_x = delta_vx * delta_x_scaled_x + delta_vy * delta_x_scaled_y;
    double delta_x_dot_delta_x = delta_x_scaled_x * delta_x_scaled_x + delta_x_scaled_y * delta_x_scaled_y;

    // Calculate velocity changes
    double scalar1 = delta_v_dot_delta_x * (m2 + m2) / (m1 + m2) / delta_x_dot_delta_x;
    double scalar2 = delta_v_dot_delta_x * (m1 + m1) / (m1 + m2) / delta_x_dot_delta_x;

    // Update v1: v1 = v1 - delta_x_scaled * scalar1
    v1x = v1x - delta_x_scaled_x * scalar1;
    v1y = v1y - delta_x_scaled_y * scalar1;

    // Update v2: v2 = v2 + delta_x_scaled * scalar2
    v2x = v2x + delta_x_scaled_x * scalar2;
    v2y = v2y + delta_x_scaled_y * scalar2;

    // Apply velocity changes to orbs
    this.vx = v1x;
    this.vy = v1y;
    other.vx = v2x;
    other.vy = v2y;

    // Move both orbs apart proportionally
    // This is required because in one frame
    // The orbs can "overlap" which is not right
    // This code 'reverses' them so they're just touching
    double total_overlap = combined_radius - delta_x_magnitude;
    double ratio1 = m2 / (m1 + m2);
    double ratio2 = m1 / (m1 + m2);
    this.x += delta_x_norm_x * total_overlap * ratio1;
    this.y += delta_x_norm_y * total_overlap * ratio1;
    other.x -= delta_x_norm_x * total_overlap * ratio2;
    other.y -= delta_x_norm_y * total_overlap * ratio2;
  }
  ```
