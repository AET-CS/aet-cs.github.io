import java.util.*;

public class BooleanExpressionParser {

    public static boolean parseBooleanExpression(String expression) {
        // Remove any spaces
        expression = expression.replaceAll("\\s+", "");

        // Convert the expression to a list of tokens
        List<String> tokens = tokenize(expression);

        // Parse the expression from tokens and return the result
        return parseExpression(tokens);
    }

    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(' || c == ')' || c == '!' || c == '&' || c == '|') {
                // Add the current token if it's non-empty
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }

                // Add operators and parentheses as tokens
                if (c == '&' || c == '|') {
                    // Handle && and ||
                    tokens.add(expression.substring(i, i + 2));
                    i++; // Skip next character
                } else {
                    tokens.add(String.valueOf(c));
                }
            } else {
                currentToken.append(c);
            }
        }

        // Add the last token if it's non-empty
        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private static boolean parseExpression(List<String> tokens) {
        Stack<Boolean> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        int i = 0;
        while (i < tokens.size()) {
            String token = tokens.get(i);

            if (token.equals("true")) {
                values.push(true);
            } else if (token.equals("false")) {
                values.push(false);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                // Evaluate the expression within the parentheses
                while (!operators.peek().equals("(")) {
                    evaluateTop(values, operators.pop());
                }
                operators.pop(); // Remove '('
            } else if (token.equals("&&") || token.equals("||")) {
                // Handle operator precedence
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    evaluateTop(values, operators.pop());
                }
                operators.push(token);
            } else if (token.equals("!")) {
                operators.push(token);
            }

            i++;
        }

        // Evaluate any remaining operators
        while (!operators.isEmpty()) {
            evaluateTop(values, operators.pop());
        }

        return values.pop();
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "||":
                return 1;
            case "&&":
                return 2;
            case "!":
                return 3;
            default:
                return -1;
        }
    }

    private static void evaluateTop(Stack<Boolean> values, String operator) {
        if (operator.equals("!")) {
            boolean value = values.pop();
            values.push(!value);
        } else {
            boolean b = values.pop();
            boolean a = values.pop();
            if (operator.equals("&&")) {
                values.push(a && b);
            } else if (operator.equals("||")) {
                values.push(a || b);
            }
        }
    }

    public static void main(String[] args) {
        String expression = "true && !(true || false)";
        boolean result = parseBooleanExpression(expression);
        System.out.println("Output: " + result);  // Output: false
    }
}
