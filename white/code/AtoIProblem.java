public class AtoIProblem {

	public static int myAtoi(String s) {
		// your code here
		return 0; // change this!
		}

public static void check(String in, int out) {
		System.out.print("Input " + in + "...");
		System.out.print("Output " + myAtoi(in) + "...");
		if (myAtoi(in) == out) {
				System.out.println("PASSED.");
		} else {
				System.out.println("FAILED. Expected: " + out);
		}
}

public static void main(String args[]) {
		check("12345", 12345);
		check("   123", 123);
		check("0", 0);
		check("-0", 0);
		check("-123", -123);
		check("    +401", 401);
		check("  234.12E", 234);
		check("  12 34 40", 12);

}
}
