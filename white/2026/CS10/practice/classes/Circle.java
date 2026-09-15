public class Circle {
    private double radius;

    // Constructor
    public Circle(double r) {
        radius = r;
    }

    // Returns the radius
    public double getRadius() {
        return radius;
    }

    // Sets the radius
    public void setRadius(double r) {
        radius = r;
    }

    // Returns the area (pi * r^2)
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Returns the circumference (2 * pi * r)
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    // Returns the diameter
    public double getDiameter() {
        return 2 * radius;
    }

    // Increases the radius by the given amount
    public void grow(double amount) {
        radius += amount;
    }

    // Returns the string representation
    public String toString() {
        return "Circle with radius " + radius;
    }
}
