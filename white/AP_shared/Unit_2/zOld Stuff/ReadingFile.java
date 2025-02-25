import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingFile {

	public static void main(String[] args) {
		
		Scanner input = null;

		try {
//			System.out.print("Enter a file name (without .txt): ");
//			String filename = input.nextLine();
			input = new Scanner(new File("greeneggs.txt"));
		} 
		catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		} 

		while (input.hasNext()) {
			System.out.println(input.nextLine());
		}

	}

}
