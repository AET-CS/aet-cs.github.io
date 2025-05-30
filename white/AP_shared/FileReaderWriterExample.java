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