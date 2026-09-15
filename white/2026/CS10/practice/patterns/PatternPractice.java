public class PatternPractice {
    public static void rectangle1() {
        // Make a 4x6 rectangle
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                System.out.print("*");
            }
            System.out.println(); // Move to next line
        }
    }

    public static void rectangle2() {
        // Make a 4x6 rectangle
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print("*");
            }
            System.out.println(); // Move to next line
        }
    }

    public static void triangleL() {
        // Make a 4x6 rectangle
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < row + 1; col++) {
                System.out.print("*");
            }
            System.out.println(); // Move to next line
        }
    }

    public static void triangleR() {
        // Make a 4x6 rectangle
        for (int stars = 1; stars <= 5; stars++) {
            int spaces = 5 - stars;
            for (int i = 1; i <= spaces; i++) {
                System.out.print(" ");
            }
            for (int i = 1; i <= stars; i++) {
                System.out.print("*");
            }
            System.out.println(); // Move to next line
        }
    }

    public static void odd() {
        for (int stars = 1; stars <= 9; stars += 2) {
            for (int i = 1; i <= stars; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void centered() {
        for (int stars = 1; stars <= 9; stars += 2) {
            int spaces = (9 - stars) / 2;
            for (int i = 1; i <= spaces; i++) {
                System.out.print(" ");
            }
            for (int i = 1; i <= stars; i++) {
                System.out.print("*");
            }
            for (int i = 1; i <= spaces; i++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        rectangle1();
        System.out.println();
        triangleL();
        System.out.println();
        triangleR();
        System.out.println();
        odd();
        System.out.println();
        centered();
    }
}