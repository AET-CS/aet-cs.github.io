

public class AtoIProblemKey {


	public static int myAtoi(String s) {
					int value = 0;
					int sign = 1;
					int index = 0;
					int bigLimit = ~(1<<31);
					int smallLimit = 1<<31;

					while((index < s.length()) && (s.charAt(index)==' ')) index++;
					if(s.charAt(index)=='-') {
							sign = -1;
							index++;
					}
					if(s.charAt(index)=='+') {
							sign = 1;
							index++;
					}
					while(index < s.length() && Character.isDigit(s.charAt(index))) {
							if (value > bigLimit/10) return bigLimit;
							if (value < smallLimit/10) return smallLimit;
							value = 10*value + (s.charAt(index)-'0');
							index++;
					}
					return sign*value;
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
