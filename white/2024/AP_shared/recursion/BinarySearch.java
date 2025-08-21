package binarySearch;

import java.util.Scanner;

public class BinarySearch {

    public static int binarySearch(int[] array, int target, int L, int H) {
			// add code here
			// comment EVERY LINE with a sentence explanation
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner scanner = new Scanner(System.in);
        int[] primes = {2,3,5,7,11,13,17,19,23,29,31};
        while (!end) {
            System.out.print("Enter a number to search, 0 to end: ");
            int number = scanner.nextInt();

						// fill in the missing line here

						if (result ==-1) System.out.println("Not found");
            if (result > -1) System.out.println("Found at index " + result);
            end = (number == 0);

        }
    }
}
