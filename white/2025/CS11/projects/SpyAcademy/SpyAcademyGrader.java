/**
 * SpyAcademy Auto-Grader
 *
 * This class automatically tests all methods in the SpyAcademy lab.
 *
 * USAGE:
 * 1. Make sure SpyAcademy.java is in the same folder as this file
 * 2. Compile both files: javac SpyAcademyGrader.java SpyAcademy.java
 * 3. Run the grader: java SpyAcademyGrader
 * 4. View your score and see which tests passed/failed!
 */

public class SpyAcademyGrader {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int currentMethodTests = 0;
    private static int currentMethodPassed = 0;

    /**
     * Main method - runs all tests
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        SPY ACADEMY AUTO-GRADER v1.0                ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();

        // Test methods in order of difficulty
        testReverseString();
        testCaesarShift();
        testCaesarDecrypt();
        testCountVowels();
        testCaesarShiftPhrase();
        testCaesarDecryptPhrase();
        testIsValidDecode();

        // Display final results
        displayFinalResults();
    }

    // ==================== TEST METHODS ====================

    private static void testReverseString() {
        startMethod("reverseString", "Phase 1 - Warmup");

        testCase("reverseString(\"HELLO\")", "OLLEH",
                SpyAcademy.reverseString("HELLO"));

        testCase("reverseString(\"AGENT\")", "TNEGA",
                SpyAcademy.reverseString("AGENT"));

        testCase("reverseString(\"CODE\")", "EDOC",
                SpyAcademy.reverseString("CODE"));

        testCase("reverseString(\"A\")", "A",
                SpyAcademy.reverseString("A"));

        testCase("reverseString(\"RACECAR\")", "RACECAR",
                SpyAcademy.reverseString("RACECAR"));

        endMethod();
    }

    private static void testCaesarShift() {
        startMethod("caesarShift", "Phase 1 - Encoding");

        testCase("caesarShift(\"HELLO\", 3)", "KHOOR",
                SpyAcademy.caesarShift("HELLO", 3));

        testCase("caesarShift(\"ABC\", 1)", "BCD",
                SpyAcademy.caesarShift("ABC", 1));

        testCase("caesarShift(\"XYZ\", 3)", "ABC",
                SpyAcademy.caesarShift("XYZ", 3));

        testCase("caesarShift(\"AGENT\", 5)", "FLJSY",
                SpyAcademy.caesarShift("AGENT", 5));

        testCase("caesarShift(\"HELLO\", 0)", "HELLO",
                SpyAcademy.caesarShift("HELLO", 0));

        testCase("caesarShift(\"ZEBRA\", 1)", "AFCSB",
                SpyAcademy.caesarShift("ZEBRA", 1));

        testCase("caesarShift(\"AAA\", 25)", "ZZZ",
                SpyAcademy.caesarShift("AAA", 25));

        endMethod();
    }

    private static void testCaesarDecrypt() {
        startMethod("caesarDecrypt", "Phase 1 - Decoding");

        testCase("caesarDecrypt(\"KHOOR\", 3)", "HELLO",
                SpyAcademy.caesarDecrypt("KHOOR", 3));

        testCase("caesarDecrypt(\"BCD\", 1)", "ABC",
                SpyAcademy.caesarDecrypt("BCD", 1));

        testCase("caesarDecrypt(\"ABC\", 3)", "XYZ",
                SpyAcademy.caesarDecrypt("ABC", 3));

        testCase("caesarDecrypt(\"FLJSY\", 5)", "AGENT",
                SpyAcademy.caesarDecrypt("FLJSY", 5));

        testCase("caesarDecrypt(\"HELLO\", 0)", "HELLO",
                SpyAcademy.caesarDecrypt("HELLO", 0));

        testCase("caesarDecrypt(\"AAA\", 1)", "ZZZ",
                SpyAcademy.caesarDecrypt("AAA", 1));

        // Round-trip test
        String original = "MISSION";
        String encoded = SpyAcademy.caesarShift(original, 7);
        testCase("Round-trip: caesarDecrypt(caesarShift(\"MISSION\", 7), 7)", "MISSION",
                SpyAcademy.caesarDecrypt(encoded, 7));

        endMethod();
    }

    private static void testCountVowels() {
        startMethod("countVowels", "Phase 1 - Helper Method");

        testCase("countVowels(\"HELLO\")", 2,
                SpyAcademy.countVowels("HELLO"));

        testCase("countVowels(\"AEIOU\")", 5,
                SpyAcademy.countVowels("AEIOU"));

        testCase("countVowels(\"BCDFG\")", 0,
                SpyAcademy.countVowels("BCDFG"));

        testCase("countVowels(\"PROGRAMMING\")", 3,
                SpyAcademy.countVowels("PROGRAMMING"));

        testCase("countVowels(\"AGENT\")", 2,
                SpyAcademy.countVowels("AGENT"));

        testCase("countVowels(\"WHY\")", 0,
                SpyAcademy.countVowels("WHY"));

        endMethod();
    }

    private static void testCaesarShiftPhrase() {
        startMethod("caesarShiftPhrase", "Phase 2 - Phrase Encoding");

        testCase("caesarShiftPhrase(\"MEET AT NOON!\", 3)", "PHHW DW QRRQ!",
                SpyAcademy.caesarShiftPhrase("MEET AT NOON!", 3));

        testCase("caesarShiftPhrase(\"HELLO WORLD\", 5)", "MJQQT BTWQI",
                SpyAcademy.caesarShiftPhrase("HELLO WORLD", 5));

        testCase("caesarShiftPhrase(\"THE MISSION IS GO!\", 13)", "GUR ZVFFVBA VF TB!",
                SpyAcademy.caesarShiftPhrase("THE MISSION IS GO!", 13));

        testCase("caesarShiftPhrase(\"A B C\", 1)", "B C D",
                SpyAcademy.caesarShiftPhrase("A B C", 1));

        testCase("caesarShiftPhrase(\"TOP SECRET!!\", 7)", "AVW ZLJYLA!!",
                SpyAcademy.caesarShiftPhrase("TOP SECRET!!", 7));

        testCase("caesarShiftPhrase(\"CODE 123\", 2)", "EQFG 123",
                SpyAcademy.caesarShiftPhrase("CODE 123", 2));

        testCase("caesarShiftPhrase(\"YES-OR-NO?\", 10)", "IOC-YB-XY?",
                SpyAcademy.caesarShiftPhrase("YES-OR-NO?", 10));

        endMethod();
    }

    private static void testCaesarDecryptPhrase() {
        startMethod("caesarDecryptPhrase", "Phase 2 - Phrase Decoding");

        testCase("caesarDecryptPhrase(\"PHHW DW QRRQ!\", 3)", "MEET AT NOON!",
                SpyAcademy.caesarDecryptPhrase("PHHW DW QRRQ!", 3));

        testCase("caesarDecryptPhrase(\"MJQQT BTWQI\", 5)", "HELLO WORLD",
                SpyAcademy.caesarDecryptPhrase("MJQQT BTWQI", 5));

        testCase("caesarDecryptPhrase(\"GUR ZVFFVBA VF TB!\", 13)", "THE MISSION IS GO!",
                SpyAcademy.caesarDecryptPhrase("GUR ZVFFVBA VF TB!", 13));

        testCase("caesarDecryptPhrase(\"B C D\", 1)", "A B C",
                SpyAcademy.caesarDecryptPhrase("B C D", 1));

        testCase("caesarDecryptPhrase(\"AVW ZLJYLA!!\", 7)", "TOP SECRET!!",
                SpyAcademy.caesarDecryptPhrase("AVW ZLJYLA!!", 7));

        // Round-trip test
        String original = "THE MISSION IS GO!";
        String encoded = SpyAcademy.caesarShiftPhrase(original, 10);
        testCase("Round-trip: caesarDecryptPhrase(caesarShiftPhrase(...), 10)", original,
                SpyAcademy.caesarDecryptPhrase(encoded, 10));

        endMethod();
    }

    private static void testIsValidDecode() {
        startMethod("isValidDecode", "Optional - Validation Helper");

        String[] keywords = { "AGENT", "MISSION", "SECRET", "CODE" };

        testCase("isValidDecode(\"THE AGENT IS HERE\", keywords)", true,
                SpyAcademy.isValidDecode("THE AGENT IS HERE", keywords));

        testCase("isValidDecode(\"TOP SECRET FILES\", keywords)", true,
                SpyAcademy.isValidDecode("TOP SECRET FILES", keywords));

        testCase("isValidDecode(\"THE CODE IS READY\", keywords)", true,
                SpyAcademy.isValidDecode("THE CODE IS READY", keywords));

        testCase("isValidDecode(\"MISSION COMPLETE\", keywords)", true,
                SpyAcademy.isValidDecode("MISSION COMPLETE", keywords));

        testCase("isValidDecode(\"NOTHING HERE\", keywords)", false,
                SpyAcademy.isValidDecode("NOTHING HERE", keywords));

        testCase("isValidDecode(\"HELLO WORLD\", keywords)", false,
                SpyAcademy.isValidDecode("HELLO WORLD", keywords));

        endMethod();
    }

    // ==================== HELPER METHODS ====================

    private static void startMethod(String methodName, String description) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║  Testing: " + String.format("%-41s", methodName) + "║");
        System.out.println("║  " + String.format("%-49s", description) + "║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        currentMethodTests = 0;
        currentMethodPassed = 0;
    }

    private static void endMethod() {
        System.out.println();
        System.out.println("  Method Result: " + currentMethodPassed + "/" + currentMethodTests + " tests passed");
        if (currentMethodPassed == currentMethodTests) {
            System.out.println("  ✅ ALL TESTS PASSED!");
        } else {
            System.out.println("  ❌ Some tests failed - check your implementation");
        }
        System.out.println();
        System.out.println("────────────────────────────────────────────────────");
        System.out.println();
    }

    private static void testCase(String testName, String expected, String actual) {
        totalTests++;
        currentMethodTests++;

        System.out.println("Test " + currentMethodTests + ": " + testName);

        try {
            if (expected.equals(actual)) {
                System.out.println("  ✅ PASS");
                passedTests++;
                currentMethodPassed++;
            } else {
                System.out.println("  ❌ FAIL");
                System.out.println("     Expected: \"" + expected + "\"");
                System.out.println("     Actual:   \"" + actual + "\"");
            }
        } catch (Exception e) {
            System.out.println("  ❌ ERROR: " + e.getMessage());
            System.out.println("     Expected: \"" + expected + "\"");
        }
        System.out.println();
    }

    private static void testCase(String testName, int expected, int actual) {
        totalTests++;
        currentMethodTests++;

        System.out.println("Test " + currentMethodTests + ": " + testName);

        try {
            if (expected == actual) {
                System.out.println("  ✅ PASS");
                passedTests++;
                currentMethodPassed++;
            } else {
                System.out.println("  ❌ FAIL");
                System.out.println("     Expected: " + expected);
                System.out.println("     Actual:   " + actual);
            }
        } catch (Exception e) {
            System.out.println("  ❌ ERROR: " + e.getMessage());
            System.out.println("     Expected: " + expected);
        }
        System.out.println();
    }

    private static void testCase(String testName, boolean expected, boolean actual) {
        totalTests++;
        currentMethodTests++;

        System.out.println("Test " + currentMethodTests + ": " + testName);

        try {
            if (expected == actual) {
                System.out.println("  ✅ PASS");
                passedTests++;
                currentMethodPassed++;
            } else {
                System.out.println("  ❌ FAIL");
                System.out.println("     Expected: " + expected);
                System.out.println("     Actual:   " + actual);
            }
        } catch (Exception e) {
            System.out.println("  ❌ ERROR: " + e.getMessage());
            System.out.println("     Expected: " + expected);
        }
        System.out.println();
    }

    private static void displayFinalResults() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                   FINAL RESULTS                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();

        double percentage = (totalTests > 0) ? (passedTests * 100.0 / totalTests) : 0;

        System.out.println("  Total Tests:   " + totalTests);
        System.out.println("  Tests Passed:  " + passedTests);
        System.out.println("  Tests Failed:  " + (totalTests - passedTests));
        System.out.println();
        System.out.println("  Score: " + passedTests + "/" + totalTests + " (" +
                String.format("%.1f", percentage) + "%)");
        System.out.println();

        // Grade calculation
        String grade;
        String message;

        if (percentage >= 95) {
            grade = "A+";
            message = "Outstanding work, Agent! 🏆";
        } else if (percentage >= 90) {
            grade = "A";
            message = "Excellent work! 🎉";
        } else if (percentage >= 85) {
            grade = "B+";
            message = "Great job! 👍";
        } else if (percentage >= 80) {
            grade = "B";
            message = "Good work! Keep it up!";
        } else if (percentage >= 75) {
            grade = "C+";
            message = "Solid effort! Review failed tests.";
        } else if (percentage >= 70) {
            grade = "C";
            message = "Passing, but needs improvement.";
        } else if (percentage >= 65) {
            grade = "D";
            message = "Review the concepts and try again.";
        } else {
            grade = "F";
            message = "Keep practicing! Check the handout for help.";
        }

        System.out.println("  Grade: " + grade);
        System.out.println("  " + message);
        System.out.println();

        // Specific feedback
        if (passedTests < totalTests) {
            System.out.println("  💡 TIP: Look at the failed tests above to see what");
            System.out.println("     needs fixing. Compare your output to the expected");
            System.out.println("     results and check your logic!");
        }

        System.out.println();
        System.out.println("════════════════════════════════════════════════════");
    }
}
