// Run this file to test your PuzzleSolver class
// This assumes you use the specs given in the notes


import java.util.*;

public class PuzzleSolverTest {
    private int totalTests = 0;
    private int passedTests = 0;

    public static void main(String[] args) {
        PuzzleSolverTest tester = new PuzzleSolverTest();

        System.out.println("=== Testing PuzzleSolver Class ===\n");

        tester.testConstructor();
        tester.testValidMoves();
        tester.testMakeMove();
        tester.testIsSolved();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Total Tests: " + tester.totalTests);
        System.out.println("Passed: " + tester.passedTests);
        System.out.println("Failed: " + (tester.totalTests - tester.passedTests));
        System.out.println("Success Rate: " + String.format("%.1f%%",
                (tester.passedTests * 100.0 / tester.totalTests)));
    }

    private void recordTest(boolean passed) {
        totalTests++;
        if (passed) passedTests++;
    }

    public void testConstructor() {
        System.out.println("Testing Constructor:");

        // Test 1: Basic constructor functionality
        try {
            PuzzleSolver solver3x3 = new PuzzleSolver(3);
            PuzzleSolver solver4x4 = new PuzzleSolver(4);
            System.out.println("Test 1 - Constructor for different sizes");
            System.out.println("Expected: No exceptions thrown");
            System.out.println("Actual: No exceptions thrown");
            System.out.println("Result: PASS\n");
            recordTest(true);
        } catch (Exception e) {
            System.out.println("Test 1 - Constructor for different sizes");
            System.out.println("Expected: No exceptions thrown");
            System.out.println("Actual: Exception - " + e.getMessage());
            System.out.println("Result: FAIL\n");
            recordTest(false);
        }
    }

