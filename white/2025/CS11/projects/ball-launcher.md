## Canon Ball Launcher

If your code isn't working or you want to get my numbers exactly, here's my entire `while` loop. Feel free to use it (but add a comment in the code saying where you got this).

```java
while (!hit_ground) {
    frame++;
    time += delta_t;

    // Euler integration step
    y = y + v_y * delta_t;
    v_y = v_y + a_y * delta_t;
    x = x + v_x * delta_t;

    // vx does not change.

    if (v_y < 0 && y <= radius) {
        hit_ground = true;
    }

    if (y > max_height) {
        max_height = y;
    }

    // Clear and draw
    StdDraw.clear(StdDraw.WHITE);
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.filledCircle(x, y, radius);

    StdDraw.show();
    StdDraw.pause(1000 / fps);

    // Print data rows (optional)
    System.out.printf("%-15.3f %-15.3f %-15.3f %-15.3f %-15.3f%n",
            time, x, y, v_x, v_y);
}
```