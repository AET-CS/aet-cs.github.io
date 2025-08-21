---
title: "Scanner Examples"
---


Here's a simple scanner that reads and writes words

```java
import java.util.Scanner;

public class UserInputExample {

    public static void main(String[] args) {
        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter some words (type 'exit' to stop):");

        // Loop to continuously ask for input until 'exit' is entered
        while (true) {
            // Ask the user for input
            String input = scanner.nextLine();

            // Check if the user typed 'exit' to break the loop
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Print the input back to the user
            System.out.println("You entered: " + input);
        }

        // Close the scanner when done
        scanner.close();

        System.out.println("Goodbye!");
    }
}
```

Here's a scanner that reads from a file

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderExample {

    public static void main(String[] args) {
        try {
            // Create a File object for the file you want to read
            File file = new File("input.txt");

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            // Read and print each line from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

            // Close the scanner when done
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
```

Here's a scanner that reads a file and a PrintWriter that writes another one

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReaderWriterExample {

    public static void main(String[] args) {
        try {
            // Create File objects for the input and output files
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");

            // Create a Scanner object to read the input file
            Scanner scanner = new Scanner(inputFile);

            // Create a PrintWriter object to write to the output file
            PrintWriter writer = new PrintWriter(outputFile);

            // Read each line from the input file and write it to the output file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line); // Optionally print the line to the console
                writer.println(line); // Write the line to the output file
            }

            // Close the scanner and writer when done
            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
```