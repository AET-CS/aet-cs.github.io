import java.util.Scanner;

/**
 * A text-based vending machine you can play with. Run this to try out your
 * methods by hand. Run Tester to check them.
 */
public class VendingMachineApp {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(
                new Snack[][] { { null, new Snack("gum", 0.75), new Snack("chips", 1.50), new Snack("mints", 0.75) },
                        { new Snack("candy", 1.25), null, new Snack("pretzels", 1.00), null },
                        { new Snack("cookies", 2.00), new Snack("mints", 0.75), null, new Snack("popcorn", 1.75) } });

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println(machine);
            System.out.println("1) Restock a row");
            System.out.println("2) Buy the cheapest snack           (buyCheapest)");
            System.out.println("3) Buy the cheapest, full machine   (buyCheapestFull)");
            System.out.println("4) Start over with a full machine");
            System.out.println("5) Start over with an empty machine");
            System.out.println("0) Quit");
            System.out.print("> ");
            String choice = in.nextLine().trim();

            if (choice.equals("0")) {
                break;
            } else if (choice.equals("1")) {
                System.out.print("Row: ");
                int row = Integer.parseInt(in.nextLine().trim());
                if (row < 0 || row >= machine.getNumRows()) {
                    System.out.println("No such row.");
                    continue;
                }
                System.out.print("Snack name: ");
                String name = in.nextLine().trim();
                System.out.print("Price: ");
                double price = Double.parseDouble(in.nextLine().trim());
                boolean placed = machine.restock(row, new Snack(name, price));
                System.out.println(placed ? "Restocked " + name + "." : "Row " + row + " is full.");
            } else if (choice.equals("2") || choice.equals("3")) {
                Snack bought = choice.equals("2") ? machine.buyCheapest() : machine.buyCheapestFull();
                System.out.println(bought == null ? "Nothing came out." : "*clunk* You got " + bought);
            } else if (choice.equals("4")) {
                machine = new VendingMachine(
                        new Snack[][] { { new Snack("chips", 1.50), new Snack("gum", 0.75), new Snack("mints", 0.75) },
                                { new Snack("candy", 1.25), new Snack("jerky", 2.50), new Snack("pretzels", 1.00) },
                                { new Snack("cookies", 2.00), new Snack("mints", 0.75), new Snack("popcorn", 1.75) } });
            } else if (choice.equals("5")) {
                machine = new VendingMachine(3, 4);
            } else {
                System.out.println("Pick 0-5.");
            }
        }
    }
}
