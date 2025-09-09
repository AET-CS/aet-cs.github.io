#RPN Evaluator Extensions

1. Extend RPN evaluator to read user input instead of reading items from a predefined list. You should create a `Scanner` and a loop that reads one line of input from the user, outputs the evaluated expression, and repeats until the user enters `stop`.
2. Extend the previous version to accept unary operators such as `sin` and `cos`. You will need to convert your Stack to a stack of `Double` because sin/cos/etc return doubles and not `int`s. You may choose to read integers OR doubles from the user.
3. Finally extend the previous version to return a fully parenthesized infix expression *instead* of an evaluated version. In this case the user can input any string and it will be interpreted as a variable name. Note: you do *not* need to parse any user input as an integer or a double. Every token remains a string. See output below for a sample

#Sample Output

Here's an example of RPN Evaluator with the Unary Operator extension and the extension to convert to infix notation.

```bash
Enter RPN expression (or 'stop' to quit): 10 sin
Result: sin(10)
Enter RPN expression (or 'stop' to quit): b b * 4 a c * * - sqrt
Result: sqrt(((b*b)-(4*(a*c))))
Enter RPN expression (or 'stop' to quit): -1 b * b b * 4 a c * * - sqrt + 2 a * /
Result: (((-1*b)+sqrt(((b*b)-(4*(a*c)))))/(2*a))
Enter RPN expression (or 'stop' to quit): x y * a b - 10 /
ERROR: Invalid expression: 2 values remaining
Enter RPN expression (or 'stop' to quit): x y * a b - 10 + +
Result: ((x*y)+((a-b)+10))
Enter RPN expression (or 'stop' to quit): 1 2 3 4 5 6 - - - - -
Result: (1-(2-(3-(4-(5-6)))))
Enter RPN expression (or 'stop' to quit): stop
```