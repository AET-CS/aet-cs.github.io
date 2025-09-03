
// INSTRUCTIONS
// Read this code thoroughly before trying to write anything!
// Then look for the "ADD CODE HERE" sections
// Read the surrounding code and fill in the missing pieces
// Feel free to google any java methods or documentation you need
// As, is this class will throw an error when you try to run it.
// When working, it computes several examples so you can see it works

// WHEN THIS IS DONE -- modify the main method to take in user input
// And return a value (you may need to add error tests)

import java.util.Stack;

public class RPNEvaluator {

    /**
     * Evaluates a valid RPN expression string containing integers and basic
     * operators.
     * Separated by white space
     * 
     * @param rpnExpression Space-separated RPN expression (e.g., "3 5 + 2 *")
     * @return The evaluated result
     * @throws IllegalArgumentException if expression is invalid
     */
    public static int evaluate(String rpnExpression) {
        if (rpnExpression == null || rpnExpression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        Stack<Integer> stack = new Stack<>();
        // create an array of String objects names "tokens", by calling "trim" and
        // "split" on the input string
        // you want to split the array into Strings separated by whitespace
        // ADD CODE HERE

        // loop over every token in the string
        for (String token : tokens) {
            if (isOperator(token)) {
                // Need at least 2 operands for binary operators
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands for " + token);
                }

                // Pop two operands (order matters for - and /) off the stack
                // then call "apply operator"
                // then push the resulting value back on the stack
                // ADD CODE HERE

            } else {
                // Parse and push number
                try {
                    int number = Integer.parseInt(token);
                    stack.push(number);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid token: " + token);
                }
            }
        }

        // Valid RPN should leave exactly one value on stack
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: " + stack.size() + " values remaining");
        }

        // return the value on the top of the stack
        // ADD CODE HERE
    }

    /**
     * Check if token is a supported operator.
     */
    private static boolean isOperator(String token) {
        // return True iff token is a valid binary operand
        // otherwise False
        // ADD CODE HERE
    }

    /**
     * Apply the given operator to two operands.
     */
    private static int applyOperator(int left, int right, String operator) {
        switch (operator) {
            // calcuate the correct value of this two-operand expression
            // and return it
            // you may assume the expression is valid
            // or optionally throw an IllegalArgumentException
            // ADD CODE HERE

        }

    }
    // ---------------------------------------------------------------------
    // Below this line, you do not need to make any changes
    // ---------------------------------------------------------------------

    /**
     * Test the RPN evaluator with some examples.
     */
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "3 5 +", // 8
                "15 7 1 1 + - / 3 *", // 9
                "7 2 + 3 4 * 2 / -", // 3
                "8 3 2 * + 5 4 - 6 * 2 / +", // 17
                "4 5 * 3 2 + / 6 -" // -2
        };

        System.out.println("RPN Evaluator Test Results:");
        System.out.println("===========================");

        for (String expression : testCases) {
            try {
                int result = evaluate(expression);
                System.out.printf("%-25s = %d%n", "\"" + expression + "\"", result);
            } catch (Exception e) {
                System.out.printf("%-25s = ERROR: %s%n", "\"" + expression + "\"", e.getMessage());
            }
        }

        // Show step-by-step evaluation for one example
        System.out.println("\nStep-by-step evaluation of \"7 2 + 3 4 * 2 / -\":");
        evaluateWithSteps("7 2 + 3 4 * 2 / -");
    }

    /**
     * Evaluates RPN expression showing stack state at each step (for
     * demonstration).
     */
    public static void evaluateWithSteps(String rpnExpression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = rpnExpression.trim().split("\\s+");

        System.out.printf("%-8s %-12s %s%n", "Token", "Operation", "Stack");
        System.out.println("--------------------------------");

        for (String token : tokens) {
            if (isOperator(token)) {
                int right = stack.pop();
                int left = stack.pop();
                int result = applyOperator(left, right, token);
                stack.push(result);
                System.out.printf("%-8s %-12s %s%n", token,
                        left + " " + token + " " + right + " = " + result, stack);
            } else {
                int number = Integer.parseInt(token);
                stack.push(number);
                System.out.printf("%-8s %-12s %s%n", token, "push " + number, stack);
            }
        }

        System.out.println("Final result: " + stack.peek());
    }
}