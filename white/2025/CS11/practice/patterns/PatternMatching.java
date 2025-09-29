public class PatternMatching {

    public static void p1() {
        for (int row = 1; row <= 6; row++) {
            for (int col = 1; col <= 8; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void p2() {
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 8; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void p3() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 5; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void p4() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 5; col++) {
                System.out.print("*");
            }
        }
    }

    public static void p5() {
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 2 * row; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void p6() {
        for (int stars = 1; stars >= 5; stars++) {
            int spaces = 7 - stars;
            for (int i = 1; i <= stars; i++) {
                System.out.print("*");
            }
            for (int i = 1; i <= spaces; i++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void p7() {
        for (int stars = 5; stars >= 1; stars--) {
            int spaces = 7 - stars;
            for (int i = 1; i <= stars; i++) {
                System.out.print("*");
            }
            for (int i = 1; i <= spaces; i++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void p8() {
        for (int stars = 5; stars >= 1; stars--) {
            int spaces = 7 - stars;
            for (int i = 1; i < stars; i++) {
                System.out.print("*");
            }
            for (int i = 1; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void p9() {
        for (int stars = 5; stars >= 1; stars--) {
            int spaces = 7 - stars;
            for (int i = 1; i <= spaces; i++) {
                System.out.print(" ");
            }
            for (int i = 1; i <= stars; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void p10() {
        for (int i = 0; i <= 10; i++) {
            int stars = Math.abs(5 - i);
            int spaces = 5 - stars;
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= stars; j++) {
                System.out.print("**");
            }
            System.out.println();
        }
    }

    public static void p11() {
        for (int i = 0; i <= 10; i++) {
            int stars = 5 - Math.abs(5 - i);
            int spaces = 5 - stars;
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= stars; j++) {
                System.out.print("**");
            }
            System.out.println();
        }
    }

    public static void p12() {
        for (int i = 0; i <= 10; i++) {
            int stars = 5 - Math.abs(5 - i);
            int spaces = 5 - stars;
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printAnswer(boolean decrypt) {
        String answer = "¢gR¢eR¢dR¢cbR¢ccR¢iR¢k";
        String decodeString = "";
        if (!decrypt) {
            return;
        }
        for (char c : answer.toCharArray()) {
            c = (char) (((int) c - 50) % 255);
            decodeString += c;
        }
        System.out.println(decodeString);
    }

    public static void main(String[] args) {
        printAnswer(false);
    }

}
