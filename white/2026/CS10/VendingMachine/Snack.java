/**
 * A single snack in a vending machine. This class is complete. You do not need
 * to change it.
 */
public class Snack {
    private String name;
    private double price;

    public Snack(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /** Returns the name of this snack */
    public String getName() {
        return name;
    }

    /** Returns the price of this snack, in dollars */
    public double getPrice() {
        return price;
    }

    /** Returns something like "chips $1.50" */
    public String toString() {
        return name + " $" + String.format("%.2f", price);
    }
}
