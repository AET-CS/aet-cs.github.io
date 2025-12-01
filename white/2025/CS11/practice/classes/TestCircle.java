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
