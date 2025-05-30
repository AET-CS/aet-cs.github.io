public class MyArrayListDriver {
    public static void main(String[] args) {
        // Create a new MyArrayList of Strings
        MyArrayList<String> names = new MyArrayList<>();

        System.out.println("Testing MyArrayList implementation with strings:");

        // Test adding elements
        System.out.println("\n--- Testing add() method ---");
        names.append("Alice");
        names.append("Bob");
        names.append("Charlie");
        names.append("David");
        System.out.println("Added 4 names to the list");
        System.out.println("Current size: " + names.size());

        // Test get method
        System.out.println("\n--- Testing get() method ---");
        System.out.println("Element at index 0: " + names.get(0));
        System.out.println("Element at index 2: " + names.get(2));

        // Test set method
        System.out.println("\n--- Testing set() method ---");
        String oldValue = names.set(1, "Barbara");
        System.out.println("Replaced '" + oldValue + "' with 'Barbara' at index 1");
        System.out.println("Element at index 1 is now: " + names.get(1));

        // Test add at index
        System.out.println("\n--- Testing add(index, element) method ---");
        names.add(2, "Eve");
        System.out.println("Added 'Eve' at index 2");
        System.out.println("Current size: " + names.size());

        // Print all elements
        System.out.println("\n--- Current list contents ---");
        for (int i = 0; i < names.size(); i++) {
            System.out.println("Index " + i + ": " + names.get(i));
        }

        // Test remove by index
        System.out.println("\n--- Testing remove(index) method ---");
        String removed = names.remove(3);
        System.out.println("Removed '" + removed + "' from index 3");
        System.out.println("Current size: " + names.size());

        // Test remove by object
        System.out.println("\n--- Testing remove(Object) method ---");
        boolean wasRemoved = names.remove("Alice");
        System.out.println("Was 'Alice' removed? " + wasRemoved);
        System.out.println("Current size: " + names.size());

        // Test contains
        System.out.println("\n--- Testing contains() method ---");
        System.out.println("Contains 'Barbara'? " + names.contains("Barbara"));
        System.out.println("Contains 'Alice'? " + names.contains("Alice"));

        // Test indexOf
        System.out.println("\n--- Testing indexOf() method ---");
        System.out.println("Index of 'Eve': " + names.indexOf("Eve"));
        System.out.println("Index of 'Frank' (not in list): " + names.indexOf("Frank"));

        // Test clear
        System.out.println("\n--- Testing clear() method ---");
        names.clear();
        System.out.println("List cleared");
        System.out.println("Is empty? " + names.isEmpty());
        System.out.println("Current size: " + names.size());

        // Test adding after clearing
        System.out.println("\n--- Testing adding after clear ---");
        names.append("Zack");
        System.out.println("Added 'Zack' after clearing");
        System.out.println("Current size: " + names.size());
        System.out.println("Element at index 0: " + names.get(0));
    }
}