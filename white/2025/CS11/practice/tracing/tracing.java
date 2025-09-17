class tracing {

    public static void trace1() {
        int x = 5;
        int y = 3;
        x = x + y;
        y = x - y;
        System.out.println(x + " " + y);
    }

    public static void trace2() {
        int count = 0;
        for (int i = 1; i <= 5; i++) {
            count = count + i;
        }
        System.out.println(count);
    }

    public static void trace3(int n) {
        if (n > 10) {
            n = n * 2;
        } else {
            n = n + 5;
        }
        System.out.println(n);
    }

    public static void trace4() {
        int x = 10;
        int count = 0;
        while (x > 0) {
            x = x - 3;
            count++;
        }
        System.out.println(count + " " + x);
    }

    public static void trace5(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                sum = sum + i;
            }
        }
        System.out.println(sum);
    }

    public static void trace6(int n) {
        int a = 1;
        int b = 0;
        while (a < n) {
            a = a * 2;
            b++;
        }
        System.out.println(a + " " + b);
    }

    public static void trace7(int x, int y) {
        int result = 0;
        if (x > 5) {
            if (y < 10) {
                result = x + y;
            } else {
                result = x - y;
            }
        } else {
            result = x * y;
        }
        System.out.println(result);
    }

    public static void trace8() {
        int x = 1;
        int y = 10;
        while (x < y) {
            x = x + 2;
            y = y - 1;
        }
        System.out.println(x + " " + y);
    }

    public static void trace9(int n) {
        int result = 1;
        int count = 0;
        while (result <= n) {
            result = result * 3;
            count++;
        }
        System.out.println(count + " " + result);
    }

    public static void trace10(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        System.out.println(a);
    }

    public static void main(String args[]) {
        trace1();
        trace2();
        trace3(7);
        trace4();
        trace5(6);
        trace6(20);
        trace7(8, 15);
        trace8();
        trace9(50);
        trace10(5);
    }

}