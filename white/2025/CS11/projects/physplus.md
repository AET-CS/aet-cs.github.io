# Physics Simulator - Extensions

Congratulations on building a working physics simulator! Now it's time to extend it and make it more interesting. These extensions will help you explore more advanced physics concepts and make your simulation more realistic and fun.

**Getting Started:** Copy your `BallDropper.java` code to a new file called `PhysicsExtensions.java` (or whatever name you like). You'll add these features one at a time to this new file.

## Extension 1: 2D Motion with Projectile Launch

So far, your ball only moves vertically. Add horizontal motion!

Create a `v_x` variable for horizontal velocity and give it a non-zero initial value. Update the x position each frame using the same kinematic equations you used for y.

Launch your ball from the left edge of the screen with a positive v_y so it arcs upward and across the screen like a real projectile!

## Extension 2: Bouncing Off Walls

When your ball hits the edge of the screen, make it bounce back instead of disappearing or stopping.

Think about what happens physically when a ball bounces off a wall - which component of velocity changes, and which stays the same? You'll need to check all four edges (left, right, top, bottom) and handle each appropriately.

Hint: When bouncing off a vertical wall, does v_x or v_y change? What about a horizontal wall?

## Extension 3: Friction

Real bounces aren't perfectly elastic - the ball loses some energy on each bounce. Add friction to your collisions!

When the ball bounces, reduce the magnitude of the velocity component that's changing by a fixed percentage. For example, if the ball bounces off the floor, you might set `v_y = -0.8 * v_y` instead of just `v_y = -v_y`. The 0.8 means the ball retains 80% of its speed.

Experiment with different friction coefficients. What value makes the ball eventually stop? Is this physically valid?

## Extension 4: Floor Guard

You might notice that when the ball bounces off the floor, sometimes it can get "stuck" slightly below where it should be, especially if it's moving fast. Add a guard to ensure the ball's y position never goes below the floor (or below `radius` to be precise). This will make your bouncing more stable.

## Extension 5: Launch from Angle and Speed

Instead of setting v_x and v_y directly, let's think like physicists! Set an initial launch angle (in degrees) and an initial speed (magnitude of velocity).

Calculate v_x and v_y from these values using trigonometry. Refer to your physics notes for the component formulas. This is how you'd actually aim a projectile in the real world!

Try different angles - what angle gives you the maximum range?

## Extension 6: Engines and Thrust

Add horizontal acceleration! Create an `a_x` variable that you can control. You could:
- Set a constant horizontal acceleration (like wind or thrust)
- Use keyboard input to fire thrusters (StdDraw has keyboard detection methods)
- Make the acceleration change over time

This lets you simulate rockets, airplanes, or other powered flight!

## Extension 7: Air Resistance

Real objects don't fall forever at increasing speeds - air resistance slows them down. Add drag force that opposes motion.

A simple model: drag acceleration is proportional to velocity but in the opposite direction. Try something like `a_drag_y = -k * v_y` where k is a small constant (like 0.1). Apply this drag to both x and y components.

Watch how the ball reaches a terminal velocity instead of accelerating forever!

## Extension 8: Moon Gravity

What would your simulation look like on the Moon? The Moon's gravity is about 1/6 of Earth's.

Create a new constant for Moon gravity and run your simulation. How does the motion change? How does it affect bouncing height?

## Extension 9: Multiple Balls

Instead of one ball, simulate several! You'll need to use arrays (or ArrayLists) to store the position and velocity of each ball.

Each ball should follow the same physics rules independently. This is your first step toward simulating complex systems with many interacting objects.

**Optional challenge:** Create a `Ball` class to encapsulate all the properties (position, velocity, radius) and behavior of a single ball. This will make your code much cleaner!

## Extension 10: Motion Trails

Instead of clearing the screen completely each frame, don't clear it at all (or draw a white rectangle over most of the screen each frame). This will leave trails showing the path each ball has taken.

Experiment with different approaches to see what looks best!

## Extension 11: Energy Conservation

Physics tells us that energy should be conserved (in the absence of friction/drag). Calculate the total energy of your ball and see if your simulation conserves it!

Total energy = kinetic energy + potential energy
- KE = ½mv² (you can set m = 1 for simplicity)
- PE = mgy (with y measured from the ground)

Print the total energy at each frame or every few frames. Is it constant? If you add bouncing, does it stay constant? What about with drag or friction?

## Extension 12: Size Matters

Make the ball's radius affect its physics! For a sphere moving through air, the drag force depends on cross-sectional area:

$$F_{drag} = \frac{1}{2}\rho v^2 C_d A$$

where $A = \pi r^2$ is the cross-sectional area. Since $F = ma$, the drag acceleration is:

$$a_{drag} = \frac{F_{drag}}{m}$$

If we assume the ball's mass is proportional to its volume ($m \propto r^3$) and its cross-sectional area is $A \propto r^2$, then:

$$a_{drag} \propto \frac{r^2}{r^3} = \frac{1}{r}$$

This means larger balls experience less drag acceleration! Implement this relationship in your simulation. Do larger balls fall faster or reach higher terminal velocities?

---

## Challenge: Combine Them All!

Once you've tried individual extensions, combine several together to create a rich simulation. For example:
- Multiple balls with different sizes
- Each ball experiences gravity, drag, friction, and bounces off walls
- Use keyboard controls to add thrust to one ball
- Display energy for each ball
- Show motion trails in different colors

The possibilities are endless! This is how real physics simulations are built - start with basic principles and keep adding complexity.

## What You've Learned

Through these extensions, you're exploring:
- Vector physics (2D motion with independent x and y components)
- Collision detection and response
- Energy conservation and dissipation
- Complex systems with multiple interacting objects
- Numerical stability and edge cases

These are the foundations of game engines, scientific simulations, and computer graphics. You're doing real computational physics!

Keep experimenting and have fun!