    public void testValidMoves() {
        System.out.println("Testing validMoves method:");
        PuzzleSolver solver = new PuzzleSolver(3);

        // Test 2: Blank in top-left corner (position 0)
        int[] state1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> moves1 = solver.validMoves(state1);
        List<Integer> expected1 = Arrays.asList(3, 1);
        boolean test2Pass = moves1.contains(1) && moves1.contains(3) && moves1.size() == 2;
        System.out.println("Test 2 - Blank at top-left corner (position 0)");
        System.out.println("State: " + Arrays.toString(state1));
        System.out.println("Expected moves: " + expected1 + " (any order)");
        System.out.println("Actual moves: " + moves1);
        System.out.println("Result: " + (test2Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test2Pass);

        // Test 3: Blank in center (position 4)
        int[] state2 = {1, 2, 3, 4, 0, 5, 6, 7, 8};
        List<Integer> moves2 = solver.validMoves(state2);
        List<Integer> expected2 = Arrays.asList(7, 1, 5, 3);
        boolean test3Pass = moves2.contains(1) && moves2.contains(3) && moves2.contains(5) && moves2.contains(7) && moves2.size() == 4;
        System.out.println("Test 3 - Blank in center (position 4)");
        System.out.println("State: " + Arrays.toString(state2));
        System.out.println("Expected moves: " + expected2 + " (any order)");
        System.out.println("Actual moves: " + moves2);
        System.out.println("Result: " + (test3Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test3Pass);

        // Test 4: Blank in bottom-right corner (position 8)
        int[] state3 = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        List<Integer> moves3 = solver.validMoves(state3);
        List<Integer> expected3 = Arrays.asList(5, 7);
        boolean test4Pass = moves3.contains(5) && moves3.contains(7) && moves3.size() == 2;
        System.out.println("Test 4 - Blank in bottom-right corner (position 8)");
        System.out.println("State: " + Arrays.toString(state3));
        System.out.println("Expected moves: " + expected3 + " (any order)");
        System.out.println("Actual moves: " + moves3);
        System.out.println("Result: " + (test4Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test4Pass);

        // Test 5: Blank on edge (position 1)
        int[] state4 = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> moves4 = solver.validMoves(state4);
        List<Integer> expected4 = Arrays.asList(4, 0, 2);
        boolean test5Pass = moves4.contains(0) && moves4.contains(2) && moves4.contains(4) && moves4.size() == 3;
        System.out.println("Test 5 - Blank on edge (position 1)");
        System.out.println("State: " + Arrays.toString(state4));
        System.out.println("Expected moves: " + expected4 + " (any order)");
        System.out.println("Actual moves: " + moves4);
        System.out.println("Result: " + (test5Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test5Pass);
    }

    public void testMakeMove() {
        System.out.println("Testing makeMove method:");
        PuzzleSolver solver = new PuzzleSolver(3);

        // Test 6: Move blank from position 0 to position 1
        int[] state1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] result1 = solver.makeMove(state1, 1);
        int[] expected1 = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        boolean test6Pass = Arrays.equals(result1, expected1);
        System.out.println("Test 6 - Move blank from position 0 to position 1");
        System.out.println("Original state: " + Arrays.toString(state1));
        System.out.println("Move to position: 1");
        System.out.println("Expected result: " + Arrays.toString(expected1));
        System.out.println("Actual result: " + Arrays.toString(result1));
        System.out.println("Result: " + (test6Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test6Pass);

        // Test 7: Move blank from center
        int[] state2 = {1, 2, 3, 4, 0, 5, 6, 7, 8};
        int[] result2 = solver.makeMove(state2, 1);
        int[] expected2 = {1, 0, 3, 4, 2, 5, 6, 7, 8};
        boolean test7Pass = Arrays.equals(result2, expected2);
        System.out.println("Test 7 - Move blank from center to position 1");
        System.out.println("Original state: " + Arrays.toString(state2));
        System.out.println("Move to position: 1");
        System.out.println("Expected result: " + Arrays.toString(expected2));
        System.out.println("Actual result: " + Arrays.toString(result2));
        System.out.println("Result: " + (test7Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test7Pass);

        // Test 8: Move blank from bottom (corrected test case)
        int[] state3 = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        int[] result3 = solver.makeMove(state3, 5);
        int[] expected3 = {1, 2, 3, 4, 5, 0, 7, 6, 8};
        boolean test8Pass = Arrays.equals(result3, expected3);
        System.out.println("Test 8 - Move blank from position 7 to position 5");
        System.out.println("Original state: " + Arrays.toString(state3));
        System.out.println("Move to position: 5");
        System.out.println("Expected result: " + Arrays.toString(expected3));
        System.out.println("Actual result: " + Arrays.toString(result3));
        System.out.println("Result: " + (test8Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test8Pass);

        // Test 9: Original state should be unchanged
        int[] originalState1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        boolean test9Pass = Arrays.equals(state1, originalState1);
        System.out.println("Test 9 - Original state immutability");
        System.out.println("Expected original state: " + Arrays.toString(originalState1));
        System.out.println("Actual original state: " + Arrays.toString(state1));
        System.out.println("Result: " + (test9Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test9Pass);
    }

    public void testIsSolved() {
        System.out.println("Testing isSolved method:");
        PuzzleSolver solver = new PuzzleSolver(3);

        // Test 10: Goal state
        int[] goalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        boolean result1 = solver.isSolved(goalState);
        boolean expected1 = true;
        boolean test10Pass = (result1 == expected1);
        System.out.println("Test 10 - Goal state recognition");
        System.out.println("State: " + Arrays.toString(goalState));
        System.out.println("Expected: " + expected1);
        System.out.println("Actual: " + result1);
        System.out.println("Result: " + (test10Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test10Pass);

        // Test 11: Unsolved state
        int[] unsolvedState = {1, 2, 3, 4, 5, 6, 8, 7, 0};
        boolean result2 = solver.isSolved(unsolvedState);
        boolean expected2 = false;
        boolean test11Pass = (result2 == expected2);
        System.out.println("Test 11 - Unsolved state (swapped 7 and 8)");
        System.out.println("State: " + Arrays.toString(unsolvedState));
        System.out.println("Expected: " + expected2);
        System.out.println("Actual: " + result2);
        System.out.println("Result: " + (test11Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test11Pass);

        // Test 12: Another unsolved state
        int[] unsolvedState2 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        boolean result3 = solver.isSolved(unsolvedState2);
        boolean expected3 = false;
        boolean test12Pass = (result3 == expected3);
        System.out.println("Test 12 - Unsolved state (blank at start)");
        System.out.println("State: " + Arrays.toString(unsolvedState2));
        System.out.println("Expected: " + expected3);
        System.out.println("Actual: " + result3);
        System.out.println("Result: " + (test12Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test12Pass);

        // Test 13: Almost solved state
        int[] almostSolved = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        boolean result4 = solver.isSolved(almostSolved);
        boolean expected4 = false;
        boolean test13Pass = (result4 == expected4);
        System.out.println("Test 13 - Almost solved state (blank and 8 swapped)");
        System.out.println("State: " + Arrays.toString(almostSolved));
        System.out.println("Expected: " + expected4);
        System.out.println("Actual: " + result4);
        System.out.println("Result: " + (test13Pass ? "PASS" : "FAIL") + "\n");
        recordTest(test13Pass);
    }
}