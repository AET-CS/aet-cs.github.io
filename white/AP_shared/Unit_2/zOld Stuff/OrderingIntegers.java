import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderingIntegers {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		List<Integer> numbers = new ArrayList<>();
		
		try {
			System.out.print("Enter a file name (without the .txt):");
			String filename = input.nextLine();
			input = new Scanner(new File(filename + ".txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error.  File not found.");
		}
		
		// put the first number into the array list
		if (input.hasNextInt()) {
			numbers.add(input.nextInt());
		}
		
		while (input.hasNextInt()) {
			int number = input.nextInt();
			
			// Orders numbers in ascending order
			int index = numbers.size() - 1;
			while (index >= 0 && number < numbers.get(index)) {
				index--;
			}
			numbers.add(index+1,  number);
			
	
			// Orders numbers in descending order
			
//			int index = 0;
//			while (index < numbers.size() && number <= numbers.get(index)) {
//				index++;
//			}
//			numbers.add(index, number);
			
		}
		
		System.out.println(numbers);
		
		
		
	}

}
