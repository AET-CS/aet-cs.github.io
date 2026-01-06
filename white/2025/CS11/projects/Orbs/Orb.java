import java.awt.*;

public class Orb {

    // Default constructor - randomizes position and velocity
    public Orb() {
        this.radius = Math.random() * 20 + 20;

        this.x = Math.random() * (WIDTH - 2 * this.radius) + this.radius;
        this.y = Math.random() * (HEIGHT - 2 * this.radius) + this.radius;

        this.vx = Math.random() * 400 - 200; // Random velocity between -200 and 200
        this.vy = Math.random() * 400 - 200;

        this.ax = 0;
        this.ay = -GRAVITY; // gravity

        this.color = StdDraw.BLUE;
        this.dead = false;
    }

    // Constructor with position only
    public Orb(double x, double y) {
        this.x = x;
        this.y = y;
        this.vx = Math.random() * 400 - 200;
        this.vy = Math.random() * 400 - 200;
        this.ax = 0;
        this.ay = -980.0;
        this.radius = 20;
        this.color = StdDraw.BLUE;
    }

    // Full constructor
    public Orb(double x, double y, double vx, double vy, double ax, double ay, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.radius = radius;
        this.color = color;
    }

    // Update position and velocity using Euler integration
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

    // Handle wall collisions
    private void checkWallCollision() {
        if (y + radius > HEIGHT || y - radius < 0) {
            vy = -vy;
        }
        if (x + radius > WIDTH || x - radius < 0) {
            vx = -vx;
        }
    }

    public void checkOrbCollision(Orb orb) {
        if (orb == this) {
            return;
        }
        double d = Math.sqrt(Math.pow(x - orb.x, 2) + Math.pow(y - orb.y, 2));
        if (d < radius + orb.radius) {
            if (radius > orb.radius) {
                orb.dead = true;
            } else {
                dead = true;
            }
        }
    }

    // Draw the orb
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, radius);
    }

    // Getters (useful for debugging or game logic)
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isDead() {
        return dead;
    }
}