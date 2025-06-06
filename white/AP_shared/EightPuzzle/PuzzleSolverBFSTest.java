// Run this file to test your PuzzleSolver class
// This assumes you use the specs given in the notes


import java.util.Arrays;
import java.util.List;
import java.util.Stack;



public class PuzzleSolverBFSTest {
    private int totalTests = 0;
    private int passedTests = 0;

    private int[][] start_states = {
            {1, 2, 3, 4, 5, 6, 7, 8, 0},
            {1, 2, 3, 4, 5, 0, 7, 8, 6},
            {1, 2, 3, 4, 0, 5, 7, 8, 6},
            {1, 2, 3, 5, 0, 6, 4, 7, 8},
            {1, 5, 0, 4, 3, 2, 7, 8, 6},
            {0, 1, 3, 5, 2, 6, 4, 7, 8},
            {1, 0, 2, 4, 8, 3, 7, 6, 5},
            {1, 2, 3, 0, 4, 8, 7, 6, 5},
            {5, 4, 2, 0, 1, 3, 7, 8, 6},
            {1, 2, 0, 4, 5, 6, 7, 3, 8},
            {2, 6, 5, 1, 0, 3, 4, 7, 8},
            {4, 1, 5, 8, 0, 2, 7, 6, 3},
            {5, 1, 3, 2, 8, 0, 4, 6, 7},
            {4, 8, 1, 5, 2, 3, 7, 6, 0},
            {5, 6, 0, 2, 8, 3, 1, 4, 7},
            {3, 5, 8, 1, 0, 4, 7, 6, 2},
            {4, 2, 1, 7, 0, 8, 5, 6, 3},
            {0, 1, 6, 8, 2, 4, 5, 7, 3}
    };

    private int[] steps = {
            0,
            1,
            2,
            4,
            6,
            6,
            7,
            7,
            9,
            10,
            10,
            12,
            13,
            14,
            16,
            16,
            16,
            24
    };

    public static void main(String[] args) {
        PuzzleSolverBFSTest tester = new PuzzleSolverBFSTest();

        System.out.println("=== Testing PuzzleSolver ===\n");
        tester.testCases();
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

    public void generateTestCases() {
        PuzzleModel model = new PuzzleModel(3);
        PuzzleSolver solver = new PuzzleSolver(3);
        for (int i = 0; i<20; i++) {
            model.limitedShuffle((int) (i*i / 4));
            Stack<Integer> solution = new Stack<>();
            String start_state = Arrays.toString(model.getBoard());
            solution = solver.bfs_solve(model.getBoard());
        }
        model.limitedShuffle(5);
    }

    public void testCases() {
        PuzzleSolver solver = new PuzzleSolver(3);
        for (int i = 0; i < steps.length; i++) {
            Stack<Integer> solution = solver.bfs_solve(start_states[i]);
            boolean passed = solution.size() == steps[i];
            String result = "";
            if (passed) {
                result = "PASS";
            } else {
                result = "FAIL";
            }
            System.out.println(result + " : Testing state: " + Arrays.toString(start_states[i]) + ". Expected solution length: " + solution.size() + " Received length: " + steps[i]);
            recordTest(passed);
        }
    }
}