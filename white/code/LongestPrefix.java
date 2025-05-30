import java.util.Arrays;

public class LongestPrefix {
	public static String longestCommonPrefix(String[] strs) {
		// your code here
		return "";
	}

	public static void check(String[] in, String out) {
		System.out.print("Input " + Arrays.toString(in) + "...");
		System.out.print("Output " + longestCommonPrefix(in) + "...");
		if (longestCommonPrefix(in).equals(out)) {
				System.out.println("PASSED.");
		} else {
				System.out.println("FAILED. Expected: " + out);
		}
}

public static void main(String args[]) {
		check(new String[]{"abc1", "abc2", "abc3"}, "abc");
		check(new String[]{"dog", "door", "dome"}, "do");
		check(new String[]{"scale", "tree", "bird"}, "");
		check(new String[]{"at", "atol", "attest"}, "at");
		check(new String[]{"", "", ""}, "");
		check(new String[]{"a","a","a"},"a");
	}
}
