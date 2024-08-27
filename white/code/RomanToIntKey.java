import java.util.HashMap;


public class RomanToIntKey {

	static HashMap<Character, Integer> values = new HashMap<>();
	public static void initialize() {
			values.put('I',1);
			values.put('V',5);
			values.put('X',10);
			values.put('L',50);
			values.put('C',100);
			values.put('D',500);
			values.put('M',1000);
	}

	public static int romanToInt(String s) {
			initialize();
			int[] places;
			places = new int[s.length()];

			for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					places[i] = values.get(c);
			}

			for(int i = 1; i < s.length(); i++) {
					if (places[i] > places[i-1]) {
							places[i-1] = places[i-1] * -1;
					}
			}

			int result = 0;
			for (int i = 0; i < s.length(); i++) {
					result += places[i];
			}
			return result;
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
