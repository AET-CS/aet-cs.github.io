public class ReverseIntKey {
	public static int reverse(int x) {
		if (x == 1 << 31) return 0;
		int max = ~(1 << 31) / 10;
		int w = x;
		x = Math.abs(x);
		int new_y = x % 10;
		int y = new_y;
		while(x > 9) {
				x = x/10;
				new_y = 10*y + x % 10;
				if (y > max) {
						return 0;
				}
				y = new_y;
		}
		if (w > 0) {
				return y;
		}
		return -y;
	}

	public static void check(int in, int out) {
		System.out.print("Input " + in + "...");
		System.out.print("Output " + reverse(in) + "...");
		if (reverse(in) == out) {
				System.out.println("PASSED.");
		} else {
				System.out.println("FAILED. Expected: " + out);
		}
}

public static void main(String args[]) {
		check(1423, 3241);
		check(1001, 1001);
		check(1234567, 7654321);
		check(12300, 321);
		check(1, 1);
		check(0, 0);
		check(100,1);

	}
}
