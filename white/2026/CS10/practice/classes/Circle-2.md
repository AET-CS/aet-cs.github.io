---
geometry: margin=0.5in, letterpaper
---

```java
public class Circle {
    private double radius;
    private String name;
    private static int count = 0;

    public Circle(double r) {
        radius = r;
        name = "No Name";
        count++;
    }

    public Circle(double r, String name) {
        radius = r;
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double r) {
        radius = r;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }

    public void grow(double amount) {
        radius += amount;
    }

    public String toString() {
        if (name.equals("No Name")) {
            return "Unnamed circle with radius " + radius;
        } else {
            return "Circle with radius " + radius + " and name " + name;
        }
}
```

```java
public class CircleDemo {
    public static void main(String[] args) {

        System.out.println("=== Static Count Demo ===");
        System.out.println("Count at start: " + Circle.getCount());

        Circle c1 = new Circle(5.0);
        System.out.println("After creating c1: " + Circle.getCount());

        Circle c2 = new Circle(3.0, "Little Circle");
        Circle c3 = new Circle(10.0, "Big Circle");
        System.out.println("After creating c2 and c3: " + Circle.getCount());

        System.out.println("Count via c1.getCount(): " + c1.getCount());


        System.out.println("=== Name Getter/Setter Demo ===");
        System.out.println("c1 name: \"" + c1.getName() + "\"");
        System.out.println("c2 name: \"" + c2.getName() + "\"");

        c1.setName("Medium Circle");
        System.out.println("c1 name after setName: \"" + c1.getName() + "\"");


        System.out.println("=== Instance vs Static Demo ===");
        System.out.println("c1 radius: " + c1.getRadius());
        System.out.println("c2 radius: " + c2.getRadius());
        System.out.println("Count is shared: " + Circle.getCount());

        Circle c4 = new Circle(1.0);
        Circle c5 = new Circle(2.0, "Tiny");
        System.out.println("Final count: " + Circle.getCount());


        System.out.println("=== Other Methods Demo ===");
        System.out.println(c3.toString());
        System.out.println("c3 area: " + c3.getArea());
        System.out.println("c3 circumference: " + c3.getCircumference());


        // Describe the errors in each line
        System.out.println(c1.radius);

        System.out.println(c2.name);

        System.out.println(Circle.count);

        c1.radius = 100.0;

        System.out.println(Circle.getRadius());

        System.out.println(Circle.getName());

        Circle c6 = new Circle();

        Circle c7 = new Circle("hello");

        Circle c8 = new Circle("My Circle", 5.0);
    }
}
```