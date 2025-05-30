public class RomanToInt {

public static int romanToInt(String s) {
	// your code here
	return 0; // edit this
}
public static void check(String in, int out) {
		System.out.print("Input " + in + "...");
		System.out.print("Output " + romanToInt(in) + "...");
		if (romanToInt(in) == out) {
				System.out.println("PASSED.");
		} else {
				System.out.println("FAILED. Expected: " + out);
		}
}

public static void main(String args[]) {
		check("XVIII", 18);
		check("MMLXVII", 2067);
		check("XXIV", 24);
		check("LXIV", 64);
		check("CCCXLIV", 344);
		check("CCXXX", 230);
	}
}
