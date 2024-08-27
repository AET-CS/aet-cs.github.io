import java.util.Arrays;

public class LongestPrefixKey {
	public static String longestCommonPrefix(String[] strs) {

		if (strs.length == 1) return strs[0];

		int shortestLength = 1000;
		for (String s : strs) {
				if (s.length() < shortestLength) {
						shortestLength = s.length();
				}
		}
		if (shortestLength == 0) return "";

		boolean matching = true;
		int end = 0;
		while (matching && end < shortestLength) {
				for (int i = 1; i < strs.length; i++) {
						matching = matching && (strs[i].charAt(end) == strs[0].charAt(end));
				}
				if (matching) end++;
		}
		return strs[0].substring(0,end);
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
