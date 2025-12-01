```java
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
```

\clearpage

```java
public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle(5.0);
        System.out.println(c1);
        System.out.println("Area: " + c1.getArea());
        System.out.println("Circumference: " + c1.getCircumference());
        System.out.println();

        Circle c2 = new Circle(10.0);
        System.out.println(c2);
        System.out.println("Diameter: " + c2.getDiameter());
        System.out.println("Area: " + c2.getArea());
        System.out.println();

        System.out.println("Growing c1 by 3.0...");
        c1.grow(3.0);
        System.out.println(c1);
        System.out.println("New area: " + c1.getArea());
    }
}
```
